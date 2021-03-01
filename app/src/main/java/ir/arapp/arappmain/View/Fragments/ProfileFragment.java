package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.DrawerManager;
import ir.arapp.arappmain.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment
{

//    region Variable
    FragmentProfileBinding fragmentProfileBinding;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);

//        Hooks
//        Drawer Locked
        ((DrawerManager) requireActivity()).setDrawerLocked(true);

//        Return view
        return fragmentProfileBinding.getRoot();
    }

//    region methods
//    Fragment navigation
//    Error/success message
//    endregion
}