package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import java.util.Objects;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.FragmentManager;
import ir.arapp.arappmain.Util.Services.HideShowKeyboard;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentValidateRegisterBinding;
import ir.arapp.arappmain.viewmodel.RegisterViewModel;

public class RegisterValidateFragment extends Fragment implements SnackBarMessage, FragmentManager
{

//    region Variables
    FragmentValidateRegisterBinding fragmentValidateRegisterBinding;
    RegisterViewModel registerValidateViewModel;
    private SnackBarToast snackBarToast;
    private HideShowKeyboard hideShowKeyboard;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
         fragmentValidateRegisterBinding = FragmentValidateRegisterBinding.inflate(inflater, container, false);
//        Set view model
        registerValidateViewModel = ViewModelProviders.of(requireActivity()).get(RegisterViewModel.class);
        fragmentValidateRegisterBinding.setViewModel(registerValidateViewModel);
        registerValidateViewModel.snackBarMessage = this;
        registerValidateViewModel.fragmentManager = this;
//        Life cycle owner
        fragmentValidateRegisterBinding.setLifecycleOwner(this);
//        Hooks
        snackBarToast = new SnackBarToast(fragmentValidateRegisterBinding.getRoot());
        hideShowKeyboard = new HideShowKeyboard(getContext(), fragmentValidateRegisterBinding.getRoot());
//        Toolbar
        ((AppCompatActivity) requireActivity()).setSupportActionBar(fragmentValidateRegisterBinding.registerToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentValidateRegisterBinding.registerToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Pin view animation
        fragmentValidateRegisterBinding.pinView.setAnimationEnable(true);
        pinViewTryAgainLineColor();
//        Return view
        return fragmentValidateRegisterBinding.getRoot();
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
                fragmentValidateRegisterBinding.pinView.setLineColor(
                        ResourcesCompat.getColor(getResources(), R.color.notificationColorRed, requireActivity().getTheme()));
                break;
            case "resend":
                fragmentValidateRegisterBinding.timer.setTextColor(getResources().getColor(R.color.colorAccentDark));
                fragmentValidateRegisterBinding.pinView.setText("");
                fragmentValidateRegisterBinding.pinView.setLineColor(
                        ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, requireActivity().getTheme()));
                break;
        }
    }
//    Pin View change Line Color
    private void pinViewTryAgainLineColor()
    {
        fragmentValidateRegisterBinding.pinView.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                fragmentValidateRegisterBinding.pinView.setLineColor(
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
        final NavController navController = Navigation.findNavController(requireView());

        switch (message)
        {
            case "phoneRegister":
                navController.navigate(R.id.action_registerValidateFragment_to_registerPhoneFragment);
                requireActivity().getViewModelStore().clear();
                hideShowKeyboard.hideKeyboardFrom(true);
                break;
            case "register":
                navController.navigate(R.id.action_registerValidateFragment_to_registerFragment);
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