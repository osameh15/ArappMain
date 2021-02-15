package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Objects;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.databinding.FragmentValidateRegisterBinding;
import ir.arapp.arappmain.viewmodel.RegisterValidateViewModel;

public class RegisterValidateFragment extends Fragment implements SnackBarMessage, NavigateFragment
{

    //region Variables
    RegisterValidateViewModel registerValidateViewModel;
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
        FragmentValidateRegisterBinding fragmentValidateRegisterBinding = FragmentValidateRegisterBinding.inflate(inflater, container, false);
        //set view model
        registerValidateViewModel = ViewModelProviders.of(requireActivity()).get(RegisterValidateViewModel.class);
        fragmentValidateRegisterBinding.setViewModel(registerValidateViewModel);
        registerValidateViewModel.navigateFragment = this;
        registerValidateViewModel.snackBarMessage = this;
        //Toolbar
        ((AppCompatActivity) requireActivity()).setSupportActionBar(fragmentValidateRegisterBinding.registerToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentValidateRegisterBinding.registerToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
        //return view
        return fragmentValidateRegisterBinding.getRoot();
    }
    //Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
    }
    //region fragment navigation
    @Override
    public void navigateToFragment(String message)
    {
        //Nav Controller
        final NavController navController = Navigation.findNavController(getView());

        if (message.equals("phoneRegister"))
        {
            navController.navigate(R.id.action_registerValidateFragment_to_registerPhoneFragment);
        }
        if (message.equals("register"))
        {
            navController.navigate(R.id.action_registerValidateFragment_to_registerFragment);
        }
    }
    //endregion
    //region error/success message
    @Override
    public void onSuccess(String message)
    {

    }
    @Override
    public void onFailure(String message)
    {

    }
    //endregion
}