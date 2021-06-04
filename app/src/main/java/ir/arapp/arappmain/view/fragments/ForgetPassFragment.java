package ir.arapp.arappmain.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.util.services.FragmentManager;
import ir.arapp.arappmain.util.services.HideShowKeyboard;
import ir.arapp.arappmain.util.services.SnackBarMessage;
import ir.arapp.arappmain.util.services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentForgetPassBinding;
import ir.arapp.arappmain.viewmodel.ForgetPassViewModel;

public class ForgetPassFragment extends Fragment implements SnackBarMessage, FragmentManager
{

//    region Variable
    FragmentForgetPassBinding fragmentForgetPassBinding;
    private SnackBarToast snackBarToast;
    private HideShowKeyboard hideShowKeyboard;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//       Inflate the layout for this fragment
        fragmentForgetPassBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_forget_pass, container, false);
//        Set View model
        ForgetPassViewModel forgetPassViewModel = new ViewModelProvider(requireActivity()).get(ForgetPassViewModel.class);
        fragmentForgetPassBinding.setViewModel(forgetPassViewModel);
        forgetPassViewModel.snackBarMessage = this;
        forgetPassViewModel.fragmentManager = this;
//        Set Life Cycle
            fragmentForgetPassBinding.setLifecycleOwner(this);
//        Hooks
        snackBarToast = new SnackBarToast(fragmentForgetPassBinding.getRoot());
        hideShowKeyboard = new HideShowKeyboard(getContext(), fragmentForgetPassBinding.getRoot());
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentForgetPassBinding.forgetPassToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentForgetPassBinding.forgetPassToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Return view
        return fragmentForgetPassBinding.getRoot();
    }

//    region methods
//    Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
        requireActivity().getViewModelStore().clear();
    }
//    Fragment navigation
    @Override
    public void navigateToFragment(String message)
    {
//        Nav Controller
        final NavController navController = Navigation.findNavController(requireView());

        if (message.equals("login"))
        {
            navController.navigate(R.id.action_forgetPassFragment_to_loginFragment);
            hideShowKeyboard.hideKeyboardFrom(true);
        }
    }
//    Check Pin View!
    @Override
    public void setFunction(String type)
    {
    }
//    Error/success message
    @Override
    public void onSuccess(String message)
    {
        snackBarToast.snackBarLongTime(message);
    }
    @Override
    public void onFailure(String message)
    {
        snackBarToast.snackBarShortTime(message);
    }
//    endregion
}