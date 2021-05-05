package ir.arapp.arappmain.View.Fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.SpinnerAdapter;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentEditUserBinding;
import ir.arapp.arappmain.viewmodel.EditUserViewModel;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.api.PersianPickerDate;
import ir.hamsaa.persiandatepicker.api.PersianPickerListener;

public class EditUserFragment extends Fragment
{
//    region Variables
    FragmentEditUserBinding fragmentEditUserBinding;
    EditUserViewModel editUserViewModel;
    SnackBarToast snackBarToast;
    private Typeface typeface;
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
        fragmentEditUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_user, container, false);
//        Set view model
        editUserViewModel = new ViewModelProvider(requireActivity()).get(EditUserViewModel.class);
        fragmentEditUserBinding.setViewModel(editUserViewModel);
//        Hooks
        snackBarToast = new SnackBarToast(fragmentEditUserBinding.getRoot());
        typeface = ResourcesCompat.getFont(requireContext(), R.font.iransans_bold);
//        Set LifeCycle
        fragmentEditUserBinding.setLifecycleOwner(this);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(false);
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentEditUserBinding.profileToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentEditUserBinding.profileToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Spinner adapter
        spinnerAdapter();
//        Set Birthday
        fragmentEditUserBinding.setBirthday.setOnClickListener(v -> setBirthday());
//        Return View
        return fragmentEditUserBinding.getRoot();
    }

//    region Methods
//    Option menu and manage it
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
//    Spinner adapter
    private void spinnerAdapter()
    {
//        Edu Spinner
        editUserViewModel.getAllEduList().observe(getViewLifecycleOwner(), edu ->
        {
            SpinnerAdapter eduAdapter = new SpinnerAdapter(requireContext(), R.layout.custom_spinner_layout, edu);
            eduAdapter.setDropDownViewResource(R.layout.custom_spinner_layout_dropdown);
            fragmentEditUserBinding.educationSpinner.setAdapter(eduAdapter);
        });
//        Province Spinner
        editUserViewModel.getAllProvinceList().observe(getViewLifecycleOwner(), province ->
        {
            SpinnerAdapter provinceAdapter = new SpinnerAdapter(requireContext(), R.layout.custom_spinner_layout, province);
            provinceAdapter.setDropDownViewResource(R.layout.custom_spinner_layout_dropdown);
            fragmentEditUserBinding.provinceSpinner.setAdapter(provinceAdapter);
        });
//        City Spinner
        fragmentEditUserBinding.provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position == 1)
                {
                    editUserViewModel.setCityList(1);
                }
                else if (position == 2)
                {
                    editUserViewModel.setCityList(2);
                }
                else
                {
                    editUserViewModel.setCityList(0);
                }
                editUserViewModel.getAllCityList().observe(getViewLifecycleOwner(), city ->
                {
                    SpinnerAdapter cityAdapter = new SpinnerAdapter(requireContext(), R.layout.custom_spinner_layout, city);
                    cityAdapter.setDropDownViewResource(R.layout.custom_spinner_layout_dropdown);
                    fragmentEditUserBinding.citySpinner.setAdapter(cityAdapter);
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
    }
//    set Birthday
    private void setBirthday()
    {
        PersianDatePickerDialog persianDatePickerDialog = new PersianDatePickerDialog(requireContext());
        persianDatePickerDialog
            .setPositiveButtonString("تایید")
            .setNegativeButton("صرف نظر")
            .setTodayButton("امروز")
            .setTodayButtonVisible(true)
            .setMinYear(1300)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setInitDate(1330, 1, 15)
            .setActionTextColor(Color.GRAY)
            .setTypeFace(typeface)
            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setShowInBottomSheet(true)
            .setListener(new PersianPickerListener()
            {
                @Override
                public void onDateSelected(@NotNull PersianPickerDate persianPickerDate)
                {
                    fragmentEditUserBinding.textBirthday.setText(persianPickerDate.getPersianLongDate());
                }
                @Override
                public void onDismissed()
                {
                }
            });
        persianDatePickerDialog.show();
    }
//    Back button navigation
    private void onNavigateUp()
{
    requireActivity().onBackPressed();
}
//    endregion
}
