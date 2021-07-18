package ir.arapp.arappmain.util.adapters.services

import androidx.recyclerview.widget.RecyclerView
import ir.arapp.arappmain.util.adapters.services.CategoryItemAdapter.CategoryItemViewHolder
import ir.arapp.arappmain.util.services.ItemClickListener
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import ir.arapp.arappmain.R
import com.google.android.material.card.MaterialCardView
import com.makeramen.roundedimageview.RoundedImageView
import ir.arapp.arappmain.model.base.Category
import java.util.ArrayList

class CategoryItemAdapter     //    endregion
//    region Methods
//    Constructor
    (  //    region Variable
    private val view: View
) : RecyclerView.Adapter<CategoryItemViewHolder>() {
    private var categories = ArrayList<Category>()
    var itemClickListener: ItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
//        Inflate Layout
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_category_item_container, parent, false)
        return CategoryItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
//        get current item in category
        val currentItem = categories[position]
        //        Set data and update xml
//        holder.roundedImageView.setImageResource(currentItem.picture)
        holder.cardView.setOnClickListener { view: View? ->
            itemClickListener!!.onItemClickListener(
                this.view,
                currentItem.id?:0,
                currentItem.title
            )
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    //    Notify Data changed
    fun setCategories(categories: ArrayList<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    //    endregion
    //    View holder class
    class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        region Variable
        var cardView: MaterialCardView
        var roundedImageView: RoundedImageView

        //            endregion
        //        Constructor to View holder
        init {
            //            Hooks
            cardView = itemView.findViewById(R.id.cardViewCategoryItemContainer)
            roundedImageView = itemView.findViewById(R.id.categoryItemContainer)
        }
    }
}