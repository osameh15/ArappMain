package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.SpinnerAdapter;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentAddServiceBinding;
import ir.arapp.arappmain.databinding.FragmentEditServiceBinding;
import ir.arapp.arappmain.viewmodel.AddServiceViewModel;
import ir.arapp.arappmain.viewmodel.EditServiceViewModel;

public class EditServiceFragment extends Fragment
{

//    region Variables
    FragmentEditServiceBinding fragmentEditServiceBinding;
    EditServiceViewModel editServiceViewModel;
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
        fragmentEditServiceBinding = FragmentEditServiceBinding.inflate(inflater, container, false);
//        Set view model
        editServiceViewModel = ViewModelProviders.of(requireActivity()).get(EditServiceViewModel.class);
        fragmentEditServiceBinding.setViewModel(editServiceViewModel);
//        Hooks
        snackBarToast = new SnackBarToast(fragmentEditServiceBinding.getRoot());
//        Set LifeCycle
        fragmentEditServiceBinding.setLifecycleOwner(this);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(false);
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentEditServiceBinding.profileToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentEditServiceBinding.profileToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Return View
        return fragmentEditServiceBinding.getRoot();
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
//    endregion
}
