package ir.arapp.arappmain.View.Fragments;

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
import ir.arapp.arappmain.R;

public class RegLogFragment extends Fragment
{
    //Variables

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reg_log, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        //To Navigate and go to specific fragment ...
        /*navController.navigate(R.id.action_regLogFragment_to_loginFragment);*/

        //To Navigate, pop up and disable back navigate on phone ...
        /*NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.regLogFragment, true).build();
        navController.navigate(R.id.action_regLogFragment_to_loginFragment, null, navOptions);*/
    }
}