package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.arapp.arappmain.R;

public class CategoryFragment extends Fragment
{

//    region Variable
//    endregion

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//         Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

//        Hooks

        return view;
    }

//    region methods
//    fragment navigation
//    error/success message
//    endregion
}