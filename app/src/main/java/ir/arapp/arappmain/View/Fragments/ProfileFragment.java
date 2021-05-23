package ir.arapp.arappmain.View.Fragments;

import android.content.Intent;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.ProfileItemAdapter;
import ir.arapp.arappmain.Util.Services.FragmentManager;
import ir.arapp.arappmain.Util.Services.ItemClickListener;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SessionManager;
import ir.arapp.arappmain.Util.Services.SnackBarMessage;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.View.Activities.AuthActivity;
import ir.arapp.arappmain.View.Activities.HomeActivity;
import ir.arapp.arappmain.databinding.FragmentProfileBinding;
import ir.arapp.arappmain.viewmodel.ProfileViewModel;

public class ProfileFragment extends Fragment implements SnackBarMessage, FragmentManager, ItemClickListener
{

//    region Variable
    FragmentProfileBinding fragmentProfileBinding;
    private ProfileItemAdapter profileItemAdapter;
    private SnackBarToast snackBarToast;
    private SessionManager sessionManager;
//    endregion

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
//        Set View model
        ProfileViewModel profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        fragmentProfileBinding.setViewModel(profileViewModel);
        profileViewModel.snackBarMessage = this;
        profileViewModel.fragmentManager = this;
//        Hooks
        profileItemAdapter = new ProfileItemAdapter(fragmentProfileBinding.getRoot());
        snackBarToast = new SnackBarToast(fragmentProfileBinding.getRoot());
        sessionManager = new SessionManager(requireContext());
        profileItemAdapter.itemClickListener = this;
//        Set Life Cycle
        fragmentProfileBinding.setLifecycleOwner(this);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(true);
//        Recycler View Profile items
        profileViewModel.getAllProfileItems().observe(getViewLifecycleOwner(), profiles ->
        {
            profileItemAdapter.setProfileArrayList(profiles);
            setRecyclerView();
        });
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

//        Fragment navigation
    @Override
    public void navigateToFragment(String message)
    {
        final NavController navController = Navigation.findNavController(requireView());
        switch (message)
        {
            case "editUser":
                navController.navigate(R.id.action_profileFragment_to_editUserFragment);
                break;
            case "addService":
                navController.navigate(R.id.action_profileFragment_to_addServiceFragment);
                break;
            case "myService":
                navController.navigate(R.id.action_profileFragment_to_myServiceFragment);
                break;
        }
    }
//    Set methods for specific function
    @Override
    public void setFunction(String type)
    {
    }
//    Error/success message
    @Override
    public void onSuccess(String message)
    {
        snackBarToast.snackBarLongTime(message, requireActivity().findViewById(R.id.bottomNavigationView));
    }
    @Override
    public void onFailure(String message)
    {
        snackBarToast.snackBarLongTime(message);
    }
//    On Click Listener on Recycler View items
    @Override
    public void onItemClickListener(View view, int position, String message)
    {
        Intent intent = new Intent(requireContext(), AuthActivity.class);
        switch (position)
        {
            case 1:
                navigateToFragment("addService");
                break;
            case 3:
                navigateToFragment("myService");
                break;
            case 8:
                sessionManager.setLogin(false);
                requireActivity().finish();
                startActivity(intent);
                break;
            default:
                onSuccess(message);
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