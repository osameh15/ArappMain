package ir.arapp.arappmain.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentValidateRegisterBinding
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.HideShowKeyboard
import ir.arapp.arappmain.util.services.SnackBarMessage
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.RegisterViewModel
import java.util.*

class RegisterValidateFragment : Fragment(), SnackBarMessage, FragmentManager {
    //    region Variables
    var _fragmentValidateRegisterBinding: FragmentValidateRegisterBinding? = null
    val fragmentValidateRegisterBinding get() = _fragmentValidateRegisterBinding!!
    private var snackBarToast: SnackBarToast? = null
    private var hideShowKeyboard: HideShowKeyboard? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentValidateRegisterBinding =null
    }
    //    endregion
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentValidateRegisterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_validate_register, container, false)
        //        Set view model
        val registerValidateViewModel = ViewModelProvider(requireActivity()).get(
            RegisterViewModel::class.java
        )
        fragmentValidateRegisterBinding.setViewModel(registerValidateViewModel)
        registerValidateViewModel.snackBarMessage = this
        registerValidateViewModel.fragmentManager = this
        //        Life cycle owner
        fragmentValidateRegisterBinding.setLifecycleOwner(this)
        //        Hooks
        snackBarToast = SnackBarToast(fragmentValidateRegisterBinding.getRoot())
        hideShowKeyboard = HideShowKeyboard(requireContext(), fragmentValidateRegisterBinding.getRoot())
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentValidateRegisterBinding.registerToolbar)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentValidateRegisterBinding.registerToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Pin view animation
        fragmentValidateRegisterBinding.pinView.setAnimationEnable(true)
        pinViewTryAgainLineColor()
        //        Return view
        return fragmentValidateRegisterBinding.getRoot()
    }

    //    region methods
    //    Back button navigation
    private fun onNavigateUp() {
        requireActivity().onBackPressed()
    }

    //    Set methods for specific function
    override fun setFunction(type: String?) {
        when (type) {
            "error" -> fragmentValidateRegisterBinding!!.pinView.setLineColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.notificationColorRed,
                    requireActivity().theme
                )
            )
            "editPhone" -> {
                fragmentValidateRegisterBinding!!.editPhone.isEnabled = true
                fragmentValidateRegisterBinding!!.editPhone.setTextColor(resources.getColor(R.color.colorAccent))
            }
            "resend" -> {
                fragmentValidateRegisterBinding!!.pinView.setText("")
                fragmentValidateRegisterBinding!!.editPhone.isEnabled = false
                fragmentValidateRegisterBinding!!.editPhone.setTextColor(resources.getColor(R.color.disable))
                fragmentValidateRegisterBinding!!.pinView.setLineColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorPrimaryDark,
                        requireActivity().theme
                    )
                )
            }
        }
    }

    //    Pin View change Line Color
    private fun pinViewTryAgainLineColor() {
        fragmentValidateRegisterBinding!!.pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                fragmentValidateRegisterBinding!!.pinView.setLineColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorPrimaryDark,
                        requireActivity().theme
                    )
                )
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    //    Fragment navigation
    override fun navigateToFragment(message: String?) {
//        Nav Controller
        val navController = Navigation.findNavController(requireView())
        when (message) {
            "phoneRegister" -> {
                navController.navigate(R.id.action_registerValidateFragment_to_registerPhoneFragment)
                hideShowKeyboard!!.hideKeyboardFrom(true)
            }
            "register" -> {
                navController.navigate(R.id.action_registerValidateFragment_to_registerFragment)
                hideShowKeyboard!!.hideKeyboardFrom(true)
            }
        }
    }

    //    Error/success message
    override fun onSuccess(message: String?) {
        snackBarToast!!.snackBarShortTime(message)
    }

    override fun onFailure(message: String?) {
        snackBarToast!!.snackBarShortTime(message)
    } //    endregion
}