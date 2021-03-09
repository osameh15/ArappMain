package ir.arapp.arappmain.View.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.marozzi.roundbutton.RoundButton;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.FragmentManager;
import ir.arapp.arappmain.Util.Services.HideShowKeyboard;
import ir.arapp.arappmain.Util.Services.SessionManager;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.View.Activities.HomeActivity;
import ir.arapp.arappmain.databinding.FragmentRegisterBinding;
import ir.arapp.arappmain.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment implements SnackBarMessage, FragmentManager
{

//    region Variable
    FragmentRegisterBinding fragmentRegisterBinding;
    private SnackBarToast snackBarToast;
    private HideShowKeyboard hideShowKeyboard;
    private SessionManager sessionManager;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false);
//        Set View model
        RegisterViewModel registerViewModel = ViewModelProviders.of(requireActivity()).get(RegisterViewModel.class);
        fragmentRegisterBinding.setViewModel(registerViewModel);
        registerViewModel.snackBarMessage = this;
        registerViewModel.fragmentManager = this;
//        Set Life Cycle
        fragmentRegisterBinding.setLifecycleOwner(this);
//        Hooks
        sessionManager = new SessionManager(requireContext());
        snackBarToast = new SnackBarToast(fragmentRegisterBinding.getRoot());
        hideShowKeyboard = new HideShowKeyboard(getContext(), fragmentRegisterBinding.getRoot());
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentRegisterBinding.registerToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentRegisterBinding.registerToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Return view
        return fragmentRegisterBinding.getRoot();
    }

//    region methods
//    Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
        requireActivity().getViewModelStore().clear();
    }
//    Fragment navigation
    @Override
    public void navigateToFragment(String message)
    {
        if (message.equals("home"))
        {
            Intent homeActivity = new Intent(requireActivity(), HomeActivity.class);
            sessionManager.setLogin(true);
            startActivity(homeActivity);
            hideShowKeyboard.hideKeyboardFrom(true);
            requireActivity().getViewModelStore().clear();
            requireActivity().finish();
        }
    }
//    Set methods for specific function
    @Override
    public void setFunction(String type)
    {
//        Privacy Bottom sheet dialog
        if (type.equals("privacy"))
        {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.bottomSheetDialogTheme);
//            Set layout inflater
            @SuppressLint("InflateParams") View view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_privacy_app_bottom_sheet, null);
//            Hooks
            TextView textView = view.findViewById(R.id.privacy_text);
            RoundButton close = view.findViewById(R.id.closeBottomSheet);
//            Set action
            textView.setMovementMethod(new ScrollingMovementMethod());
            if (close != null)
            {
                close.setOnClickListener(view1 -> bottomSheetDialog.dismiss());
            }
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();
        }
    }
//    Error/success message
    @Override
    public void onSuccess(String message)
    {
        snackBarToast.snackBarLongTime(message);
    }
    @Override
    public void onFailure(String message)
    {
        snackBarToast.snackBarShortTime(message);
    }
//    endregion
}