package ir.arapp.arappmain.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.marozzi.roundbutton.RoundButton
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentRegisterBinding
import ir.arapp.arappmain.util.services.*
import ir.arapp.arappmain.view.activities.HomeActivity
import ir.arapp.arappmain.viewmodel.RegisterViewModel
import java.util.*

class RegisterFragment : Fragment(), SnackBarMessage, FragmentManager {
    //    region Variable
    var _fragmentRegisterBinding: FragmentRegisterBinding? = null
    val fragmentRegisterBinding get() = _fragmentRegisterBinding!!
    private var snackBarToast: SnackBarToast? = null
    private var hideShowKeyboard: HideShowKeyboard? = null
    private var sessionManager: SessionManager? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentRegisterBinding = null
    }

    //    endregion
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentRegisterBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        //        Set View model
        val registerViewModel = ViewModelProvider(requireActivity()).get(
            RegisterViewModel::class.java
        )
        fragmentRegisterBinding.setViewModel(registerViewModel)
        registerViewModel.snackBarMessage = this
        registerViewModel.fragmentManager = this
        //        Set Life Cycle
        fragmentRegisterBinding.setLifecycleOwner(this)
        //        Hooks
        sessionManager = SessionManager(requireContext())
        snackBarToast = SnackBarToast(fragmentRegisterBinding.getRoot())
        hideShowKeyboard = HideShowKeyboard(requireContext(), fragmentRegisterBinding.getRoot())
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentRegisterBinding.registerToolbar)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentRegisterBinding.registerToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Return view
        return fragmentRegisterBinding.getRoot()
    }

    //    region methods
    //    Back button navigation
    private fun onNavigateUp() {
        requireActivity().onBackPressed()
        requireActivity().viewModelStore.clear()
    }

    //    Fragment navigation
    override fun navigateToFragment(message: String?) {
        if (message == "home") {
            val homeActivity = Intent(requireActivity(), HomeActivity::class.java)
            sessionManager!!.setLogin(true)
            startActivity(homeActivity)
            hideShowKeyboard!!.hideKeyboardFrom(true)
            requireActivity().viewModelStore.clear()
            requireActivity().finish()
        }
    }

    //    Set methods for specific function
    override fun setFunction(type: String?) {
//        Privacy Bottom sheet dialog
        if (type == "privacy") {
            val bottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.bottomSheetDialogTheme)
            //            Set layout inflater
            @SuppressLint("InflateParams") val view = LayoutInflater.from(requireContext())
                .inflate(R.layout.custom_privacy_app_bottom_sheet, null)
            //            Hooks
            val textView = view.findViewById<TextView>(R.id.privacy_text)
            val close: RoundButton = view.findViewById(R.id.closeBottomSheet)
            //            Set action
            textView.movementMethod = ScrollingMovementMethod()
            if (close != null) {
                close.setOnClickListener { view1: View? -> bottomSheetDialog.dismiss() }
            }
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
        }
    }

    //    Error/success message
    override fun onSuccess(message: String?) {
        snackBarToast!!.snackBarLongTime(message)
    }

    override fun onFailure(message: String?) {
        snackBarToast!!.snackBarShortTime(message)
    } //    endregion
}