package ir.arapp.arappmain.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import java.util.Objects;

import ir.arapp.arappmain.Util.Services.FragmentManager;
import ir.arapp.arappmain.Util.Services.HideShowKeyboard;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.View.Activities.HomeActivity;
import ir.arapp.arappmain.databinding.FragmentRegisterBinding;
import ir.arapp.arappmain.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment implements SnackBarMessage, FragmentManager
{

//    region Variable
    FragmentRegisterBinding fragmentRegisterBinding;
    RegisterViewModel registerViewModel;
    private SnackBarToast snackBarToast;
    private HideShowKeyboard hideShowKeyboard;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false);
//        Set View model
        registerViewModel = ViewModelProviders.of(requireActivity()).get(RegisterViewModel.class);
        fragmentRegisterBinding.setViewModel(registerViewModel);
        registerViewModel.snackBarMessage = this;
        registerViewModel.fragmentManager = this;
//        Set Life Cycle
        fragmentRegisterBinding.setLifecycleOwner(this);
//        Hooks
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