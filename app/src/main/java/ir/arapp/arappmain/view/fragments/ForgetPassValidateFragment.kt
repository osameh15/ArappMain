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
import ir.arapp.arappmain.databinding.FragmentValidateForgetPassBinding
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.HideShowKeyboard
import ir.arapp.arappmain.util.services.SnackBarMessage
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.ForgetPassViewModel
import java.util.*

class ForgetPassValidateFragment : Fragment(), SnackBarMessage, FragmentManager {
    //    region Variables
    var _fragmentValidateForgetPassBinding: FragmentValidateForgetPassBinding? = null
    val fragmentValidateForgetPassBinding get() = _fragmentValidateForgetPassBinding!!
    private var snackBarToast: SnackBarToast? = null
    private var hideShowKeyboard: HideShowKeyboard? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentValidateForgetPassBinding = null
    }
    //    endregion
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentValidateForgetPassBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_validate_forget_pass,
            container,
            false
        )
        //        Set view model
        val forgetPassValidateViewModel = ViewModelProvider(requireActivity()).get(
            ForgetPassViewModel::class.java
        )
        fragmentValidateForgetPassBinding.setViewModel(forgetPassValidateViewModel)
        forgetPassValidateViewModel.snackBarMessage = this
        forgetPassValidateViewModel.fragmentManager = this
        //        Life cycle owner
        fragmentValidateForgetPassBinding.setLifecycleOwner(this)
        //        Hooks
        snackBarToast = SnackBarToast(fragmentValidateForgetPassBinding.getRoot())
        hideShowKeyboard = HideShowKeyboard(requireContext(), fragmentValidateForgetPassBinding.getRoot())
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(
            fragmentValidateForgetPassBinding.forgetPassToolbar
        )
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentValidateForgetPassBinding.forgetPassToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Pin view animation
        fragmentValidateForgetPassBinding.pinView.setAnimationEnable(true)
        pinViewTryAgainLineColor()
        //        Return view
        return fragmentValidateForgetPassBinding.getRoot()
    }

    //    region methods
    //    Back button navigation
    private fun onNavigateUp() {
        requireActivity().onBackPressed()
    }

    //    Set methods for specific function
    override fun setFunction(type: String?) {
        when (type) {
            "error" -> fragmentValidateForgetPassBinding!!.pinView.setLineColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.notificationColorRed,
                    requireActivity().theme
                )
            )
            "editPhone" -> {
                fragmentValidateForgetPassBinding!!.editPhone.isEnabled = true
                fragmentValidateForgetPassBinding!!.editPhone.setTextColor(resources.getColor(R.color.colorAccent))
            }
            "resend" -> {
                fragmentValidateForgetPassBinding!!.pinView.setText("")
                fragmentValidateForgetPassBinding!!.editPhone.isEnabled = false
                fragmentValidateForgetPassBinding!!.editPhone.setTextColor(resources.getColor(R.color.disable))
                fragmentValidateForgetPassBinding!!.pinView.setLineColor(
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
        fragmentValidateForgetPassBinding!!.pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                fragmentValidateForgetPassBinding!!.pinView.setLineColor(
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
        val navController = Navigation.findNavController(
            fragmentValidateForgetPassBinding!!.root
        )
        when (message) {
            "forgetPass" -> {
                navController.navigate(R.id.action_forgetPassValidateFragment_to_forgetPassFragment)
                hideShowKeyboard!!.hideKeyboardFrom(true)
            }
            "phone" -> {
                navController.navigate(R.id.action_forgetPassValidateFragment_to_forgetPassPhoneFragment)
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