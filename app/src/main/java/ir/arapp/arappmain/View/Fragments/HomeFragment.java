package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mancj.materialsearchbar.MaterialSearchBar;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Services.DrawerManager;
import ir.arapp.arappmain.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements MaterialSearchBar.OnSearchActionListener
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
//        Search View Material
        fragmentHomeBinding.searchView.setArrowIcon(R.drawable.ic_arrow_forward_black_48dp);
        fragmentHomeBinding.searchView.setOnSearchActionListener(this);
        fragmentHomeBinding.searchView.setOnClickListener(view -> onSearchClick());
//        Drawer Locked
        ((DrawerManager) requireActivity()).setDrawerLocked(false);
//        Return view
        return fragmentHomeBinding.getRoot();
    }

//    region methods
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
//    region Search view bar
    private void onSearchClick()
    {
        final NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.action_homeFragment_to_searchFragment);
    }
    @Override
    public void onSearchStateChanged(boolean enabled)
    {
    }
    @Override
    public void onSearchConfirmed(CharSequence text)
    {
    }
    @Override
    public void onButtonClicked(int buttonCode)
    {
        switch (buttonCode)
        {
            case MaterialSearchBar.BUTTON_NAVIGATION:
                ((DrawerManager) requireActivity()).openDrawer();
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                fragmentHomeBinding.searchView.closeSearch();
                break;
        }
    }
//    endregion
//    Fragment navigation
//    Error/success message
//    endregion
}