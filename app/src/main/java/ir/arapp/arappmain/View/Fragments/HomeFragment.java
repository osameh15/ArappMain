package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import ir.arapp.arappmain.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment
{

//    region Variable
    FragmentHomeBinding fragmentHomeBinding;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
//        Hooks

//        Return view
        return fragmentHomeBinding.getRoot();
    }

//    region methods
//    Fragment navigation
//    Error/success message
//    endregion
}