package com.chris.eban.demo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.chris.eban.R;

import java.util.Locale;

@BindingMethods({
        @BindingMethod(type = DriverManagerProgressView.class, attribute = "pv_progress", method = "setPvProgress"),
        @BindingMethod(type = DriverManagerProgressView.class, attribute = "pv_item_title", method = "setPvItemTitle")
})
public class DriverManagerProgressView extends View {

//    private static final String TAG = "DriverManagerProgressVi";

    private static final boolean DBG = false;
    /**
     * 进度最小值 默认0
     */
    private static final int PROGRESS_MIN_VALUE = 0;
    /**
     * 进度最大值 默认100
     */
    private static final int PROGRESS_MAX_VALUE = 100;
    /**
     * 默认标题字体大小 单位 sp
     */
    private static final int defaultTextSizeValue = 30;
    /**
     * 颜色过滤器
     */
    PorterDuffColorFilter colorFilter;
    /**
     * 标题
     */
    private String itemTitle;
    private Paint itemTitleTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint itemMoreTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private float itemTitleX;
    private float itemTitleY;
    private float itemMoreX;
    /**
     * 进度背景drawable
     */
    private Drawable progressBgDrawable;
    /**
     * 进度条drawable
     */
    private Drawable progressSrcDrawable;
    /**
     * 进度背景边界
     */
    private Rect drawableBgBounds = new Rect();
    /**
     * 进度条边界
     */
    private Rect drawableSrcBounds = new Rect();
    /**
     * 进度
     */
    private int progress;
    /**
     * 进度drawable和背景drawable间距padding
     */
    private int drawablePadding = 5;
    /**
     * 标题文字的边界
     */
    private Rect textBounds = new Rect();
    /**
     * 字符偏移量
     */
    private float textOffset = 50;
    /**
     * 偏移因子 让进度条占有view宽度的百分比
     */
    private float offsetFactor = 0.25f;


    public DriverManagerProgressView(Context context) {
        this(context, null);
    }

