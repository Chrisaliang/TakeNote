package com.chris.takenote;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;

public class ProgressView extends View {

    private final static float DEFAULT_SCALE_LENGTH = 16;
    private static final float DEFAULT_SCALE_MARGIN = 4;
    /**
     * 进度最小值 默认0
     */
    private static final int PROGRESS_MIN_VALUE = 0;
    /**
     * 进度最大值 默认100
     */
    private static final int PROGRESS_MAX_VALUE = 100;

    private Paint progressPaint;
    private float sx, sy, ex, ey, px, py;
    private Drawable currentProgressDrawable;
    private Drawable otherProgressDrawable;
    private Drawable gradientProgressDrawable;
    @ColorInt
    private int progressOtherColor;
    @ColorInt
    private int progressCurrentColor;

    private RectF rect = new RectF();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int progress;
    private Rect currentRect = new Rect();
    private Rect otherRect = new Rect();
    private float scale_length;
    private float scale_margin;
    /**
     * 水平中心点 center y
     */
    private int cy;

    /**
     * 刻度的宽度
     */
    private int bw;
    private Path rightPath = new Path();
    private Path leftPath = new Path();
    private DrawFilter gradientFilter;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @SuppressWarnings("unused")
    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(progressOtherColor);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);

        gradientFilter = new DrawFilter();

        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        scale_margin = t.getDimensionPixelSize(R.styleable.ProgressView_pv_scale_margin,
                (int) dp2px(DEFAULT_SCALE_MARGIN));
        scale_length = t.getDimensionPixelSize(R.styleable.ProgressView_pv_scale_length,
                (int) dp2px(DEFAULT_SCALE_LENGTH));
        progress = t.getInt(R.styleable.ProgressView_pv_progress, 0);
        progressCurrentColor = t.getColor(R.styleable.ProgressView_pgs_current_color, Color.GREEN);
        progressOtherColor = t.getColor(R.styleable.ProgressView_pgs_other_color, Color.GRAY);
        currentProgressDrawable = t.getDrawable(R.styleable.ProgressView_pv_currentDrawable);
        otherProgressDrawable = t.getDrawable(R.styleable.ProgressView_pv_otherDrawable);
        gradientProgressDrawable = t.getDrawable(R.styleable.ProgressView_pv_gradientDrawable);
        bw = t.getDimensionPixelSize(R.styleable.ProgressView_pv_scale_bound_width,
                (int) dp2px(4));
        t.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        cy = getMeasuredWidth() / 2;

        sx = getMeasuredWidth() / 2;
        sy = scale_margin;
        ex = sx;
        ey = scale_length + sy;

        px = sx;
        py = px;

        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        currentRect.set(
                (int) (cy - bw / 2.0 + 0.5),
                (int) (sy + 0.5),
                (int) (cy + bw / 2.0 + 0.5),
                (int) (ey + scale_length / 2.0 + 0.5)
        );
        otherRect.set(
                (int) (cy - bw / 2.0 + 0.5),
                (int) (sy + 0.5),
                (int) (cy + bw / 2.0 + 0.5),
                (int) (ey + 0.5)
        );
        rightPath.moveTo((int) (cy - bw / 2.0 + 0.5) - 1, (int) (sy - 0.5) - 1); // 左上
        rightPath.lineTo((int) (cy + bw / 2.0 + 0.5) + 1, (int) (sy - 0.5) - 1); // 右上
        rightPath.lineTo((int) (cy + bw / 2.0 + 0.5) + 1, (int) (ey + 0.5) + 1); // 右下
        rightPath.lineTo((int) (cy - bw / 2.0 + 0.5) - 1, (int) (ey + scale_length / 2.0 + 0.5) + 1); //左下
        rightPath.close();


        leftPath.moveTo((int) (cy - bw / 2.0 + 0.5) - 1, (int) (sy - 0.5) - 1); // 左上
        leftPath.lineTo((int) (cy + bw / 2.0 + 0.5) + 1, (int) (sy - 0.5) - 1); // 右上
        leftPath.lineTo((int) (cy + bw / 2.0 + 0.5) + 1, (int) (ey + scale_length / 2.0 + 0.5) + 1); // 右下
        leftPath.lineTo((int) (cy - bw / 2.0 + 0.5) - 1, (int) (ey + 0.5) + 1); //左下
        leftPath.close();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        checkValue();

        super.onDraw(canvas);

        canvas.drawRect(rect, paint);

        drawProgress(canvas);

        drawText(canvas);

        drawShadow(canvas);
    }

    private void drawShadow(Canvas canvas) {
//        progressPaint.setStrokeWidth(2);
        otherProgressDrawable.setBounds(otherRect);
        currentProgressDrawable.setBounds(currentRect);
        gradientProgressDrawable.setBounds(currentRect);
        canvas.save();
        canvas.setDrawFilter(gradientFilter);
//        canvas.clipRect(0,0,getMeasuredWidth(),getMeasuredWidth());
        canvas.scale(1f,-1f,getMeasuredWidth() / 2, getMeasuredHeight() / 1.65f);
//        canvas.rotate(180, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        if (progress == 0) {
            for (int i = 1; i <= 100; i++) {
                canvas.rotate(3.6f, px, py);
                otherProgressDrawable.draw(canvas);
            }
        } else {
            for (int i = 1; i <= 100; i++) {
                canvas.rotate(3.6f, px, py);
                if (i == progress) {
                    currentProgressDrawable.draw(canvas);
                } else if (i == progress + 1 || (progress == 100 && i == 1)) {
                    canvas.save();
                    canvas.clipPath(rightPath);
                    gradientProgressDrawable.draw(canvas);
                    canvas.restore();
                } else if (i == progress - 1 || (progress == 1 && i == 100)) {
                    canvas.save();
                    canvas.clipPath(leftPath);
                    gradientProgressDrawable.draw(canvas);
                    canvas.restore();
                } else {
                    otherProgressDrawable.draw(canvas);
                }
            }
        }
        canvas.restore();
    }

    private void drawText(Canvas canvas) {
        canvas.drawText("text", 100, 100, paint);
    }

    private void checkValue() {
        if (progress < PROGRESS_MIN_VALUE) progress = PROGRESS_MIN_VALUE;
        if (progress > PROGRESS_MAX_VALUE) progress = PROGRESS_MAX_VALUE;

        if (currentProgressDrawable == null)
            currentProgressDrawable = new ColorDrawable(progressCurrentColor);
        if (otherProgressDrawable == null)
            otherProgressDrawable = new ColorDrawable(progressOtherColor);
        if (gradientProgressDrawable == null)
            gradientProgressDrawable = new ColorDrawable(
                    getMiddleColor(progressCurrentColor, progressOtherColor));
    }

    private void drawProgress(Canvas canvas) {
//        progressPaint.setStrokeWidth(2);
        otherProgressDrawable.setBounds(otherRect);
        currentProgressDrawable.setBounds(currentRect);
        gradientProgressDrawable.setBounds(currentRect);
        canvas.save();
        if (progress == 0) {
            for (int i = 1; i <= 100; i++) {
                canvas.rotate(3.6f, px, py);
                otherProgressDrawable.draw(canvas);
            }
        } else {
            for (int i = 1; i <= 100; i++) {
                canvas.rotate(3.6f, px, py);
                if (i == progress) {
                    currentProgressDrawable.draw(canvas);
                } else if (i == progress + 1 || (progress == 100 && i == 1)) {
                    canvas.save();
                    canvas.clipPath(rightPath);
                    gradientProgressDrawable.draw(canvas);
                    canvas.restore();
                } else if (i == progress - 1 || (progress == 1 && i == 100)) {
                    canvas.save();
                    canvas.clipPath(leftPath);
                    gradientProgressDrawable.draw(canvas);
                    canvas.restore();
                } else {
                    otherProgressDrawable.draw(canvas);
                }
            }
        }
        canvas.restore();
    }


    @SuppressWarnings({"unused", "SameParameterValue"})
    private float sp2px(float spValue) {
        float density = getContext().getResources().getDisplayMetrics().scaledDensity;
        return spValue * density;
    }

    @SuppressWarnings({"unused", "SameParameterValue"})
    private float dp2px(float dpValue) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return dpValue * density;
    }

    @SuppressWarnings("unused")
    private static float dp2px(float dpValue, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return dpValue * density;
    }

    private int getMiddleColor(int startColor, int endColor) {
        return getColorFrom(startColor, endColor, 0.5f);
    }

    private int getColorFrom(int startColor, int endColor, @SuppressWarnings("SameParameterValue") float radio) {
        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);
        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);

        int red = (int) (redStart + ((redEnd - redStart) * radio + 0.5));
        int greed = (int) (greenStart + ((greenEnd - greenStart) * radio + 0.5));
        int blue = (int) (blueStart + ((blueEnd - blueStart) * radio + 0.5));
        return Color.argb(255, red, greed, blue);
    }

}
