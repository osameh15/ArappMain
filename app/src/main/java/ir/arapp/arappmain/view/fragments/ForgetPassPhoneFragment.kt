package ir.arapp.arappmain.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentPhoneForgetPassBinding
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.HideShowKeyboard
import ir.arapp.arappmain.util.services.SnackBarMessage
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.ForgetPassViewModel
import java.util.*

class ForgetPassPhoneFragment : Fragment(), SnackBarMessage, FragmentManager {
    //    region Variable
    var _fragmentPhoneForgetPassBinding: FragmentPhoneForgetPassBinding? = null
    val fragmentPhoneForgetPassBinding get() = _fragmentPhoneForgetPassBinding!!
    private var hideShowKeyboard: HideShowKeyboard? = null
    private var snackBarToast: SnackBarToast? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentPhoneForgetPassBinding  = null
    }
    //    endregion
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentPhoneForgetPassBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_phone_forget_pass, container, false)
        //        Set view model
        val forgetPassPhoneViewModel = ViewModelProvider(requireActivity()).get(
            ForgetPassViewModel::class.java
        )
        fragmentPhoneForgetPassBinding.setViewModel(forgetPassPhoneViewModel)
        forgetPassPhoneViewModel.snackBarMessage = this
        forgetPassPhoneViewModel.fragmentManager = this
        //        Set Life Cycle
        fragmentPhoneForgetPassBinding.setLifecycleOwner(this)
        //        Hooks
        snackBarToast = SnackBarToast(fragmentPhoneForgetPassBinding.getRoot())
        hideShowKeyboard = HideShowKeyboard(requireContext(), fragmentPhoneForgetPassBinding.getRoot())
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentPhoneForgetPassBinding.forgetPassToolbar)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentPhoneForgetPassBinding.forgetPassToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Return view
        return fragmentPhoneForgetPassBinding.getRoot()
    }

    //    region methods
    //    On resume
    override fun onResume() {
        super.onResume()
        requireActivity().window.clearFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                    or WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    //    Back button navigation
    private fun onNavigateUp() {
        requireActivity().onBackPressed()
    }

    //    Fragment navigation
    override fun navigateToFragment(message: String?) {
//        Nav Controller
        val navController = Navigation.findNavController(requireView())
        if (message == "validate") {
            navController.navigate(R.id.action_forgetPassPhoneFragment_to_forgetPassValidateFragment)
            hideShowKeyboard!!.hideKeyboardFrom(true)
        }
    }

    //    Set methods for specific function
    override fun setFunction(type: String?) {}

    //    Error/success message
    override fun onSuccess(message: String?) {
        snackBarToast!!.snackBarShortTime(message)
    }

    override fun onFailure(message: String?) {
        snackBarToast!!.snackBarShortTime(message)
    } //    endregion
}