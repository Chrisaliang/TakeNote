package com.chris.eban.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class LeonView extends View {

    private Paint paint;

    public LeonView(Context context) {
        this(context, null);
    }

    public LeonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public LeonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLACK);
        canvas.drawLine(40, 40, 40, 440, paint);
        canvas.drawLine(20, 420, 800, 420, paint);

        paint.setColor(Color.BLUE);
        canvas.drawRect(60, 300, 100, 420, paint);

        paint.setColor(Color.GREEN);
        canvas.drawRoundRect(120, 100, 160, 420, 10, 10, paint);

        paint.setColor(Color.YELLOW);
        canvas.drawRect(200, 100, 500, 400, paint);

        paint.setColor(Color.MAGENTA);
        canvas.drawArc(200, 100, 500, 400, -30, 120, true, paint);
    }
}
