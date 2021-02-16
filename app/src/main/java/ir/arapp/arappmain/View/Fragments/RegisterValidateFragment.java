package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.chaos.view.PinView;

import java.util.Objects;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.CheckPinView;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentValidateRegisterBinding;
import ir.arapp.arappmain.viewmodel.RegisterValidateViewModel;

public class RegisterValidateFragment extends Fragment implements SnackBarMessage, NavigateFragment, CheckPinView
{

    //region Variables
    FragmentValidateRegisterBinding fragmentValidateRegisterBinding;
    RegisterValidateViewModel registerValidateViewModel;
    private SnackBarToast snackBarToast;
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
         fragmentValidateRegisterBinding = FragmentValidateRegisterBinding.inflate(inflater, container, false);
        //set view model
        registerValidateViewModel = ViewModelProviders.of(requireActivity()).get(RegisterValidateViewModel.class);
        fragmentValidateRegisterBinding.setViewModel(registerValidateViewModel);
        registerValidateViewModel.navigateFragment = this;
        registerValidateViewModel.snackBarMessage = this;
        registerValidateViewModel.checkPinView = this;
        //Hooks
        snackBarToast = new SnackBarToast(fragmentValidateRegisterBinding.getRoot());
        //Toolbar
        ((AppCompatActivity) requireActivity()).setSupportActionBar(fragmentValidateRegisterBinding.registerToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentValidateRegisterBinding.registerToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
        //pin view animation
        fragmentValidateRegisterBinding.pinView.setAnimationEnable(true);
        fragmentValidateRegisterBinding.pinView.setTransformationMethod(new PasswordTransformationMethod());
        //return view
        return fragmentValidateRegisterBinding.getRoot();
    }
    //Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
    }
    //check pin code error
    @Override
    public void checkPin()
    {
        fragmentValidateRegisterBinding.pinView.setLineColor(ResourcesCompat.getColor(getResources(), R.color.notificationColorRed, requireActivity().getTheme()));
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
        snackBarToast.snackBarShortTime(message);
    }
    //endregion
}