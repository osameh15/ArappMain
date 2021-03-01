package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.Objects;

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
        fragmentHomeBinding.searchView.setArrowIcon(requireActivity().getResources().
                getIdentifier("ic_baseline_arrow_forward_48", "drawable", requireActivity().getPackageName()));
//        Drawer Locked
        ((DrawerManager) requireActivity()).setDrawerLocked(false);

//        Return view
        return fragmentHomeBinding.getRoot();
    }

//    region methods
//    region Search view bar
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
                Log.d("navigation", "click");
                ((DrawerManager) requireActivity()).openDrawer();
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                fragmentHomeBinding.searchView.closeSearch();
                break;
        }
    }
//    Fragment navigation
//    Error/success message
//    endregion
}