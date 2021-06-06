package ir.arapp.arappmain.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import ir.arapp.arappmain.R

class SpinnerAdapter : ArrayAdapter<String?> {
    //    region Variables
    private val typeface: Typeface?

    //    endregion
    //    Constructor
    constructor(context: Context, resource: Int) : super(context, resource) {
        typeface = ResourcesCompat.getFont(context, R.font.iransans_bold)
    }

    constructor(context: Context, resource: Int, objects: List<String?>) : super(
        context,
        resource,
        0,
        objects
    ) {
        typeface = ResourcesCompat.getFont(context, R.font.iransans_bold)
    }

    //    region Methods
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        (view as TextView).typeface = typeface
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        (view as TextView).typeface = typeface
        return view
    }

    override fun setDropDownViewResource(resource: Int) {
        super.setDropDownViewResource(resource)
    }

    override fun getPosition(item: String?): Int {
        return super.getPosition(item)
    } //    endregion
}