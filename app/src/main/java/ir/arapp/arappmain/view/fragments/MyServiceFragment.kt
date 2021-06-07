package ir.arapp.arappmain.view.fragments

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentMyServiceBinding
import ir.arapp.arappmain.model.base.Service
import ir.arapp.arappmain.util.adapters.services.MyServiceAdapter
import ir.arapp.arappmain.util.services.ItemClickListener
import ir.arapp.arappmain.util.services.NavigationManager
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.ServicesViewModel
import java.util.*

class MyServiceFragment : Fragment(), ItemClickListener {
    //    region Variable
    var _fragmentMyServiceBinding: FragmentMyServiceBinding? = null
    val fragmentMyServiceBinding get() = _fragmentMyServiceBinding!!
    private var myServiceAdapter: MyServiceAdapter? = null
    private var snackBarToast: SnackBarToast? = null
    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentMyServiceBinding = null
    }
    //    endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentMyServiceBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_my_service, container, false)
        //        Set view model
        val servicesViewModel = ViewModelProvider(requireActivity()).get(
            ServicesViewModel::class.java
        )
        //        Hooks
        myServiceAdapter = MyServiceAdapter(fragmentMyServiceBinding.getRoot())
        snackBarToast = SnackBarToast(fragmentMyServiceBinding.getRoot())
        myServiceAdapter!!.itemClickListener = this
        //        Set life cycle
        fragmentMyServiceBinding.setLifecycleOwner(this)
        //        Drawer Locked and visible Bottom navigation
        (requireActivity() as NavigationManager).setDrawerLocked(true)
        (requireActivity() as NavigationManager).bottomNavigationVisibility(false)
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentMyServiceBinding.profileToolbar)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentMyServiceBinding.profileToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Recycler View Services items
        servicesViewModel.allCategoryItems.observe(
            viewLifecycleOwner,
            { services: ArrayList<Service> ->
                myServiceAdapter?.services = services
                setRecyclerView()
            })
        //        Return view
        return fragmentMyServiceBinding.getRoot()
    }

    //    region methods
    //    Option menu and manage it
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.setGroupVisible(R.id.bottomNavigationMenu, false)
    }

    //    Back button navigation
    private fun onNavigateUp() {
        requireActivity().onBackPressed()
    }

    //    Set notification adapter and layout manager
    private fun setRecyclerView() {
        fragmentMyServiceBinding!!.myServiceRecyclerView.setHasFixedSize(true)
        //        Add spacing between 2 item
        fragmentMyServiceBinding!!.myServiceRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                12,
                1
            )
        )
        fragmentMyServiceBinding!!.myServiceRecyclerView.adapter = myServiceAdapter
    }

    //    On click listener on each item
    override fun onItemClickListener(view: View?, position: Int, message: String?) {
        val navController = Navigation.findNavController(requireView())
        if (message == "edit") {
            navController.navigate(R.id.action_myServiceFragment_to_editServiceFragment)
        } else if (message == "delete") {
            deleteService()
        } else {
            snackBarToast!!.snackBarShortTime(message)
        }
    }

    private fun deleteService() {
        val TIME_LOADING = 2200
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.bottomSheetDialogTheme)
        @SuppressLint("InflateParams") val bottomSheetView = LayoutInflater.from(requireContext())
            .inflate(R.layout.custom_bottom_sheet_delete_service, null)
        //            View Hooks
        val deleteLinearLayout =
            bottomSheetView.findViewById<LinearLayout>(R.id.deleteServiceLinearLayout)
        val delete = bottomSheetView.findViewById<LinearLayout>(R.id.deleteServiceAction)
        val cancel = bottomSheetView.findViewById<LinearLayout>(R.id.cancelServiceAction)
        val loading: LottieAnimationView = bottomSheetView.findViewById(R.id.loading)
        //            Set on click listener
        delete.setOnClickListener { v: View? ->
            deleteLinearLayout.visibility = View.GONE
            loading.visibility = View.VISIBLE
            Handler().postDelayed({ bottomSheetDialog.dismiss() }, TIME_LOADING.toLong())
        }
        cancel.setOnClickListener { v: View? -> bottomSheetDialog.dismiss() }
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    //    endregion
    //    private inline class for spacing recycler view items
    private class GridSpacingItemDecoration     //        endregion
    //        Constructor
        (//        region Variables
        private val spanCount: Int, private val spacing: Int, private val spacing_top: Int
    ) : ItemDecoration() {
        private val includeEdge = true

        //        Get item offsets to set layout spacing
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
//             item phases_position
            val position = parent.getChildAdapterPosition(view)
            //             item column
            val column = position % spanCount
            if (includeEdge) {
//                 spacing - column * ((1f / spanCount) * spacing)
                outRect.left = spacing - column * spacing / spanCount
                //                 (column + 1) * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount
                if (position < spanCount) {
//                     top edge
                    outRect.top = spacing_top
                }
                //                 item bottom
                outRect.bottom = spacing_top
            } else {
//                 column * ((1f / spanCount) * spacing)
                outRect.left = column * spacing / spanCount
                //                 spacing - (column + 1) * ((1f /    spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount) {
//                     item top
                    outRect.top = spacing_top
                }
            }
        }
    }
}