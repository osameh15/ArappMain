package ir.arapp.arappmain.View.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.arappmain.radialtimepicker.ClockMode;
import com.arappmain.radialtimepicker.OnTimeResultListener;
import com.arappmain.radialtimepicker.PageData;
import com.arappmain.radialtimepicker.TimePickerBottomSheetFragment;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.SpinnerAdapter;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentAddServiceBinding;
import ir.arapp.arappmain.viewmodel.AddServiceViewModel;
import kotlin.Unit;

public class AddServiceFragment extends Fragment
{

//    region Variables
    FragmentAddServiceBinding fragmentAddServiceBinding;
    AddServiceViewModel addServiceViewModel;
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
        fragmentAddServiceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_service, container, false);
//        Set view model
        addServiceViewModel = new ViewModelProvider(requireActivity()).get(AddServiceViewModel.class);
        fragmentAddServiceBinding.setViewModel(addServiceViewModel);
//        Hooks
        timePickerBottomSheetFragment = new TimePickerBottomSheetFragment();
        snackBarToast = new SnackBarToast(fragmentAddServiceBinding.getRoot());
//        Set LifeCycle
        fragmentAddServiceBinding.setLifecycleOwner(this);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(false);
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentAddServiceBinding.profileToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentAddServiceBinding.profileToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Spinner Adapter
        spinnerAdapter();
//        Time Picker
        timePicker();
//        Return View
        return fragmentAddServiceBinding.getRoot();
    }

//    region Methods
//    Menu option
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
//    Spinner adapter
    private void spinnerAdapter()
    {
//        Set category Spinner
        addServiceViewModel.getAllCategory().observe(getViewLifecycleOwner(), category ->
        {
            SpinnerAdapter categorySpinner = new SpinnerAdapter(requireContext(), R.layout.custom_spinner_layout, category);
            categorySpinner.setDropDownViewResource(R.layout.custom_spinner_layout_dropdown);
            fragmentAddServiceBinding.categorySpinner.setAdapter(categorySpinner);
        });
//        Opening year spinner
        addServiceViewModel.getAllOpeningYear().observe(getViewLifecycleOwner(), openingYear ->
        {
            SpinnerAdapter yearAdapter = new SpinnerAdapter(requireContext(), R.layout.custom_spinner_layout, openingYear);
            yearAdapter.setDropDownViewResource(R.layout.custom_spinner_layout_dropdown);
            fragmentAddServiceBinding.openingYearSpinner.setAdapter(yearAdapter);
        });
    }
//    Time Picker
    @SuppressLint("SetTextI18n")
    private void timePicker()
    {
        Typeface typeface = ResourcesCompat.getFont(requireContext(), R.font.yekan);
        NumberFormat numberFormat = new DecimalFormat("00");
        timePickerBottomSheetFragment.set24Hour(true);
        timePickerBottomSheetFragment.setTextTypeFace(typeface);
        timePickerBottomSheetFragment.initTime(8,0,21,0);
        fragmentAddServiceBinding.setTimeActivity.setOnClickListener(v ->
        {
            timePickerBottomSheetFragment.setClockArrowMode(PageData.ClockArrow.Hour);
            timePickerBottomSheetFragment.show(getChildFragmentManager(), "tag");
        });
        timePickerBottomSheetFragment.setOnTimeResultListener((aBoolean, i, i1, i2, i3) ->
        {
            fragmentAddServiceBinding.textTimeActivity.setText("زمان شروع: " + numberFormat.format(i) + ":" + numberFormat.format(i1)
                    + "\n" + "زمان پایان: " + numberFormat.format(i2) + ":" + numberFormat.format(i3));
            return Unit.INSTANCE;
        });
    }
//    endregion
}
