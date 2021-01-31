package ir.arapp.arappmain.View.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.marozzi.roundbutton.RoundButton;

import java.util.Objects;

import ir.arapp.arappmain.R;

public class RegLogFragment extends Fragment
{
    //Variables
    RoundButton register;
    TextView login;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reg_log, container, false);

        //Hooks
        register = view.findViewById(R.id.signUpRegLog);
        login = view.findViewById(R.id.loginRegLog);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        //OnClick
        register.setOnClickListener(view1 -> goToRegisterFragment(navController));
        login.setOnClickListener(view1 -> goToLoginFragment(navController));

        //To Navigate, pop up and disable back navigate on phone ...
        /*NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.regLogFragment, true).build();
        navController.navigate(R.id.action_regLogFragment_to_loginFragment, null, navOptions);*/
    }

    @Override
    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    //region fragment navigation
    //Go To another fragments
    private void goToLoginFragment(NavController navController)
    {
        navController.navigate(R.id.action_regLogFragment_to_loginFragment);
    }
    private void goToRegisterFragment(NavController navController)
    {
        navController.navigate(R.id.action_regLogFragment_to_registerPhoneFragment);
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
    //endregion

}