package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.marozzi.roundbutton.RoundButton;

import ir.arapp.arappmain.R;

public class RegisterPhoneFragment extends Fragment
{

    //Variable
    TextView login;
    RoundButton register;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phone_register, container, false);

        //Hooks
        login = view.findViewById(R.id.SignUpLogin);
        register = view.findViewById(R.id.phoneRegisterButton);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        //OnClick
        login.setOnClickListener(view1 -> goToLogin(navController));
        register.setOnClickListener(view1 -> goToCodeRegister(navController));
    }

    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    //region fragment navigation
    private void goToCodeRegister(NavController navController)
    {
        navController.navigate(R.id.action_registerPhoneFragment_to_registerValidateFragment);
    }

    private void goToLogin(NavController navController)
    {
        navController.navigate(R.id.action_registerPhoneFragment_to_loginFragment);
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
    //endregion
}