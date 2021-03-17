package com.chris.eban.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import timber.log.Timber

class PinEditTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var pointNum: Int = 6
    private var inputNum: Int = 0

    init {
        paint.color = Color.GRAY
        paint.strokeWidth = 20f
        paint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        val pointGap = measuredWidth / (pointNum + 1)
        for (i in 1..pointNum) {
            if (i <= inputNum) {
                paint.color = Color.parseColor("#789123")
                canvas?.drawPoint((pointGap * i).toFloat(), (measuredHeight / 2).toFloat(), paint)
            } else {
                paint.color = Color.GRAY
                canvas?.drawPoint((pointGap * i).toFloat(), (measuredHeight / 2).toFloat(), paint)
            }
        }
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        Timber.e("onTextChanged: $text")
        inputNum = text?.length ?: 0
    }
}