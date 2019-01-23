package com.chris.eban.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.chris.eban.R;

import androidx.annotation.Nullable;

public class SwitchView extends View {

    private static final String TAG = "SwitchView";
    private Drawable iconStartOpen;
    private Drawable iconStartClose;
    private Drawable iconEndOpen;
    private Drawable iconEndClose;
    private Drawable bgDrawable;
    private int measureWidth;
    private int measureHeight;
    //    public Shader iconStartShader;
    private RectF rect = new RectF();
    private float radius;
    private Paint paint;
    private Rect iconStartBounds = new Rect();
    private Rect iconEndBounds = new Rect();
    //    private Paint iconStartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private GestureDetector mDetector;
    private boolean isOpen;
    private int iconWidth;
    private Path roundIconPath = new Path();
    private Path bgPath = new Path();

    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.CYAN);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchView);

        iconStartOpen = typedArray.getDrawable(R.styleable.SwitchView_sv_startIconOpen);
        iconStartClose = typedArray.getDrawable(R.styleable.SwitchView_sv_startIconClose);
        iconEndOpen = typedArray.getDrawable(R.styleable.SwitchView_sv_endIconOpen);
        iconEndClose = typedArray.getDrawable(R.styleable.SwitchView_sv_endIconClose);
        bgDrawable = typedArray.getDrawable(R.styleable.SwitchView_sv_bg);
        isOpen = typedArray.getBoolean(R.styleable.SwitchView_sv_selected, true);

        checkDrawable();

        mDetector = new GestureDetector(context, new DragListener());

        typedArray.recycle();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w == oldw && h == oldh) return;
        measureWidth = w;
        measureHeight = h;
        bgPath.reset();
        bgPath.addRoundRect(0, 0, measureWidth, measureHeight, measureHeight / 2, measureHeight / 2, Path.Direction.CW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        rect.set(0, 0, measureWidth, measureHeight);
        int min = Math.min(measureHeight, measureWidth);
        iconStartBounds.set(0, 0, min, min);
        iconEndBounds.set(measureWidth - min, 0, measureWidth, min);
        radius = min / 2;
        iconWidth = min;
        bgPath.addRoundRect(0, 0, measureWidth, measureHeight, measureHeight / 2, measureHeight / 2, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawRoundRect(rect, radius, radius, paint);

        canvas.save();
        canvas.clipPath(bgPath);
        bgDrawable.setBounds(0, 0, measureWidth, measureHeight);
        bgDrawable.draw(canvas);
        canvas.restore();

        if (isOpen) {
            canvas.save();
            roundIconPath.reset();
            roundIconPath.addCircle((iconStartBounds.left + iconStartBounds.right) / 2,
                    (iconStartBounds.top + iconStartBounds.bottom) / 2,
                    iconWidth / 2, Path.Direction.CW);
            canvas.clipPath(roundIconPath);
            iconStartOpen.setBounds(iconStartBounds);
            iconStartOpen.draw(canvas);
            canvas.restore();

            canvas.save();
            roundIconPath.reset();
            roundIconPath.addCircle((iconEndBounds.left + iconEndBounds.right) / 2,
                    (iconEndBounds.top + iconEndBounds.bottom) / 2,
                    iconWidth / 2, Path.Direction.CW);
            canvas.clipPath(roundIconPath);
            iconEndClose.setBounds(iconEndBounds);
            iconEndClose.draw(canvas);
            canvas.restore();
        } else {
            canvas.save();
            roundIconPath.reset();
            roundIconPath.addCircle((iconStartBounds.left + iconStartBounds.right) / 2,
                    (iconStartBounds.top + iconStartBounds.bottom) / 2,
                    iconWidth / 2, Path.Direction.CW);
            canvas.clipPath(roundIconPath);
            iconStartClose.setBounds(iconStartBounds);
            iconStartClose.draw(canvas);
            canvas.restore();

            canvas.save();
            roundIconPath.reset();
            roundIconPath.addCircle((iconEndBounds.left + iconEndBounds.right) / 2,
                    (iconEndBounds.top + iconEndBounds.bottom) / 2,
                    iconWidth / 2, Path.Direction.CW);
            canvas.clipPath(roundIconPath);
            iconEndOpen.setBounds(iconEndBounds);
            iconEndOpen.draw(canvas);
            canvas.restore();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = false;
        if (isInRange(event.getX())) {
            result = mDetector.onTouchEvent(event);
            if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                dispatchUpEvent();
                updateSelectedState(event.getX());
            }
        }
        return result;
    }

    private boolean isInRange(float x) {
        int left = getLeft() + getPaddingLeft();
        int right = getRight() - getPaddingRight();
        return x > left && x < right;
    }

    private void dispatchUpEvent() {

    }

    private void checkDrawable() {
        if (iconStartOpen == null)
            iconStartOpen = new ColorDrawable(Color.BLUE);

        if (iconStartClose == null)
            iconStartClose = new ColorDrawable(Color.MAGENTA);

        if (iconEndOpen == null)
            iconEndOpen = new ColorDrawable(Color.MAGENTA);

        if (iconEndClose == null)
            iconEndClose = new ColorDrawable(Color.BLUE);

        if (bgDrawable == null)
            bgDrawable = new ColorDrawable(Color.LTGRAY);
    }

    private void updateSelectedState(float endUpX) {
        Log.e(TAG, "updateSelectedState: " + endUpX);
        isOpen = endUpX > measureWidth / 2;
        resetBounds();
        invalidate();
    }

    private void resetBounds() {
        iconStartBounds.set(0, 0, iconWidth, iconWidth);
        iconEndBounds.set((measureWidth - iconWidth), 0, measureWidth, iconWidth);
    }

    private boolean isStartIconDrag(float touchX) {
        int left = getLeft() + getPaddingLeft();
        float iconRight = left + iconWidth;
        return touchX > left && touchX < iconRight;
    }

    private void updateStartIcon(float distanceX) {
        Log.d(TAG, "updateStartIcon: " + distanceX);
        int left = iconStartBounds.left;
//        int right = iconStartBounds.right;
        int top = iconStartBounds.top;
        int bottom = iconStartBounds.bottom;
        int newLeft = Math.max(0, (int) (left - distanceX));
        newLeft = Math.min(measureWidth - iconWidth, newLeft);
        iconStartBounds.set(newLeft, top, newLeft + iconWidth, bottom);
        invalidate();
    }

    private boolean isEndIconDrag(float touchX) {
        int right = getRight() - getPaddingRight();
        float iconLeft = right - iconWidth;
        return touchX > iconLeft && touchX < right;
    }

    private void updateEndIcon(float distanceX) {
        Log.d(TAG, "updateEndIcon: " + distanceX);
        int left = iconEndBounds.left;
//        int right = iconEndBounds.right;
        int top = iconEndBounds.top;
        int bottom = iconEndBounds.bottom;
        int newLeft = Math.max(0, (int) (left - distanceX));
        newLeft = Math.min(measureWidth - iconWidth, newLeft);
        iconEndBounds.set(newLeft, top, newLeft + iconWidth, bottom);
        invalidate();
    }

    private class DragListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(TAG, "onDown: " + e);
//            float x = e.getX();
//            return isStartIconDrag(e.getX()) || isEndIconDrag(e.getX()) || super.onDown(e);
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
//            super.onShowPress(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d(TAG, "onSingleTapUp: " + e);
            updateSelectedState(e.getX());
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TAG, "onScroll: " + e1);
            if (isStartIconDrag(e1.getX())) {
                // touch view is the start icon
                updateStartIcon(distanceX);
                return true;
            } else if (isEndIconDrag(e1.getX())) {
                updateEndIcon(distanceX);
                return true;
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public void onLongPress(MotionEvent e) {
//            super.onLongPress(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            return super.onFling(e1, e2, velocityX, velocityY);
            return false;
        }
    }
}
