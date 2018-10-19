package com.chris.takenote.demo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.chris.takenote.R;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

public class DemoProgressView extends View {

    /**
     * Interpolator used for smooth progress animations.
     */
    private static final AccelerateDecelerateInterpolator PROGRESS_ANIM_INTERPOLATOR =
            new AccelerateDecelerateInterpolator();

    /**
     * Duration of smooth progress animations.
     */
    private static final int PROGRESS_ANIM_DURATION = 80;

    /**
     * this view's width and height
     **/
    private int viewWH;
    /**
     * the outer circle paint
     */
    private Paint outerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * the inner circle paint
     */
    private Paint innerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * view's center
     */
    private float centerX, centerY;
    /**
     * the outer circle radius
     */
    private float outerRadius;
    /**
     * the inner circle radius
     */
    private float innerRadius;
    /**
     * the progress's background paint
     */
    private Paint progressBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * the progress paint
     */
    private Paint progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * the progress value
     */
    private int progress;

    private float startOffsetDegrees;

    public DemoProgressView(Context context) {
        this(context, null);
    }

    public DemoProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DemoProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DemoProgressView);

        int circleColor = typedArray.getColor(R.styleable.DemoProgressView_pv_circleColor, Color.GRAY);

        outerCirclePaint.setColor(circleColor);
        outerCirclePaint.setStyle(Paint.Style.STROKE);
        outerCirclePaint.setStrokeWidth(3);
        innerCirclePaint.setColor(circleColor);
        innerCirclePaint.setStyle(Paint.Style.STROKE);
        innerCirclePaint.setStrokeWidth(8);

        MaskFilter maskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL);
        innerCirclePaint.setMaskFilter(maskFilter);

        int progressBgColor = typedArray.getColor(R.styleable.DemoProgressView_pv_progressBgColor, Color.DKGRAY);

        progressBgPaint.setColor(progressBgColor);
        progressBgPaint.setStrokeWidth(5);

        int progressColor = typedArray.getColor(R.styleable.DemoProgressView_pv_progressColor, Color.BLUE);

        progressPaint.setColor(progressColor);
        progressPaint.setStrokeWidth(5);

        progress = typedArray.getInt(R.styleable.DemoProgressView_dpv_progress, 0);
        startOffsetDegrees = typedArray.getFloat(R.styleable.DemoProgressView_pv_startOffsetDegrees, 0);

        checkValue();

        typedArray.recycle();
    }

    public DemoProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void startAnimate() {
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "progress", 0, this.progress);
//        animator.setDuration(3000);
        animator.setInterpolator(PROGRESS_ANIM_INTERPOLATOR);
        animator.start();
    }

    @Keep
    public int getProgress() {
        return progress;
    }

    @Keep
    public synchronized void setProgress(int progress) {
        if (this.progress == progress) return;
        this.progress = progress;
        invalidate();
    }

    private void checkValue() {
        if (progress < 0) progress = 0;
        if (progress > 100) progress = 100;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w == oldw && h == oldh) return;
        viewWH = Math.min(w, h);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int paddingStart = getPaddingStart();
        int paddingEnd = getPaddingEnd();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
//        centerX = (width - paddingStart - paddingEnd) /2;
//        centerY = (height - paddingTop - paddingBottom)/2;
        centerX = width / 2;
        centerY = height / 2;
        viewWH = Math.min((width - paddingStart - paddingEnd), (height - paddingTop - paddingBottom));
//        if (width>height)
        outerRadius = viewWH / 2 - 10;
        innerRadius = outerRadius - 10;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.rotate(startOffsetDegrees, centerX, centerY);

        drawBackground(canvas);

        drawProgress(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimate();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        startAnimate();
    }

    private void drawProgress(Canvas canvas) {
        canvas.save();
        // TODO: 2018/4/27 make sure that draw the first progress there
//        if (this.progress > 0)
//            canvas.drawLine(centerX, centerY - innerRadius + 10, centerX, centerY - innerRadius + 50, progressPaint);
        for (int i = 0; i < progress; ++i) {
            canvas.rotate(3.6f, centerX, centerY);
            canvas.drawLine(centerX, centerY - innerRadius + 10, centerX, centerY - innerRadius + 50, progressPaint);
        }
        canvas.restore();
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, outerRadius, outerCirclePaint);
        canvas.drawCircle(centerX, centerY, innerRadius, innerCirclePaint);

        canvas.save();
//        canvas.translate(centerX, centerY);
        for (int i = 0; i < 100; ++i) {
            canvas.drawLine(centerX, centerY - innerRadius + 10, centerX, centerY - innerRadius + 50, progressBgPaint);
            canvas.rotate(3.6f, centerX, centerY);
        }
        canvas.restore();
    }
}
