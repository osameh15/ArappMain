package ir.arapp.arappmain.view.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import ir.arapp.arappmain.R

class RateNumberView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var view: CustomCardView = LayoutInflater.from(context)
        .inflate(R.layout.rate_number_layout, this, false)
            as CustomCardView

    private lateinit var textView: TextView

    var rate: Double = 0.0
        set(value) {
            field = value
            if (rate == 0.0) {
                textView.text = "-"
                view.setCardBackgroundColor(Color.rgb(100, 100, 100))
            } else if (rate < 2) {
                textView.text = rate.toString()
                view.setCardBackgroundColor(Color.rgb(160,0,60))
            } else if (rate < 4) {
                textView.text = rate.toString()
                view.setCardBackgroundColor(Color.rgb(160,160,50))
            } else if (rate <= 5) {
                textView.text = rate.toString()
                view.setCardBackgroundColor(Color.rgb(0,160,100))
            }
        }

    init {
        addView(view)
        this.background = ColorDrawable(Color.TRANSPARENT)
        textView = view.findViewById(R.id.rate_number_layout_rate_text_view)
        rate = 0.0
    }


}