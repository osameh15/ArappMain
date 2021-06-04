package ir.arapp.arappmain.view.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.util.services.FragmentManager;
import ir.arapp.arappmain.util.services.HideShowKeyboard;
import ir.arapp.arappmain.util.services.SnackBarMessage;
import ir.arapp.arappmain.util.services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentValidateForgetPassBinding;
import ir.arapp.arappmain.viewmodel.ForgetPassViewModel;

public class ForgetPassValidateFragment extends Fragment implements SnackBarMessage, FragmentManager
{

//    region Variables
    FragmentValidateForgetPassBinding fragmentValidateForgetPassBinding;
    private SnackBarToast snackBarToast;
    private HideShowKeyboard hideShowKeyboard;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentValidateForgetPassBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_validate_forget_pass, container, false);
//        Set view model
        ForgetPassViewModel forgetPassValidateViewModel = new ViewModelProvider(requireActivity()).get(ForgetPassViewModel.class);
        fragmentValidateForgetPassBinding.setViewModel(forgetPassValidateViewModel);
        forgetPassValidateViewModel.snackBarMessage = this;
        forgetPassValidateViewModel.fragmentManager = this;
//        Life cycle owner
        fragmentValidateForgetPassBinding.setLifecycleOwner(this);
//        Hooks
        snackBarToast = new SnackBarToast(fragmentValidateForgetPassBinding.getRoot());
        hideShowKeyboard = new HideShowKeyboard(getContext(), fragmentValidateForgetPassBinding.getRoot());
//        Toolbar
        ((AppCompatActivity) requireActivity()).setSupportActionBar(fragmentValidateForgetPassBinding.forgetPassToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentValidateForgetPassBinding.forgetPassToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Pin view animation
        fragmentValidateForgetPassBinding.pinView.setAnimationEnable(true);
        pinViewTryAgainLineColor();
//        Return view
        return fragmentValidateForgetPassBinding.getRoot();
    }

//    region methods
//    Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
    }
//    Set methods for specific function
    @Override
    public void setFunction(String type)
    {
        switch (type)
        {
            case "error":
                fragmentValidateForgetPassBinding.pinView.setLineColor(
                        ResourcesCompat.getColor(getResources(), R.color.notificationColorRed, requireActivity().getTheme()));
                break;
            case "editPhone":
                fragmentValidateForgetPassBinding.editPhone.setEnabled(true);
                fragmentValidateForgetPassBinding.editPhone.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
            case "resend":
                fragmentValidateForgetPassBinding.pinView.setText("");
                fragmentValidateForgetPassBinding.editPhone.setEnabled(false);
                fragmentValidateForgetPassBinding.editPhone.setTextColor(getResources().getColor(R.color.disable));
                fragmentValidateForgetPassBinding.pinView.setLineColor(
                        ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, requireActivity().getTheme()));
                break;
        }
    }
//    Pin View change Line Color
    private void pinViewTryAgainLineColor()
    {
        fragmentValidateForgetPassBinding.pinView.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                fragmentValidateForgetPassBinding.pinView.setLineColor(
                        ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, requireActivity().getTheme()));
            }
            @Override
            public void afterTextChanged(Editable editable)
            {
            }
        });
    }
//    Fragment navigation
    @Override
    public void navigateToFragment(String message)
    {
//        Nav Controller
        final NavController navController = Navigation.findNavController(fragmentValidateForgetPassBinding.getRoot());

        switch (message)
        {
            case "forgetPass":
                navController.navigate(R.id.action_forgetPassValidateFragment_to_forgetPassFragment);
                hideShowKeyboard.hideKeyboardFrom(true);
                break;
            case "phone":
                navController.navigate(R.id.action_forgetPassValidateFragment_to_forgetPassPhoneFragment);
                hideShowKeyboard.hideKeyboardFrom(true);
                break;
        }
    }
//    Error/success message
    @Override
    public void onSuccess(String message)
    {
        snackBarToast.snackBarShortTime(message);
    }
    @Override
    public void onFailure(String message)
    {
        snackBarToast.snackBarShortTime(message);
    }
//    endregion
}