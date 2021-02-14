package ir.arapp.arappmain.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.marozzi.roundbutton.RoundButton;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.View.Activities.HomeActivity;
import ir.arapp.arappmain.viewmodel.LoginViewModel;
import ir.arapp.arappmain.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment implements SnackBarMessage, NavigateFragment
{

    //region Variable
    LoginViewModel loginViewModel;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        FragmentLoginBinding fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        //set view model
        loginViewModel = new LoginViewModel();
        fragmentLoginBinding.setViewModel(loginViewModel);
        loginViewModel.snackBarMessage = this;
        loginViewModel.navigateFragment = this;

        //return view;
        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //region fragment navigation
    @Override
    public void navigateToFragment(String fragment)
    {
        final NavController navController = Navigation.findNavController(getView());
        if (fragment.equals("signUp"))
        {
            navController.navigate(R.id.action_loginFragment_to_registerPhoneFragment);
            requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if(fragment.equals("forgetPass"))
        {
            navController.navigate(R.id.action_loginFragment_to_forgetPassPhoneFragment);
            requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if (fragment.equals("home"))
        {
            Intent homeActivity = new Intent(getActivity(), HomeActivity.class);
            startActivity(homeActivity);
            requireActivity().finish();
        }
    }
    //endregion

    //region error/success message
    @Override
    public void onSuccess()
    {
        snackBar("با موفقیت وارد شدید");
    }
    @Override
    public void onFailure(String message)
    {
        snackBar(message);
    }
    private void snackBar(String message)
    {
        Snackbar snackbar = Snackbar.make(requireView(), message, BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setDuration(2300);
        snackbar.show();
    }
    //endregion
}