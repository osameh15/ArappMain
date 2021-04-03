package ir.arapp.arappmain.View.Fragments;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.SpinnerAdapter;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentEditUserBinding;
import ir.arapp.arappmain.viewmodel.EditUserViewModel;

public class EditUserFragment extends Fragment
{
//    region Variables
    FragmentEditUserBinding fragmentEditUserBinding;
    EditUserViewModel editUserViewModel;
    SnackBarToast snackBarToast;
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
        fragmentEditUserBinding = FragmentEditUserBinding.inflate(inflater, container, false);
//        Set view model
        editUserViewModel = ViewModelProviders.of(requireActivity()).get(EditUserViewModel.class);
//        Hooks
        snackBarToast = new SnackBarToast(fragmentEditUserBinding.getRoot());
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
//    Back button navigation
    private void onNavigateUp()
{
    requireActivity().onBackPressed();
}
//    endregion
}
