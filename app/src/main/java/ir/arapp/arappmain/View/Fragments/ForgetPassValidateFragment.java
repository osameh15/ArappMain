package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.chaos.view.PinView;
import com.google.android.material.appbar.MaterialToolbar;
import com.marozzi.roundbutton.RoundButton;

import java.util.Objects;

import ir.arapp.arappmain.R;

public class ForgetPassValidateFragment extends Fragment
{

//    region Variables
    MaterialToolbar toolbar;
    TextView changeNumber;
    PinView pinView;
    TextView timer;
    RoundButton forgetPass;
//    endregion

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//         Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_validate_forget_pass, container, false);
//        Hooks
        toolbar = view.findViewById(R.id.forgetPassToolbar);
        timer = view.findViewById(R.id.timerValidationCode);
        pinView = view.findViewById(R.id.otpForgetPass);
        forgetPass = view.findViewById(R.id.forgetPassButton);
        changeNumber = view.findViewById(R.id.changePhoneNumber);
//        Toolbar
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        return view
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        toolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
        forgetPass.setOnClickListener(view1 -> goToForgetPass(navController));
        changeNumber.setOnClickListener(view1 -> goToPhoneForgetPass(navController));
    }

//    region methods
//    Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
    }
//    fragment navigation
    private void goToPhoneForgetPass(NavController navController)
    {
        navController.navigate(R.id.action_forgetPassValidateFragment_to_forgetPassPhoneFragment);
    }

    private void goToForgetPass(NavController navController)
    {
        navController.navigate(R.id.action_forgetPassValidateFragment_to_forgetPassFragment);
    }
//    endregion
}