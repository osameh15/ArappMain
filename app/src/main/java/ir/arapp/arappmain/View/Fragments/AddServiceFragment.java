package ir.arapp.arappmain.View.Fragments;

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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Field;
import java.util.Objects;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.SpinnerAdapter;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentAddServiceBinding;
import ir.arapp.arappmain.viewmodel.AddServiceViewModel;

public class AddServiceFragment extends Fragment
{

//    region Variables
    FragmentAddServiceBinding fragmentAddServiceBinding;
    AddServiceViewModel addServiceViewModel;
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
        fragmentAddServiceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_service, container, false);
//        Set view model
        addServiceViewModel = new ViewModelProvider(requireActivity()).get(AddServiceViewModel.class);
        fragmentAddServiceBinding.setViewModel(addServiceViewModel);
//        Hooks
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
//    Option menu and manage it
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
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
    private void timePicker()
    {

    }
//    endregion
}
