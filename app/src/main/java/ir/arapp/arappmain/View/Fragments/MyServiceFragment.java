package ir.arapp.arappmain.View.Fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.MyServiceAdapter;
import ir.arapp.arappmain.Util.Adapters.NotificationAdapter;
import ir.arapp.arappmain.Util.Services.ItemClickListener;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentMyServiceBinding;
import ir.arapp.arappmain.databinding.FragmentNotificationBinding;
import ir.arapp.arappmain.viewmodel.NotificationViewModel;
import ir.arapp.arappmain.viewmodel.ServicesViewModel;

public class MyServiceFragment extends Fragment implements ItemClickListener
{

//    region Variable
    FragmentMyServiceBinding fragmentMyServiceBinding;
    private MyServiceAdapter myServiceAdapter;
    private SnackBarToast snackBarToast;
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
        fragmentMyServiceBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_service, container, false);
//        Set view model
        ServicesViewModel servicesViewModel = new ViewModelProvider(requireActivity()).get(ServicesViewModel.class);
//        Hooks
        myServiceAdapter = new MyServiceAdapter(fragmentMyServiceBinding.getRoot());
        snackBarToast = new SnackBarToast(fragmentMyServiceBinding.getRoot());
        myServiceAdapter.itemClickListener = this;
//        Set life cycle
        fragmentMyServiceBinding.setLifecycleOwner(this);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(false);
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentMyServiceBinding.profileToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentMyServiceBinding.profileToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Recycler View Services items
        servicesViewModel.getAllCategoryItems().observe(getViewLifecycleOwner(), services ->
        {
            myServiceAdapter.setServices(services);
            setRecyclerView();
        });
//        Return view
        return fragmentMyServiceBinding.getRoot();
    }

//    region methods
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
//    Set notification adapter and layout manager
    private void setRecyclerView()
    {
        fragmentMyServiceBinding.myServiceRecyclerView.setHasFixedSize(true);
    //        Add spacing between 2 item
        fragmentMyServiceBinding.myServiceRecyclerView.addItemDecoration(new
                MyServiceFragment.GridSpacingItemDecoration(2, 12, 1));
        fragmentMyServiceBinding.myServiceRecyclerView.setAdapter(myServiceAdapter);
    }
//    On click listener on each item
    @Override
    public void onItemClickListener(View view, int position, String message)
    {
        if (message.equals("delete"))
        {
            snackBarToast.snackBarShortTime("حذف سرویس");
        }
        else
        {
            snackBarToast.snackBarShortTime(message);
        }
    }
//    endregion

//    private inline class for spacing recycler view items
    private static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration
    {
//        region Variables
        final private int spanCount;
        final private int spacing;
        final private int spacing_top;
        final private boolean includeEdge;
//        endregion
//        Constructor
        public GridSpacingItemDecoration(int spanCount, int spacing_left, int spacing_top)
        {
            this.spanCount = spanCount;
            this.spacing = spacing_left;
            this.includeEdge = true;
            this.spacing_top=spacing_top;
        }
//        Get item offsets to set layout spacing
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state)
        {
//             item phases_position
            int position = parent.getChildAdapterPosition(view);
//             item column
            int column = position % spanCount;
            if (includeEdge)
            {
//                 spacing - column * ((1f / spanCount) * spacing)
                outRect.left = spacing - column * spacing / spanCount;
//                 (column + 1) * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount;
                if (position < spanCount)
                {
//                     top edge
                    outRect.top = spacing_top;
                }
//                 item bottom
                outRect.bottom = spacing_top;
            }
            else
            {
//                 column * ((1f / spanCount) * spacing)
                outRect.left = column * spacing / spanCount;
//                 spacing - (column + 1) * ((1f /    spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount)
                {
//                     item top
                    outRect.top = spacing_top;
                }
            }
        }
    }
}