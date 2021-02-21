package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import java.util.Objects;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.FragmentManager;
import ir.arapp.arappmain.Util.Services.HideShowKeyboard;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentPhoneRegisterBinding;
import ir.arapp.arappmain.viewmodel.RegisterViewModel;

public class RegisterPhoneFragment extends Fragment implements SnackBarMessage, FragmentManager
{

//    region Variable
    FragmentPhoneRegisterBinding fragmentPhoneRegisterBinding;
    RegisterViewModel registerPhoneViewModel;
    private SnackBarToast snackBarToast;
    private HideShowKeyboard hideShowKeyboard;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentPhoneRegisterBinding = FragmentPhoneRegisterBinding.inflate(inflater, container, false);
//        Set view model
        registerPhoneViewModel = ViewModelProviders.of(requireActivity()).get(RegisterViewModel.class);
        fragmentPhoneRegisterBinding.setViewModel(registerPhoneViewModel);
        registerPhoneViewModel.snackBarMessage = this;
        registerPhoneViewModel.fragmentManager = this;
//        Set Life Cycle
        fragmentPhoneRegisterBinding.setLifecycleOwner(this);
//        Hooks
        snackBarToast = new SnackBarToast(fragmentPhoneRegisterBinding.getRoot());
        hideShowKeyboard = new HideShowKeyboard(getContext(), fragmentPhoneRegisterBinding.getRoot());
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentPhoneRegisterBinding.registerToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentPhoneRegisterBinding.registerToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Return view
        return fragmentPhoneRegisterBinding.getRoot();
    }

//    region methods
//    On resume
    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
//    Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
    }
//    Fragment navigation
    @Override
    public void navigateToFragment(String message)
    {
//        Nav Controller
        final NavController navController = Navigation.findNavController(requireView());

        switch (message)
        {
            case "login":
                navController.navigate(R.id.action_registerPhoneFragment_to_loginFragment);
                requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                hideShowKeyboard.hideKeyboardFrom(true);
                break;
            case "validate":
                navController.navigate(R.id.action_registerPhoneFragment_to_registerValidateFragment);
                hideShowKeyboard.hideKeyboardFrom(true);
                break;
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
        snackBarToast.snackBarShortTime(message);
    }
    @Override
    public void onFailure(String message)
    {
        snackBarToast.snackBarShortTime(message);
    }
//    endregion
}