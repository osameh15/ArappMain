package ir.arapp.arappmain.View.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.arappmain.radialtimepicker.PageData;
import com.arappmain.radialtimepicker.TimePickerBottomSheetFragment;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentEditServiceBinding;
import ir.arapp.arappmain.viewmodel.EditServiceViewModel;
import kotlin.Unit;

public class EditServiceFragment extends Fragment
{

//    region Variables
    FragmentEditServiceBinding fragmentEditServiceBinding;
    EditServiceViewModel editServiceViewModel;
    SnackBarToast snackBarToast;
    private TimePickerBottomSheetFragment timePickerBottomSheetFragment;
//    endregion

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentEditServiceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_service, container, false);
//        Set view model
        editServiceViewModel = new ViewModelProvider(requireActivity()).get(EditServiceViewModel.class);
        fragmentEditServiceBinding.setViewModel(editServiceViewModel);
//        Hooks
        snackBarToast = new SnackBarToast(fragmentEditServiceBinding.getRoot());
        timePickerBottomSheetFragment = new TimePickerBottomSheetFragment();
//        Set LifeCycle
        fragmentEditServiceBinding.setLifecycleOwner(this);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(false);
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentEditServiceBinding.profileToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentEditServiceBinding.profileToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Time Picker
        timePicker();
//        Return View
        return fragmentEditServiceBinding.getRoot();
    }

//    region Methods
//    Option menu and manage it
    @Override
    public void onPrepareOptionsMenu(@NonNull @NotNull Menu menu)
    {
        super.onPrepareOptionsMenu(menu);
        menu.setGroupVisible(R.id.bottomNavigationMenu, false);
    }
//    Back button navigation
    private void onNavigateUp()
{
    requireActivity().onBackPressed();
}
//    Time Picker
    @SuppressLint("SetTextI18n")
    private void timePicker()
    {
        Typeface typeface = ResourcesCompat.getFont(requireContext(), R.font.iransans_farsi_num);
        NumberFormat numberFormat = new DecimalFormat("00");
        timePickerBottomSheetFragment.set24Hour(true);
        timePickerBottomSheetFragment.setTextTypeFace(typeface);
        timePickerBottomSheetFragment.initTime(8,0,21,0);
        fragmentEditServiceBinding.changeTimeActivity.setOnClickListener(v ->
        {
            timePickerBottomSheetFragment.setClockArrowMode(PageData.ClockArrow.Hour);
            timePickerBottomSheetFragment.show(getChildFragmentManager(), "tag");
        });
        timePickerBottomSheetFragment.setOnTimeResultListener((aBoolean, i, i1, i2, i3) ->
        {
            fragmentEditServiceBinding.textTimeActivity.setText("آغاز ارائه خدمات ساعت: " + numberFormat.format(i) + ":" + numberFormat.format(i1)
                    + "\n" + "پایان ارائه خدمات ساعت: " + numberFormat.format(i2) + ":" + numberFormat.format(i3));
            return Unit.INSTANCE;
        });
    }
//    endregion
}
