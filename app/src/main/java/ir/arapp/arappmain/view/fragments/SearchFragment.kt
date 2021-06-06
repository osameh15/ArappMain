package ir.arapp.arappmain.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mancj.materialsearchbar.MaterialSearchBar
import com.mancj.materialsearchbar.MaterialSearchBar.OnSearchActionListener
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentSearchBinding
import ir.arapp.arappmain.util.services.NavigationManager

class SearchFragment : Fragment(), OnSearchActionListener {
    //    region Variables
    var _fragmentSearchBinding: FragmentSearchBinding? = null
    val fragmentSearchBinding get() =  _fragmentSearchBinding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentSearchBinding = null
    }
    //    endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//         Inflate the layout for this fragment
        _fragmentSearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        //        Search Controller
        fragmentSearchBinding.searchView.openSearch()
        fragmentSearchBinding.searchView.setOnSearchActionListener(this)
        fragmentSearchBinding.searchView.setArrowIcon(R.drawable.ic_arrow_forward_black_48dp)
        //        Drawer Locked and visible Bottom navigation
        (requireActivity() as NavigationManager).setDrawerLocked(true)
        (requireActivity() as NavigationManager).bottomNavigationVisibility(true)
        //        Return view
        return fragmentSearchBinding.getRoot()
    }

    //    region Methods
    override fun onSearchStateChanged(enabled: Boolean) {}
    override fun onSearchConfirmed(text: CharSequence) {}
    override fun onButtonClicked(buttonCode: Int) {
        if (buttonCode == MaterialSearchBar.BUTTON_BACK) {
            val navController = Navigation.findNavController(requireView())
            navController.navigate(R.id.action_searchFragment_to_homeFragment)
        }
    } //    endregion
}