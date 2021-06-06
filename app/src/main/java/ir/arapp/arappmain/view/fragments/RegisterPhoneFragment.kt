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
import ir.arapp.arappmain.databinding.FragmentPhoneRegisterBinding
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.HideShowKeyboard
import ir.arapp.arappmain.util.services.SnackBarMessage
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.RegisterViewModel
import java.util.*

class RegisterPhoneFragment : Fragment(), SnackBarMessage, FragmentManager {
    //    region Variable
    var _fragmentPhoneRegisterBinding: FragmentPhoneRegisterBinding? = null
    val fragmentPhoneRegisterBinding get() = _fragmentPhoneRegisterBinding!!
    private var snackBarToast: SnackBarToast? = null
    private var hideShowKeyboard: HideShowKeyboard? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentPhoneRegisterBinding = null
    }
    //    endregion
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentPhoneRegisterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_phone_register, container, false)
        //        Set view model
        val registerPhoneViewModel = ViewModelProvider(requireActivity()).get(
            RegisterViewModel::class.java
        )
        fragmentPhoneRegisterBinding.setViewModel(registerPhoneViewModel)
        registerPhoneViewModel.snackBarMessage = this
        registerPhoneViewModel.fragmentManager = this
        //        Set Life Cycle
        fragmentPhoneRegisterBinding.setLifecycleOwner(this)
        //        Hooks
        snackBarToast = SnackBarToast(fragmentPhoneRegisterBinding.getRoot())
        hideShowKeyboard = HideShowKeyboard(requireContext(), fragmentPhoneRegisterBinding.getRoot())
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentPhoneRegisterBinding.registerToolbar)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentPhoneRegisterBinding.registerToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Return view
        return fragmentPhoneRegisterBinding.getRoot()
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
        when (message) {
            "login" -> {
                navController.navigate(R.id.action_registerPhoneFragment_to_loginFragment)
                requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                hideShowKeyboard!!.hideKeyboardFrom(true)
            }
            "validate" -> {
                navController.navigate(R.id.action_registerPhoneFragment_to_registerValidateFragment)
                hideShowKeyboard!!.hideKeyboardFrom(true)
            }
        }
    }

    //    Set methods for specific function
    override fun setFunction(type: String?) {}

    //    Error/success message
    override fun onSuccess(message: String?) {
        snackBarToast!!.snackBarShortTime(message, fragmentPhoneRegisterBinding!!.loginPanelLayout)
    }

    override fun onFailure(message: String?) {
        snackBarToast!!.snackBarShortTime(message, fragmentPhoneRegisterBinding!!.loginPanelLayout)
    } //    endregion
}