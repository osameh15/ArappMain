package ir.arapp.arappmain.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentCategoryBinding
import ir.arapp.arappmain.model.Category
import ir.arapp.arappmain.adapters.services.CategoryItemAdapter
import ir.arapp.arappmain.util.services.ItemClickListener
import ir.arapp.arappmain.util.services.NavigationManager
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.CategoryViewModel
import java.util.*

class CategoryFragment : Fragment(), ItemClickListener {
    //    region Variable
    var _fragmentCategoryBinding: FragmentCategoryBinding? = null
    val fragmentCategoryBinding get() =  _fragmentCategoryBinding!!
    private var categoryItemAdapter: CategoryItemAdapter? = null
    private var snackBarToast: SnackBarToast? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentCategoryBinding = null
    }
    //    endregion
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentCategoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        //        Set View model
        val categoryViewModel = ViewModelProvider(requireActivity()).get(
            CategoryViewModel::class.java
        )
        //        Hooks
        categoryItemAdapter = CategoryItemAdapter(fragmentCategoryBinding.getRoot())
        snackBarToast = SnackBarToast(fragmentCategoryBinding.getRoot())
        categoryItemAdapter!!.itemClickListener = this
        //        Set life cycle
        fragmentCategoryBinding.setLifecycleOwner(this)
        //        Recycler view Category items
        categoryViewModel.allCategoryItems.observe(
            viewLifecycleOwner,
            { categoryItems: ArrayList<Category> ->
                categoryItemAdapter!!.setCategories(categoryItems)
                setRecyclerView()
            })
        //        Drawer Locked and visible Bottom navigation
        (requireActivity() as NavigationManager).setDrawerLocked(true)
        (requireActivity() as NavigationManager).bottomNavigationVisibility(true)
        //        Return view
        return fragmentCategoryBinding.getRoot()
    }

    //    region methods
    //    Set category adapter and layout manager
    private fun setRecyclerView() {
        fragmentCategoryBinding!!.categoryRecyclerView.setHasFixedSize(true)
        fragmentCategoryBinding!!.categoryRecyclerView.adapter = categoryItemAdapter
    }

    //    On Click Listener on Recycler View items
    override fun onItemClickListener(view: View?, position: Int, message: String?) {
        snackBarToast!!.snackBarLongTime(
            message,
            requireActivity().findViewById(R.id.bottomNavigationView)
        )
    } //    endregion
}