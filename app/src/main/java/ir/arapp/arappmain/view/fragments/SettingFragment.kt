package ir.arapp.arappmain.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentSettingBinding
import ir.arapp.arappmain.util.services.NavigationManager
import ir.arapp.arappmain.util.services.SnackBarToast
import java.util.*

class SettingFragment : Fragment() {
    //    region Variable
    var _fragmentSettingBinding: FragmentSettingBinding? = null
    val fragmentSettingBinding get() = _fragmentSettingBinding!!
    private var snackBarToast: SnackBarToast? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentSettingBinding = null
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
        _fragmentSettingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        //        Set view model
//        Hooks
        snackBarToast = SnackBarToast(fragmentSettingBinding.getRoot())
        //        Set life cycle
        fragmentSettingBinding.setLifecycleOwner(this)
        //        Drawer Locked and visible Bottom navigation
        (requireActivity() as NavigationManager).setDrawerLocked(true)
        (requireActivity() as NavigationManager).bottomNavigationVisibility(false)
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentSettingBinding.profileToolbar)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentSettingBinding.profileToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Return view
        return fragmentSettingBinding.getRoot()
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
    } //    endregion
}