package ir.arapp.arappmain.View.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import ir.arapp.arappmain.R;

public class AutoSizeTextView extends androidx.appcompat.widget.AppCompatTextView {
    private float textSizeByPercent = 1f;


    public AutoSizeTextView(@NonNull @NotNull Context context) {
        super(context);
        init();
    }


    public AutoSizeTextView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public AutoSizeTextView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AutoSizeTextView);

        textSizeByPercent = typedArray.getFloat(R.styleable.AutoSizeTextView_textSizeByPercent, 1f);

        typedArray.recycle();
        init();
    }

    private void init() {
        postInvalidate();
    }


    @SuppressLint("RestrictedApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            float textLength = 1f;
            if (getText() != null) {
                textLength = (float) (getText().toString().length());
            }
            this.setAutoSizeTextTypeUniformWithConfiguration(1, (int) (getWidth()/textLength * textSizeByPercent), 1, TypedValue.COMPLEX_UNIT_PX);
        }
    }
}
