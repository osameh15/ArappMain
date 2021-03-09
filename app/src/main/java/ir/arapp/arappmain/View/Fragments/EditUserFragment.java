package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.databinding.FragmentEditUserBinding;

public class EditUserFragment extends Fragment
{
//    region Variables
    FragmentEditUserBinding fragmentEditUserBinding;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentEditUserBinding = FragmentEditUserBinding.inflate(inflater, container, false);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(false);
//        Return View
        return fragmentEditUserBinding.getRoot();
    }

//    region Methods
//    endregion
}
