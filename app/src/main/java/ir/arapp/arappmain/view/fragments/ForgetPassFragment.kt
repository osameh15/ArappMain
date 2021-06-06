package ir.arapp.arappmain.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentForgetPassBinding
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.HideShowKeyboard
import ir.arapp.arappmain.util.services.SnackBarMessage
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.ForgetPassViewModel
import java.util.*

class ForgetPassFragment : Fragment(), SnackBarMessage, FragmentManager {
    //    region Variable
    var _fragmentForgetPassBinding: FragmentForgetPassBinding? = null
    val fragmentForgetPassBinding get() = _fragmentForgetPassBinding!!
    private var snackBarToast: SnackBarToast? = null
    private var hideShowKeyboard: HideShowKeyboard? = null

    //    endregion
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//       Inflate the layout for this fragment
        _fragmentForgetPassBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_forget_pass, container, false)
        //        Set View model
        val forgetPassViewModel = ViewModelProvider(requireActivity()).get(
            ForgetPassViewModel::class.java
        )
        fragmentForgetPassBinding.setViewModel(forgetPassViewModel)
        forgetPassViewModel.snackBarMessage = this
        forgetPassViewModel.fragmentManager = this
        //        Set Life Cycle
        fragmentForgetPassBinding.setLifecycleOwner(this)
        //        Hooks
        snackBarToast = SnackBarToast(fragmentForgetPassBinding.getRoot())
        hideShowKeyboard = HideShowKeyboard(requireContext(), fragmentForgetPassBinding.getRoot())
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentForgetPassBinding.forgetPassToolbar)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentForgetPassBinding.forgetPassToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Return view
        return fragmentForgetPassBinding.getRoot()
    }

    //    region methods
    //    Back button navigation
    private fun onNavigateUp() {
        requireActivity().onBackPressed()
        requireActivity().viewModelStore.clear()
    }

    //    Fragment navigation
    override fun navigateToFragment(message: String?) {
//        Nav Controller
        val navController = Navigation.findNavController(requireView())
        if (message == "login") {
            navController.navigate(R.id.action_forgetPassFragment_to_loginFragment)
            hideShowKeyboard!!.hideKeyboardFrom(true)
        }
    }

    //    Check Pin View!
    override fun setFunction(type: String?) {}

    //    Error/success message
    override fun onSuccess(message: String?) {
        snackBarToast!!.snackBarLongTime(message)
    }

    override fun onFailure(message: String?) {
        snackBarToast!!.snackBarShortTime(message)
    } //    endregion
}