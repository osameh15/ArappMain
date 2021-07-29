package ir.arapp.arappmain.util.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gamapp.blurcardview.BlurCardView
import com.google.android.material.card.MaterialCardView
import com.makeramen.roundedimageview.RoundedImageView
import ir.arapp.arappmain.R
import ir.arapp.arappmain.model.base.Profile
import ir.arapp.arappmain.util.adapters.ProfileItemAdapter.ProfileItemViewHolder
import ir.arapp.arappmain.util.services.ItemClickListener
import java.util.*

class ProfileItemAdapter     //    endregion
//    region Methods
//    Constructor
    (  //    region Variables
    private val view: View
) : RecyclerView.Adapter<ProfileItemViewHolder>() {
    var itemClickListener: ItemClickListener? = null
    var profileArrayList: ArrayList<Profile>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
//        Inflate Layout
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_profile_container, parent, false)
        //        Return view
        return ProfileItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) {
//        get current item in category
        val currentItem = profileArrayList!![position]
        //        Set data and update xml
        holder.roundedImageView.setImageResource(currentItem.image)
        holder.name.text = currentItem.name
        holder.cardView.setOnClickListener { view: View? ->
            itemClickListener!!.onItemClickListener(
                this.view,
                currentItem.id,
                currentItem.name
            )
        }
    }

    override fun getItemCount(): Int {
        return profileArrayList!!.size
    }

    //    Notify Data changed

    //    endregion
    //    View holder class
    class ProfileItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        region Variables
        var cardView: MaterialCardView
        var roundedImageView: RoundedImageView
        var name: TextView

        //        endregion
        //        Constructor
        init {
            //            Hooks
            cardView = itemView.findViewById(R.id.profileCardView)
            roundedImageView = itemView.findViewById(R.id.profileImageContainer)
            name = itemView.findViewById(R.id.profileNameContainer)
        }
    }
}