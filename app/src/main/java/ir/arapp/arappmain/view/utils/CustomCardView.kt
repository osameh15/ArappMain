package ir.arapp.arappmain.view.utils

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import ir.arapp.arappmain.R

class CustomCardView : MaterialCardView {
    private var canChange = true

    //value must be between 0f..1f


    fun changeCornerSize() {
        canChange = true
        postInvalidate()
    }

    var cornerRadiusByPercent = -1f
    set(value) {
        field = value
        postInvalidate()
    }

    //    public float getCornerRadiusBySize() {
    //        return cornerRadiusBySize;
    //    }
    //
    //    public void setCornerRadiusBySize(float cornerRadiusBySize) {
    //        this.cornerRadiusBySize = cornerRadiusBySize;
    //        postInvalidate();
    //    }
    //
    //    float cornerRadiusBySize = 0f;
    constructor(context: Context?) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCardView)
        cornerRadiusByPercent =
            typedArray.getFloat(R.styleable.CustomCardView_cardCornerRadiusByPercent, -1f)
        typedArray.recycle()
        changeCornerSize()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        changeCornerSize()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (canvas != null) {
            if (canChange) {
                val width = this.width
                val height = this.height
                if (cornerRadiusByPercent >= 0 && cornerRadiusByPercent <= 1) this.radius =
                    (Math.min(width, height) * cornerRadiusByPercent)
                canChange = false
            }
        }
    }
}