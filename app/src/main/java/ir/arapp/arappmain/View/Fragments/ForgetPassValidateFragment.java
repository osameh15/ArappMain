package ir.arapp.arappmain.View.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.Objects;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.CheckPinView;
import ir.arapp.arappmain.Util.Services.HideShowKeyboard;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentValidateForgetPassBinding;
import ir.arapp.arappmain.viewmodel.ForgetPassValidateViewModel;

public class ForgetPassValidateFragment extends Fragment implements SnackBarMessage, NavigateFragment, CheckPinView
{

//    region Variables
    FragmentValidateForgetPassBinding fragmentValidateForgetPassBinding;
    ForgetPassValidateViewModel forgetPassValidateViewModel;
    private SnackBarToast snackBarToast;
    private HideShowKeyboard hideShowKeyboard;
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
        fragmentValidateForgetPassBinding = FragmentValidateForgetPassBinding.inflate(inflater, container, false);
//        set view model
        forgetPassValidateViewModel = ViewModelProviders.of(requireActivity()).get(ForgetPassValidateViewModel.class);
        fragmentValidateForgetPassBinding.setViewModel(forgetPassValidateViewModel);
        forgetPassValidateViewModel.snackBarMessage = this;
        forgetPassValidateViewModel.navigateFragment = this;
        forgetPassValidateViewModel.checkPinView = this;
//        Life cycle owner
        fragmentValidateForgetPassBinding.setLifecycleOwner(this);
//        Hooks
        snackBarToast = new SnackBarToast(fragmentValidateForgetPassBinding.getRoot());
        hideShowKeyboard = new HideShowKeyboard(getContext(), fragmentValidateForgetPassBinding.getRoot());
//        Toolbar
        ((AppCompatActivity) requireActivity()).setSupportActionBar(fragmentValidateForgetPassBinding.forgetPassToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentValidateForgetPassBinding.forgetPassToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
    //        pin view animation
        fragmentValidateForgetPassBinding.pinView.setAnimationEnable(true);
        pinViewTryAgainLineColor();
//        timer
        forgetPassValidateViewModel.currentTime.observe(Objects.requireNonNull(fragmentValidateForgetPassBinding.getLifecycleOwner()), this::setTimer);
//        return view
        return fragmentValidateForgetPassBinding.getRoot();
    }

//    region methods
//    Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
        hideShowKeyboard.hideKeyboardFrom(true);
    }
    //    set timer text and color
    @SuppressLint("SetTextI18n")
    private void setTimer(Long s)
    {
        long min = s/60;
        long sec = s%60;
        @SuppressLint("DefaultLocale") String time = String.format("%02d:%02d", min, sec);
        fragmentValidateForgetPassBinding.timer.setText(time);
        if (s < 10)
        {
            fragmentValidateForgetPassBinding.timer.setTextColor(getResources().getColor(R.color.notificationColorRed));
        }
    }
    //    check pin code error
    @Override
    public void checkPin(String type)
    {
        if (type.equals("error"))
        {
            fragmentValidateForgetPassBinding.pinView.setLineColor(
                    ResourcesCompat.getColor(getResources(), R.color.notificationColorRed, requireActivity().getTheme()));
        }
        if (type.equals("resend"))
        {
            fragmentValidateForgetPassBinding.timer.setTextColor(getResources().getColor(R.color.colorAccentDark));
            fragmentValidateForgetPassBinding.pinView.setText("");
            fragmentValidateForgetPassBinding.pinView.setLineColor(
                    ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, requireActivity().getTheme()));
        }
    }
    //    Pin View change Line Color
    private void pinViewTryAgainLineColor()
    {
        fragmentValidateForgetPassBinding.pinView.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                fragmentValidateForgetPassBinding.pinView.setLineColor(
                        ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, requireActivity().getTheme()));
            }
            @Override
            public void afterTextChanged(Editable editable)
            {
            }
        });
    }
//    fragment navigation
    @Override
    public void navigateToFragment(String message)
    {
//        Nav Controller
        final NavController navController = Navigation.findNavController(fragmentValidateForgetPassBinding.getRoot());

        if (message.equals("forgetPass"))
        {
            navController.navigate(R.id.action_forgetPassValidateFragment_to_forgetPassFragment);
        }
        if (message.equals("phone"))
        {
            navController.navigate(R.id.action_forgetPassValidateFragment_to_forgetPassPhoneFragment);
            requireActivity().getViewModelStore().clear();
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