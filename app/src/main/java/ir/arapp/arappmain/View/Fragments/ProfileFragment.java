package ir.arapp.arappmain.View.Fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import ir.arapp.arappmain.Util.Adapters.ProfileItemAdapter;
import ir.arapp.arappmain.Util.Services.DrawerManager;
import ir.arapp.arappmain.databinding.FragmentProfileBinding;
import ir.arapp.arappmain.viewmodel.ProfileViewModel;

public class ProfileFragment extends Fragment
{

//    region Variable
    FragmentProfileBinding fragmentProfileBinding;
    ProfileViewModel profileViewModel;
    ProfileItemAdapter profileItemAdapter;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);
//        Set View model
        profileViewModel = ViewModelProviders.of(requireActivity()).get(ProfileViewModel.class);
//        Hooks
        profileItemAdapter = new ProfileItemAdapter(fragmentProfileBinding.getRoot());
//        Set Life Cycle
        fragmentProfileBinding.setLifecycleOwner(this);
//        Recycler View Profile items
        profileViewModel.getAllProfileItems().observe(getViewLifecycleOwner(), profiles ->
        {
            profileItemAdapter.setProfileArrayList(profiles);
            setRecyclerView();
        });
//        Drawer Locked
        ((DrawerManager) requireActivity()).setDrawerLocked(true);

//        Return view
        return fragmentProfileBinding.getRoot();
    }

//    region methods
//    Set profile adapter and layout manager
    private void setRecyclerView()
    {
        fragmentProfileBinding.profileRecyclerView.setHasFixedSize(true);
//        Add spacing between 2 item
        fragmentProfileBinding.profileRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 12, 1));
        fragmentProfileBinding.profileRecyclerView.setAdapter(profileItemAdapter);
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