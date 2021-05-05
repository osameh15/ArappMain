package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.CategoryItemAdapter;
import ir.arapp.arappmain.Util.Services.ItemClickListener;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentCategoryBinding;
import ir.arapp.arappmain.viewmodel.CategoryViewModel;

public class CategoryFragment extends Fragment implements ItemClickListener
{

//    region Variable
    FragmentCategoryBinding fragmentCategoryBinding;
    private CategoryItemAdapter categoryItemAdapter;
    private SnackBarToast snackBarToast;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
//        Set View model
        CategoryViewModel categoryViewModel = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
//        Hooks
        categoryItemAdapter = new CategoryItemAdapter(fragmentCategoryBinding.getRoot());
        snackBarToast = new SnackBarToast(fragmentCategoryBinding.getRoot());
        categoryItemAdapter.itemClickListener = this;
//        Set life cycle
        fragmentCategoryBinding.setLifecycleOwner(this);
//        Recycler view Category items
        categoryViewModel.getAllCategoryItems().observe(getViewLifecycleOwner(), categoryItems ->
        {
            categoryItemAdapter.setCategories(categoryItems);
            setRecyclerView();
        });
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(true);
//        Return view
        return fragmentCategoryBinding.getRoot();
    }

//    region methods
//    Set category adapter and layout manager
    private void setRecyclerView()
    {
        fragmentCategoryBinding.categoryRecyclerView.setHasFixedSize(true);
        fragmentCategoryBinding.categoryRecyclerView.setAdapter(categoryItemAdapter);
    }
//    On Click Listener on Recycler View items
    @Override
    public void onItemClickListener(View view, int position, String message)
    {
        snackBarToast.snackBarLongTime(message, requireActivity().findViewById(R.id.bottomNavigationView));
    }
//    endregion
}