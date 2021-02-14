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
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.marozzi.roundbutton.RoundButton;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentPhoneRegisterBinding;
import ir.arapp.arappmain.viewmodel.PhoneRegisterViewModel;

public class RegisterPhoneFragment extends Fragment implements NavigateFragment, SnackBarMessage
{

    //region Variable
    private SnackBarToast snackBarToast;
    PhoneRegisterViewModel phoneRegisterViewModel;
    MaterialToolbar toolbar;
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
        //set view model
        phoneRegisterViewModel = new PhoneRegisterViewModel();
        fragmentPhoneRegisterBinding.setViewModel(phoneRegisterViewModel);
        phoneRegisterViewModel.navigateFragment = this;
        phoneRegisterViewModel.snackBarMessage = this;
        //Hooks
        toolbar = fragmentPhoneRegisterBinding.getRoot().findViewById(R.id.registerToolbar);
        snackBarToast = new SnackBarToast(fragmentPhoneRegisterBinding.getRoot());
        //Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //OnClick
        toolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
        //return view
        return fragmentPhoneRegisterBinding.getRoot();
    }
    //On resume
    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

        if (message.equals("login"))
        {
            navController.navigate(R.id.action_registerPhoneFragment_to_loginFragment);
            requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        if (message.equals("validate"))
        {
            navController.navigate(R.id.action_registerPhoneFragment_to_registerValidateFragment);
        }
    }
    //endregion
    //region error/success message
    @Override
    public void onSuccess()
    {
    }
    @Override
    public void onFailure(String message)
    {
        snackBarToast.snackBarShortTime(message);
    }
    //endregion
}