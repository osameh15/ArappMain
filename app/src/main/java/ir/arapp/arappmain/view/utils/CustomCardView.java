package ir.arapp.arappmain.view.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;


import com.google.android.material.card.MaterialCardView;

import ir.arapp.arappmain.R;

public class CustomCardView extends MaterialCardView {

    private boolean canChange = true;
    public float getCornerRadiusByPercent() {
        return cornerRadiusByPercent;
    }

    //value must be between 0f..1f
    public void setCornerRadiusByPercent(float cornerRadiusByPercent) {
        this.cornerRadiusByPercent = cornerRadiusByPercent;
        postInvalidate();
    }

    void changeCornerSize(){
        canChange = true;
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
        cornerRadiusByPercent = typedArray.getFloat(R.styleable.CustomCardView_cardCornerRadiusByPercent, -1f);
        typedArray.recycle();
        changeCornerSize();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        changeCornerSize();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            if (canChange){
                int width = this.getWidth();
                int height = this.getHeight();
                if (cornerRadiusByPercent >= 0 && cornerRadiusByPercent <= 1)
                    this.setRadius((float) (Math.min(width , height) * cornerRadiusByPercent));
                canChange = false;
            }

        }
    }



}
