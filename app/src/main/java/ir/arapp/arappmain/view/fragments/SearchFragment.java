package ir.arapp.arappmain.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancj.materialsearchbar.MaterialSearchBar;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.util.services.NavigationManager;
import ir.arapp.arappmain.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment implements MaterialSearchBar.OnSearchActionListener
{

//    region Variables
    FragmentSearchBinding fragmentSearchBinding;
//    endregion

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//         Inflate the layout for this fragment
        fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
//        Search Controller
        fragmentSearchBinding.searchView.openSearch();
        fragmentSearchBinding.searchView.setOnSearchActionListener(this);
        fragmentSearchBinding.searchView.setArrowIcon(R.drawable.ic_arrow_forward_black_48dp);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(true);
//        Return view
        return fragmentSearchBinding.getRoot();
    }

//    region Methods
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
        if (buttonCode == MaterialSearchBar.BUTTON_BACK)
        {
            final NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_searchFragment_to_homeFragment);
        }
    }
//    endregion
}