    public DriverManagerProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public DriverManagerProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.DriverManagerProgressView);

        float titleTextSize = t.getDimension(R.styleable.DriverManagerProgressView_pv_title_text_size, sp2px(defaultTextSizeValue));
        int titleTextColor = t.getColor(R.styleable.DriverManagerProgressView_pv_title_text_color, Color.parseColor("#FFFEFEFE"));

        float progressTextSize = t.getDimension(R.styleable.DriverManagerProgressView_pv_progress_text_size, sp2px(defaultTextSizeValue));
        int progressTextColor = t.getColor(R.styleable.DriverManagerProgressView_pv_progress_text_color, Color.parseColor("#FF0299E8"));

        itemTitleTextPaint.setTextSize(titleTextSize);
        itemTitleTextPaint.setColor(titleTextColor);

        itemMoreTextPaint.setTextSize(progressTextSize);
        itemMoreTextPaint.setColor(progressTextColor);

        progressBgDrawable = t.getDrawable(R.styleable.DriverManagerProgressView_pv_progress_bg);
        progressSrcDrawable = t.getDrawable(R.styleable.DriverManagerProgressView_pv_progress_src);
        progress = t.getInt(R.styleable.DriverManagerProgressView_mpv_progress, 0);

        drawablePadding = t.getDimensionPixelSize(
                R.styleable.DriverManagerProgressView_pv_progress_drawable_padding, drawablePadding);

        itemTitle = t.getString(R.styleable.DriverManagerProgressView_pv_item_title);

        offsetFactor = t.getFloat(R.styleable.DriverManagerProgressView_pv_offset_factor, offsetFactor);
        textOffset = t.getFloat(R.styleable.DriverManagerProgressView_pv_text_offset, textOffset);


        checkDrawable();

        checkValue();

        t.recycle();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DriverManagerProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Keep
    @SuppressWarnings("unused")
    @BindingAdapter(value = "pv_progress")
    public static void setPvProgress(DriverManagerProgressView progressView, int progress) {
        progressView.setProgress(progress);
    }

    @Keep
    @SuppressWarnings("unused")
    @BindingAdapter(value = "pv_item_title")
    public static void setPvItemTitle(DriverManagerProgressView progressView, String itemTitle) {
        progressView.setPvItemTitle(itemTitle);
    }

    @Keep
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    @Keep
    public void setPvItemTitle(String title) {
        itemTitle = title;
        invalidate();
    }

    private float sp2px(float spValue) {
        float density = getContext().getResources().getDisplayMetrics().scaledDensity;
        return spValue * density;
    }

    private void checkValue() {
        if (progress < PROGRESS_MIN_VALUE) progress = PROGRESS_MIN_VALUE;
        if (progress > PROGRESS_MAX_VALUE) progress = PROGRESS_MAX_VALUE;

        if (TextUtils.isEmpty(itemTitle))
            itemTitle = "急加速";

        if (progress < 33)
            colorFilter = new PorterDuffColorFilter(Color.parseColor("#13EB15"), PorterDuff.Mode.MULTIPLY);
        else if (progress < 67)
            colorFilter = new PorterDuffColorFilter(Color.parseColor("#FB8003"), PorterDuff.Mode.MULTIPLY);
        else
            colorFilter = new PorterDuffColorFilter(Color.parseColor("#FB0101"), PorterDuff.Mode.MULTIPLY);

    }

    private void checkDrawable() {
        if (progressBgDrawable == null) {
            progressBgDrawable = new ColorDrawable(Color.BLACK);
        }
        if (progressSrcDrawable == null) {
//            progressSrcDrawable = new NinePatchDrawable();
            progressSrcDrawable = new ColorDrawable(Color.WHITE);
//            return;
        }
        progressSrcDrawable.setFilterBitmap(true);

        progressSrcDrawable.setCallback(this);
        progressBgDrawable.setCallback(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            int textHeight;

            if (TextUtils.isEmpty(itemTitle))
                textHeight = 1;
            else {
                itemTitleTextPaint.getTextBounds(itemTitle, 0, itemTitle.length(), textBounds);
                textHeight = textBounds.bottom - textBounds.top;
            }

            setMeasuredDimension(getMeasuredWidth(), textHeight * 2);
//        } else {
        }

        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int left = (int) (measuredWidth * offsetFactor);
        int right = (int) (measuredWidth * (1 - offsetFactor));
        itemTitleY = measuredHeight;
        drawableBgBounds.set(left, 0, right, measuredHeight / 2);
        drawableSrcBounds.set(left + drawablePadding,
                drawablePadding,
                (left + (right - left) * progress / 100) - drawablePadding,
                measuredHeight - drawablePadding);

        itemTitleX = measuredWidth * offsetFactor - textOffset;
        itemMoreX = measuredWidth * (1 - offsetFactor) + textOffset;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawProgress(canvas);

        drawText(canvas);
    }

    private void drawText(Canvas canvas) {

        itemTitleTextPaint.setTextAlign(Paint.Align.RIGHT);
        itemTitleTextPaint.getTextBounds(itemTitle, 0, itemTitle.length(), textBounds);
        itemTitleY = (getMeasuredHeight() / 2 - (textBounds.top + textBounds.bottom)) / 2;
        canvas.drawText(itemTitle, itemTitleX, itemTitleY, itemTitleTextPaint);

        if (DBG) {
            itemMoreTextPaint.setStrokeWidth(10);
            canvas.drawLine(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight() / 2, itemMoreTextPaint);
        }

        itemTitleTextPaint.setTextAlign(Paint.Align.LEFT);
        String itemMore = "超";
        canvas.drawText(itemMore, itemMoreX, itemTitleY, itemTitleTextPaint);
        String itemMoreProgress = String.format(Locale.CHINA, "%d%%", progress);
        canvas.drawText(itemMoreProgress, itemMoreX + itemTitleTextPaint.measureText(itemMore) + 10,
                itemTitleY, itemMoreTextPaint);
        String itemMoreUser = "车主";
        canvas.drawText(itemMoreUser, itemMoreX + itemTitleTextPaint.measureText(itemMore) + 10
                        + itemMoreTextPaint.measureText(itemMoreProgress) + 10,
                itemTitleY, itemTitleTextPaint);
    }


    private void drawProgress(Canvas canvas) {
//        Timber.tag(TAG).d(drawableBgBounds.right);
        computerBounds();
        progressBgDrawable.setBounds(drawableBgBounds);
        progressBgDrawable.draw(canvas);

        progressSrcDrawable.setBounds(drawableSrcBounds);
        progressSrcDrawable.setColorFilter(colorFilter);
        progressSrcDrawable.draw(canvas);
    }

    private void computerBounds() {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int left = (int) (measuredWidth * offsetFactor);
        int right = (int) (measuredWidth * (1 - offsetFactor));
        drawableBgBounds.set(left, 0, right, measuredHeight / 2);
        drawableSrcBounds.set(left + drawablePadding,
                drawablePadding,
                (left + (right - left) * progress / 100) - drawablePadding,
                measuredHeight - drawablePadding);
        checkValue();
    }
}
