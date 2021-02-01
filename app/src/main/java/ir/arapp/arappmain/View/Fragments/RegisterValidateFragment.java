package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.chaos.view.PinView;
import com.google.android.material.appbar.MaterialToolbar;
import com.marozzi.roundbutton.RoundButton;

import ir.arapp.arappmain.R;

public class RegisterValidateFragment extends Fragment
{

    //region Variables
    MaterialToolbar toolbar;
    TextView tryAgain;
    TextView changeNumber;
    PinView pinView;
    TextView timer;
    RoundButton register;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_validate_register, container, false);

        //Hooks
        toolbar = view.findViewById(R.id.registerToolbar);
        tryAgain = view.findViewById(R.id.sendValidationCode);
        changeNumber = view.findViewById(R.id.changePhoneNumber);
        pinView = view.findViewById(R.id.otpRegister);
        timer = view.findViewById(R.id.timerValidationCode);
        register = view.findViewById(R.id.phoneRegisterButton);

        //Toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        //OnClick
        changeNumber.setOnClickListener(view1 -> goToPhoneRegister(navController));
        register.setOnClickListener(view1 -> goToRegister(navController));
        toolbar.setNavigationOnClickListener(view1 -> onNavigateUp(navController));
    }

    //Back button navigation
    private void onNavigateUp(NavController navController)
    {
        requireActivity().onBackPressed();
        /*NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.regLogFragment, true).build();
        navController.navigate(R.id.action_regLogFragment_to_loginFragment, null, navOptions);*/
    }

    //region fragment navigation
    private void goToPhoneRegister(NavController navController)
    {
        navController.navigate(R.id.action_registerValidateFragment_to_registerPhoneFragment);
    }

    private void goToRegister(NavController navController)
    {
        navController.navigate(R.id.action_registerValidateFragment_to_registerFragment);
    }
    //endregion
}