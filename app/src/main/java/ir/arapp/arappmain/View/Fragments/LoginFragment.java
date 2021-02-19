package ir.arapp.arappmain.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.HideShowKeyboard;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.View.Activities.HomeActivity;
import ir.arapp.arappmain.viewmodel.LoginViewModel;
import ir.arapp.arappmain.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment implements SnackBarMessage, NavigateFragment
{

//    region Variable
    FragmentLoginBinding fragmentLoginBinding;
    LoginViewModel loginViewModel;
    private SnackBarToast snackBarToast;
    private HideShowKeyboard hideShowKeyboard;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);
//        Set view model
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        fragmentLoginBinding.setViewModel(loginViewModel);
        loginViewModel.snackBarMessage = this;
        loginViewModel.navigateFragment = this;
//        Hooks
        snackBarToast = new SnackBarToast(fragmentLoginBinding.getRoot());
        hideShowKeyboard = new HideShowKeyboard(getContext(), fragmentLoginBinding.getRoot());
//        Return view
        return fragmentLoginBinding.getRoot();
    }
    
//    region methods
//    On resume
    @Override
    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
//    Fragment navigation
    @Override
    public void navigateToFragment(String fragment)
    {
//        Nav Controller
        final NavController navController = Navigation.findNavController(requireView());

        switch (fragment)
        {
            case "signUp":
                navController.navigate(R.id.action_loginFragment_to_registerPhoneFragment);
                requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                        | WindowManager.LayoutParams.FLAG_FULLSCREEN);
                hideShowKeyboard.hideKeyboardFrom(true);
                break;
            case "forgetPass":
                navController.navigate(R.id.action_loginFragment_to_forgetPassPhoneFragment);
                requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                        | WindowManager.LayoutParams.FLAG_FULLSCREEN);
                hideShowKeyboard.hideKeyboardFrom(true);
                break;
            case "home":
                Intent homeActivity = new Intent(requireActivity(), HomeActivity.class);
                startActivity(homeActivity);
                hideShowKeyboard.hideKeyboardFrom(true);
                requireActivity().getViewModelStore().clear();
                requireActivity().finish();
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