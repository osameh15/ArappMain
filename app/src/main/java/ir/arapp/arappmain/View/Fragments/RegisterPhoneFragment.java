package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.HideShowKeyboard;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentPhoneRegisterBinding;
import ir.arapp.arappmain.viewmodel.RegisterPhoneViewModel;

public class RegisterPhoneFragment extends Fragment implements NavigateFragment, SnackBarMessage
{

//    region Variable
    FragmentPhoneRegisterBinding fragmentPhoneRegisterBinding;
    private SnackBarToast snackBarToast;
    private HideShowKeyboard hideKeyboardFrom;
    RegisterPhoneViewModel registerPhoneViewModel;
//    endregion

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//         Inflate the layout for this fragment
        fragmentPhoneRegisterBinding = FragmentPhoneRegisterBinding.inflate(inflater, container, false);
//        set view model
        registerPhoneViewModel = ViewModelProviders.of(requireActivity()).get(RegisterPhoneViewModel.class);
        fragmentPhoneRegisterBinding.setViewModel(registerPhoneViewModel);
        registerPhoneViewModel.navigateFragment = this;
        registerPhoneViewModel.snackBarMessage = this;
//        Hooks
        snackBarToast = new SnackBarToast(fragmentPhoneRegisterBinding.getRoot());
        hideKeyboardFrom = new HideShowKeyboard(getContext(), fragmentPhoneRegisterBinding.getRoot());
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentPhoneRegisterBinding.registerToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        OnClick
        fragmentPhoneRegisterBinding.registerToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        return view
        return fragmentPhoneRegisterBinding.getRoot();
    }

//    region methods
//    On resume
    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
//    Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
        hideKeyboardFrom.hideKeyboardFrom(true);
    }
//    fragment navigation
    @Override
    public void navigateToFragment(String message)
    {
//        Nav Controller
        final NavController navController = Navigation.findNavController(getView());

        if (message.equals("login"))
        {
            navController.navigate(R.id.action_registerPhoneFragment_to_loginFragment);
            requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            hideKeyboardFrom.hideKeyboardFrom(true);
        }
        if (message.equals("validate"))
        {
            navController.navigate(R.id.action_registerPhoneFragment_to_registerValidateFragment);
            hideKeyboardFrom.hideKeyboardFrom(true);
        }
    }
//    error/success message
    @Override
    public void onSuccess(String message)
    {
    }
    @Override
    public void onFailure(String message)
    {
        snackBarToast.snackBarShortTime(message);
    }
//    endregion
}