package ir.arapp.arappmain.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentNotificationBinding
import ir.arapp.arappmain.model.Notification
import ir.arapp.arappmain.adapters.NotificationAdapter
import ir.arapp.arappmain.util.services.NavigationManager
import ir.arapp.arappmain.viewmodel.NotificationViewModel
import java.util.*

class NotificationFragment : Fragment() {
    //    region Variable
    var _fragmentNotificationBinding: FragmentNotificationBinding? = null
    val fragmentNotificationBinding get() = _fragmentNotificationBinding!!
    private var notificationAdapter: NotificationAdapter? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentNotificationBinding = null
    }
    //    endregion
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentNotificationBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        //        Set view model
        val notificationViewModel = ViewModelProvider(requireActivity()).get(
            NotificationViewModel::class.java
        )
        //        Hooks
        notificationAdapter = NotificationAdapter()
        //        Set life cycle
        fragmentNotificationBinding.setLifecycleOwner(this)
        //        Recycler View notification items
        notificationViewModel.allNotificationList.observe(
            viewLifecycleOwner,
            { notifications: ArrayList<Notification> ->
                notificationAdapter?.notificationArrayList = notifications
                setRecyclerView()
            })
        //        Drawer Locked and visible Bottom navigation
        (requireActivity() as NavigationManager).setDrawerLocked(true)
        (requireActivity() as NavigationManager).bottomNavigationVisibility(true)
        //        Return view
        return fragmentNotificationBinding.getRoot()
    }

    //    region methods
    //    Set notification adapter and layout manager
    private fun setRecyclerView() {
        fragmentNotificationBinding!!.notificationRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        fragmentNotificationBinding!!.notificationRecyclerView.setHasFixedSize(true)
        fragmentNotificationBinding!!.notificationRecyclerView.adapter = notificationAdapter
    } //    endregion
}