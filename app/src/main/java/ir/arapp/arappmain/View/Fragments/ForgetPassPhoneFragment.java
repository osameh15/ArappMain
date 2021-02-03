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
import androidx.navigation.Navigation;

import com.google.android.material.appbar.MaterialToolbar;
import com.marozzi.roundbutton.RoundButton;

import java.util.Objects;

import ir.arapp.arappmain.R;

public class ForgetPassPhoneFragment extends Fragment
{

    //region Variable
    MaterialToolbar toolbar;
    RoundButton forgetPass;
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
        View view =  inflater.inflate(R.layout.fragment_phone_forget_pass, container, false);

        //Hooks
        toolbar = view.findViewById(R.id.forgetPassToolbar);
        forgetPass = view.findViewById(R.id.phoneForgetPassButton);

        //Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //Nav Controller
        final NavController navController = Navigation.findNavController(view);

        //OnClick
        toolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
        forgetPass.setOnClickListener(view1 -> goToForgetValidate(navController));
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
    private void goToForgetValidate(NavController navController)
    {
        navController.navigate(R.id.action_forgetPassPhoneFragment_to_forgetPassValidateFragment);
    }
    //endregion
}