package ir.arapp.arappmain.view.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import ir.arapp.arappmain.R

class AutoSizeTextView : AppCompatTextView {
    private var textSizeByPercent = 1f

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoSizeTextView)
        textSizeByPercent = typedArray.getFloat(R.styleable.AutoSizeTextView_textSizeByPercent, 1f)
        typedArray.recycle()
        init()
    }

    private fun init() {
        postInvalidate()
    }

    @SuppressLint("RestrictedApi")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (canvas != null) {
            var textLength = 1f
            if (text != null) {
                textLength = text.toString().length.toFloat()
            }
            setAutoSizeTextTypeUniformWithConfiguration(
                1,
                (width / textLength * textSizeByPercent).toInt(),
                1,
                TypedValue.COMPLEX_UNIT_PX
            )
        }
    }
}