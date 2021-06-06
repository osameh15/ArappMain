package ir.arapp.arappmain.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentLoginBinding
import ir.arapp.arappmain.util.services.*
import ir.arapp.arappmain.view.activities.HomeActivity
import ir.arapp.arappmain.viewmodel.LoginViewModel

class LoginFragment : Fragment(), SnackBarMessage, FragmentManager {
    //    region Variable
    var _fragmentLoginBinding: FragmentLoginBinding? = null
    val fragmentLoginBinding get() = _fragmentLoginBinding!!
    private var snackBarToast: SnackBarToast? = null
    private var hideShowKeyboard: HideShowKeyboard? = null
    private var sessionManager: SessionManager? = null
    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentLoginBinding = null
    }

    //    endregion
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        //        Set view model
        val loginViewModel = ViewModelProvider(requireActivity()).get(
            LoginViewModel::class.java
        )
        fragmentLoginBinding.setViewModel(loginViewModel)
        loginViewModel.snackBarMessage = this
        loginViewModel.fragmentManager = this
        //        Set Life Cycle
        fragmentLoginBinding.setLifecycleOwner(this)
        //        Hooks
        sessionManager = SessionManager(requireContext())
        snackBarToast = SnackBarToast(fragmentLoginBinding.getRoot())
        hideShowKeyboard = HideShowKeyboard(requireContext(), fragmentLoginBinding.getRoot())
        //        Return view
        return fragmentLoginBinding.getRoot()
    }

    //    region methods
    //    On resume
    override fun onResume() {
        super.onResume()
        requireActivity().window.addFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                    or WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    //        Fragment navigation
    override fun navigateToFragment(fragment: String?) {
//        Nav Controller
        val navController = Navigation.findNavController(requireView())
        when (fragment) {
            "signUp" -> {
                navController.navigate(R.id.action_loginFragment_to_registerPhoneFragment)
                requireActivity().window.clearFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                            or WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                hideShowKeyboard!!.hideKeyboardFrom(true)
            }
            "forgetPass" -> {
                navController.navigate(R.id.action_loginFragment_to_forgetPassPhoneFragment)
                requireActivity().window.clearFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                            or WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
                hideShowKeyboard!!.hideKeyboardFrom(true)
            }
            "home" -> {
                sessionManager!!.setLogin(true)
                val homeActivity = Intent(requireActivity(), HomeActivity::class.java)
                startActivity(homeActivity)
                hideShowKeyboard!!.hideKeyboardFrom(true)
                requireActivity().viewModelStore.clear()
                requireActivity().finish()
            }
        }
    }

    //    Set methods for specific function
    override fun setFunction(type: String?) {}

    //    Error/success message
    override fun onSuccess(message: String?) {
        snackBarToast!!.snackBarShortTime(message, fragmentLoginBinding!!.signUpLayout)
    }

    override fun onFailure(message: String?) {
        snackBarToast!!.snackBarShortTime(message, fragmentLoginBinding!!.signUpLayout)
    } //    endregion
}