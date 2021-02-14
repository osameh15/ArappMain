package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.google.android.material.appbar.MaterialToolbar;
import com.marozzi.roundbutton.RoundButton;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.databinding.FragmentPhoneRegisterBinding;
import ir.arapp.arappmain.viewmodel.PhoneRegisterViewModel;

public class RegisterPhoneFragment extends Fragment
{

    //region Variable
    PhoneRegisterViewModel phoneRegisterViewModel;
    MaterialToolbar toolbar;
    TextView login;
    RoundButton register;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        FragmentPhoneRegisterBinding fragmentPhoneRegisterBinding = FragmentPhoneRegisterBinding.inflate(inflater, container, false);
        phoneRegisterViewModel = new PhoneRegisterViewModel();
        fragmentPhoneRegisterBinding.setViewModel(phoneRegisterViewModel);

        //Hooks
        toolbar = fragmentPhoneRegisterBinding.getRoot().findViewById(R.id.registerToolbar);
/*        login = view.findViewById(R.id.SignUpLogin);
        register = view.findViewById(R.id.phoneRegisterButton);*/

        //Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        return fragmentPhoneRegisterBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //Nav Controller
        final NavController navController = Navigation.findNavController(view);

        //OnClick
/*        login.setOnClickListener(view1 -> goToLogin(navController));
        register.setOnClickListener(view1 -> goToCodeRegister(navController));*/
        toolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
    }

    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
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