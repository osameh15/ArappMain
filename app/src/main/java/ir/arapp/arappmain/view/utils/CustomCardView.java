package ir.arapp.arappmain.view.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;


import com.google.android.material.card.MaterialCardView;

import ir.arapp.arappmain.R;

public class CustomCardView extends MaterialCardView {


    public float getCornerRadiusByPercent() {
        return cornerRadiusByPercent;
    }

    //value must be between 0f..1f
    public void setCornerRadiusByPercent(float cornerRadiusByPercent) {
        this.cornerRadiusByPercent = cornerRadiusByPercent;
        postInvalidate();
    }

    float cornerRadiusByPercent = -1f;

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


    public CustomCardView(Context context) {
        super(context);
    }

    public CustomCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCardView);
//        cornerRadiusBySize = typedArray.getDimension(R.styleable.CustomCardView_cardCornerRadiusBySize,0f);
        cornerRadiusByPercent = typedArray.getFloat(R.styleable.CustomCardView_cardCornerRadiusByPercent, -1f);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            int width = this.getWidth();
            int height = this.getHeight();
            if (cornerRadiusByPercent >= 0 && cornerRadiusByPercent <= 1)
                this.setRadius((float) (Math.min(width , height) * cornerRadiusByPercent));

        }
    }



}
