package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.arapp.arappmain.R;

public class SpecialServiceFragment extends Fragment
{

//    region Variable
//    endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment

//        Hooks

//        Return view
        return inflater.inflate(R.layout.fragment_special_service, container, false);
    }

//    region methods
//    Fragment navigation
//    Error/success message
//    endregion
}