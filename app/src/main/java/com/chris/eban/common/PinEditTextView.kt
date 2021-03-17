package com.chris.eban.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText

@SuppressLint("LogNotTimber")
class PinEditTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0)
    : AppCompatEditText(context, attrs, defStyle), TextWatcher {

    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var pointNum: Int = 6
    private var inputNum: Int = 3

    init {
        paint.color = Color.GRAY
        paint.strokeWidth = 20f
        paint.strokeCap = Paint.Cap.ROUND
        addTextChangedListener(this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val pointGap = measuredWidth / (pointNum + 1)

        paint.color = Color.GRAY
        for (i in 1..pointNum)
            canvas?.drawPoint((pointGap * i).toFloat(), (measuredHeight / 2).toFloat(), paint)
        paint.color = Color.RED
        if (inputNum > pointNum) inputNum = pointNum
        for (i in 1..inputNum)
            canvas?.drawPoint((pointGap * i).toFloat(), (measuredHeight / 2).toFloat(), paint)

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        Log.e(TAG, "onTextChanged: $text")
    }

    override fun afterTextChanged(s: Editable?) {
        Log.e(TAG, "afterTextChanged: $s")
        inputNum = s?.length!!
        invalidate()
    }


    companion object {
        private const val TAG = "PinEditTextView"
    }
}