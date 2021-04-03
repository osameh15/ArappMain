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

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.SpinnerAdapter;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentAddServiceBinding;
import ir.arapp.arappmain.databinding.FragmentEditUserBinding;
import ir.arapp.arappmain.viewmodel.EditUserViewModel;

public class AddServiceFragment extends Fragment
{
//    region Variables
    FragmentAddServiceBinding fragmentAddServiceBinding;
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
        fragmentAddServiceBinding = FragmentAddServiceBinding.inflate(inflater, container, false);
//        Set view model
//        Hooks
//        Set LifeCycle
        fragmentAddServiceBinding.setLifecycleOwner(this);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(false);
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentAddServiceBinding.profileToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentAddServiceBinding.profileToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
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
//    Spinner adapter
//    Back button navigation
    private void onNavigateUp()
{
    requireActivity().onBackPressed();
}
//    endregion
}
