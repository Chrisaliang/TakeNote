package com.chris.takenote;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;

public class ProgressView extends View {

    private Paint progressPaint;
    private float sx, sy, ex, ey, px, py;
    @ColorInt
    private int progressColor;
    private RectF rect = new RectF();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int progress = 25;

    private float scale_length = 20;
    private float scale_margin = 5;

    /**
     * 进度最小值 默认0
     */
    private static final int PROGRESS_MIN_VALUE = 0;
    /**
     * 进度最大值 默认100
     */
    private static final int PROGRESS_MAX_VALUE = 100;

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
        progressColor = Color.BLACK;
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(progressColor);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        sx = getMeasuredWidth() / 2;
        sy = scale_margin;
        ex = sx;
        ey = scale_length;

        px = sx;
        py = px;
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(rect, paint);

        drawProgress(canvas);
    }

    private void drawProgress(Canvas canvas) {
        progressPaint.setStrokeWidth(2);
        canvas.save();
        for (int i = 0; i < 100; i++) {
            if (i == progress)
                canvas.drawLine(sx, sy, ex, ey + scale_length / 2, progressPaint);
            else if (i == progress + 1 || i == progress - 1)
                canvas.drawLine(sx, sy, ex, ey + scale_length / 4, progressPaint);
            else
                canvas.drawLine(sx, sy, ex, ey, progressPaint);
            canvas.rotate(3.6f, px, py);
        }
        canvas.restore();
    }
}
