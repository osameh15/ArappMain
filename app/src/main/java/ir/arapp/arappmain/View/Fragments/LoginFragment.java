package ir.arapp.arappmain.View.Fragments;

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

public class LoginFragment extends Fragment
{

    //Variable
    TextView signUp;
    TextView forgetPass;
    RoundButton login;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //Hooks
        signUp = view.findViewById(R.id.LoginSignUp);
        forgetPass = view.findViewById(R.id.forgetPasswordLogin);
        login = view.findViewById(R.id.login);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        //OnClick
        signUp.setOnClickListener(view1 -> goToRegister(navController));
        forgetPass.setOnClickListener(view1 -> goToForgetPass(navController));
    }

    @Override
    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    //region fragment navigation
    private void goToRegister(NavController navController)
    {
        navController.navigate(R.id.action_loginFragment_to_registerPhoneFragment);
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
    private void goToForgetPass(NavController navController)
    {
        navController.navigate(R.id.action_loginFragment_to_forgetPassPhoneFragment);
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
    private void goToHomeActivity(NavController navController)
    {
        navController.navigate(R.id.action_loginFragment_to_registerPhoneFragment);
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
    //endregion

}