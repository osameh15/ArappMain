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
import com.marozzi.roundbutton.RoundButton;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.View.Activities.HomeActivity;
import ir.arapp.arappmain.View.Activities.SplashScreenActivity;
import ir.arapp.arappmain.viewmodel.LoginViewModel;
import ir.arapp.arappmain.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment
{

    //region Variable
    TextView signUp;
    TextView forgetPass;
    RoundButton login;
    //LoginViewModel loginViewModel;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        //FragmentLoginBinding fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
/*        loginViewModel = new LoginViewModel();
        fragmentLoginBinding.setViewModel(loginViewModel);*/

        //Hooks
        signUp = view.findViewById(R.id.LoginSignUp);
        forgetPass = view.findViewById(R.id.forgetPasswordLogin);
        login = view.findViewById(R.id.login);

        return view;
        //return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        //OnClick
        signUp.setOnClickListener(view1 -> goToRegister(navController));
        forgetPass.setOnClickListener(view1 -> goToForgetPass(navController));
        login.setOnClickListener(view1 -> goToHomeActivity(navController));
    }

    @Override
    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //region fragment navigation
    private void goToRegister(NavController navController)
    {
        navController.navigate(R.id.action_loginFragment_to_registerPhoneFragment);
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private void goToForgetPass(NavController navController)
    {
        navController.navigate(R.id.action_loginFragment_to_forgetPassPhoneFragment);
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private void goToHomeActivity(NavController navController)
    {
        Intent homeActivity = new Intent(getActivity(), HomeActivity.class);
        startActivity(homeActivity);
        requireActivity().finish();
    }
    //endregion

    //SnackBar
/*    Snackbar snackbar = Snackbar.make(view, "سلام بر همه!", BaseTransientBottomBar.LENGTH_LONG);
        snackbar.setDuration(6000);
        snackbar.setAction("باشه!!", view1 -> {snackbar.dismiss();});
        snackbar.show();*/
}