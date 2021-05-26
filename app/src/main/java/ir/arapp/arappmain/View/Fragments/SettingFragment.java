package ir.arapp.arappmain.View.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.MyServiceAdapter;
import ir.arapp.arappmain.Util.Services.ItemClickListener;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentMyServiceBinding;
import ir.arapp.arappmain.databinding.FragmentSettingBinding;
import ir.arapp.arappmain.viewmodel.ServicesViewModel;

public class SettingFragment extends Fragment
{

//    region Variable
    FragmentSettingBinding fragmentSettingBinding;
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
        fragmentSettingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
//        Set view model
//        Hooks
        snackBarToast = new SnackBarToast(fragmentSettingBinding.getRoot());
//        Set life cycle
        fragmentSettingBinding.setLifecycleOwner(this);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(false);
//        Toolbar
        ((AppCompatActivity)requireActivity()).setSupportActionBar(fragmentSettingBinding.profileToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fragmentSettingBinding.profileToolbar.setNavigationOnClickListener(view1 -> onNavigateUp());
//        Return view
        return fragmentSettingBinding.getRoot();
    }

//    region methods
//    Option menu and manage it
    @Override
    public void onPrepareOptionsMenu(@NonNull @NotNull Menu menu)
    {
        super.onPrepareOptionsMenu(menu);
        menu.setGroupVisible(R.id.bottomNavigationMenu, false);
    }
//    Back button navigation
    private void onNavigateUp()
    {
        requireActivity().onBackPressed();
    }
//    endregion

}