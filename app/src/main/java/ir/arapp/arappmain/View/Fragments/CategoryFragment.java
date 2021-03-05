package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import ir.arapp.arappmain.Model.CategoryItem;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.CategoryItemAdapter;
import ir.arapp.arappmain.Util.Services.DrawerManager;
import ir.arapp.arappmain.databinding.FragmentCategoryBinding;
import ir.arapp.arappmain.viewmodel.CategoryViewModel;

public class CategoryFragment extends Fragment
{

//    region Variable
    FragmentCategoryBinding fragmentCategoryBinding;
    CategoryViewModel categoryViewModel;
    CategoryItemAdapter categoryItemAdapter;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentCategoryBinding = FragmentCategoryBinding.inflate(inflater, container, false);
//        Set View model
        categoryViewModel = ViewModelProviders.of(requireActivity()).get(CategoryViewModel.class);
//        Hooks
        categoryItemAdapter = new CategoryItemAdapter(fragmentCategoryBinding.getRoot());
//        Set life cycle
        fragmentCategoryBinding.setLifecycleOwner(this);
//        Recycler view Category items
        categoryViewModel.getAllCategoryItems().observe(getViewLifecycleOwner(), categoryItems ->
        {
            categoryItemAdapter.setCategoryItems(categoryItems);
            setRecyclerView();
        });
//        Drawer Locked
        ((DrawerManager) requireActivity()).setDrawerLocked(true);
//        Return view
        return fragmentCategoryBinding.getRoot();
    }


//    region methods
    private void setRecyclerView()
    {
        fragmentCategoryBinding.categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        fragmentCategoryBinding.categoryRecyclerView.setHasFixedSize(true);
        fragmentCategoryBinding.categoryRecyclerView.setAdapter(categoryItemAdapter);
    }
//    Fragment navigation
//    Error/success message
//    endregion
}