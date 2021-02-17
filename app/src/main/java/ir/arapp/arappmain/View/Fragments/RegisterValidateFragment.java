package ir.arapp.arappmain.View.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.chaos.view.PinView;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.CheckPinView;
import ir.arapp.arappmain.Util.Services.HideShowKeyboard;
import ir.arapp.arappmain.Util.Services.NavigateFragment;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentValidateRegisterBinding;
import ir.arapp.arappmain.viewmodel.RegisterValidateViewModel;

public class RegisterValidateFragment extends Fragment implements SnackBarMessage, NavigateFragment, CheckPinView
{

//    region Variables
    FragmentValidateRegisterBinding fragmentValidateRegisterBinding;
    RegisterValidateViewModel registerValidateViewModel;
    private SnackBarToast snackBarToast;
    private HideShowKeyboard hideShowKeyboard;
//    endregion

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//         Inflate the layout for this fragment
         fragmentValidateRegisterBinding = FragmentValidateRegisterBinding.inflate(inflater, container, false);
//        set view model
        registerValidateViewModel = ViewModelProviders.of(requireActivity()).get(RegisterValidateViewModel.class);
        fragmentValidateRegisterBinding.setViewModel(registerValidateViewModel);
        registerValidateViewModel.navigateFragment = this;
        registerValidateViewModel.snackBarMessage = this;
        registerValidateViewModel.checkPinView = this;
//        Life cycle owner
        fragmentValidateRegisterBinding.setLifecycleOwner(this);
//        Hooks
        snackBarToast = new SnackBarToast(fragmentValidateRegisterBinding.getRoot());
        hideShowKeyboard = new HideShowKeyboard(getContext(), fragmentValidateRegisterBinding.getRoot());
//        Toolbar
        ((AppCompatActivity) requireActivity()).setSupportActionBar(fragmentValidateRegisterBinding.registerToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentValidateRegisterBinding.registerToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        pin view animation
        fragmentValidateRegisterBinding.pinView.setAnimationEnable(true);
        pinViewTryAgainLineColor();
//        timer
        registerValidateViewModel.currentTime.observe(fragmentValidateRegisterBinding.getLifecycleOwner(), this::setTimer);
//        return view
        return fragmentValidateRegisterBinding.getRoot();
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
        fragmentValidateRegisterBinding.timer.setText(time);
        if (s < 10)
        {
            fragmentValidateRegisterBinding.timer.setTextColor(getResources().getColor(R.color.notificationColorRed));
        }
    }
//    check pin code error
    @Override
    public void checkPin(String type)
    {
        if (type.equals("error"))
        {
            fragmentValidateRegisterBinding.pinView.setLineColor(
                    ResourcesCompat.getColor(getResources(), R.color.notificationColorRed, requireActivity().getTheme()));
        }
        if (type.equals("resend"))
        {
            fragmentValidateRegisterBinding.timer.setTextColor(getResources().getColor(R.color.colorAccentDark));
            fragmentValidateRegisterBinding.pinView.setText("");
            fragmentValidateRegisterBinding.pinView.setLineColor(
                    ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, requireActivity().getTheme()));
        }
    }
//    Pin View change Line Color
    private void pinViewTryAgainLineColor()
    {
        fragmentValidateRegisterBinding.pinView.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                fragmentValidateRegisterBinding.pinView.setLineColor(
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
        final NavController navController = Navigation.findNavController(getView());

        if (message.equals("phoneRegister"))
        {
            navController.navigate(R.id.action_registerValidateFragment_to_registerPhoneFragment);
            getActivity().getViewModelStore().clear();
        }
        if (message.equals("register"))
        {
            navController.navigate(R.id.action_registerValidateFragment_to_registerFragment);
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