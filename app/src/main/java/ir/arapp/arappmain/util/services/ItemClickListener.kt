package ir.arapp.arappmain.util.services

import android.view.View

interface ItemClickListener {
    //    Adapter Item click listener
    fun onItemClickListener(view: View?, position: Int, message: String?)
}