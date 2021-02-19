package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import java.util.Objects;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.HideShowKeyboard;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentPhoneForgetPassBinding;
import ir.arapp.arappmain.viewmodel.ForgetPassPhoneViewModel;

public class ForgetPassPhoneFragment extends Fragment implements SnackBarMessage, NavigateFragment
{

//    region Variable
    FragmentPhoneForgetPassBinding fragmentPhoneForgetPassBinding;
    ForgetPassPhoneViewModel forgetPassPhoneViewModel;
    private HideShowKeyboard hideShowKeyboard;
    private SnackBarToast snackBarToast;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentPhoneForgetPassBinding = FragmentPhoneForgetPassBinding.inflate(inflater, container, false);
//        Set view model
        forgetPassPhoneViewModel = ViewModelProviders.of(this).get(ForgetPassPhoneViewModel.class);
        fragmentPhoneForgetPassBinding.setViewModel(forgetPassPhoneViewModel);
        forgetPassPhoneViewModel.navigateFragment = this;
        forgetPassPhoneViewModel.snackBarMessage = this;
//        Hooks
        snackBarToast = new SnackBarToast(fragmentPhoneForgetPassBinding.getRoot());
        hideShowKeyboard = new HideShowKeyboard(getContext(), fragmentPhoneForgetPassBinding.getRoot());
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentPhoneForgetPassBinding.forgetPassToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentPhoneForgetPassBinding.forgetPassToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Return view
        return fragmentPhoneForgetPassBinding.getRoot();
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
    }
//    Fragment navigation
    @Override
    public void navigateToFragment(String message)
    {
//        Nav Controller
        final NavController navController = Navigation.findNavController(requireView());

        if (message.equals("validate"))
        {
            navController.navigate(R.id.action_forgetPassPhoneFragment_to_forgetPassValidateFragment);
            hideShowKeyboard.hideKeyboardFrom(true);
        }
    }
//    Error/success message
    @Override
    public void onSuccess(String message)
    {
        snackBarToast.snackBarShortTime(message);
    }
    @Override
    public void onFailure(String message)
    {
        snackBarToast.snackBarShortTime(message);
    }
//    endregion
}