package ir.arapp.arappmain.view.utils

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class SingleDirectionRecyclerView : RecyclerView {
    var enforcer = SingleScrollDirectionEnforcer()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    fun init() {
        addOnScrollListener(enforcer)
        addOnItemTouchListener(enforcer)
    }
}