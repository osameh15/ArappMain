package ir.arapp.arappmain.view.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.madapps.liquid.LiquidRefreshLayout;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.zhpan.bannerview.BannerViewPager;

import ir.arapp.arappmain.model.Banner;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.util.adapter.BannerViewAdapter;
import ir.arapp.arappmain.util.adapter.BannerViewHolder;

import ir.arapp.arappmain.util.adapter.HighOrderServicesAdapter;
import ir.arapp.arappmain.util.adapter.HomeFragmentMainAdapter;
import ir.arapp.arappmain.util.services.NavigationManager;
import ir.arapp.arappmain.util.services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentHomeBinding;
import ir.arapp.arappmain.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment implements MaterialSearchBar.OnSearchActionListener {

    //    region Variable
    FragmentHomeBinding fragmentHomeBinding;
    HomeViewModel homeViewModel;
    BannerViewPager<Banner, BannerViewHolder> bannerViewPager;
    BannerViewAdapter bannerViewAdapter;
    SnackBarToast snackBarToast;
    //
//    SingleDirectionRecyclerView mainRecyclerView;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Inflate the layout for this fragment
//        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false);
//        Set view model
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
//        Hooks
        bannerViewAdapter = new BannerViewAdapter();
        snackBarToast = new SnackBarToast(fragmentHomeBinding.getRoot());
//        bannerViewPager = fragmentHomeBinding.getRoot().findViewById(R.id.bannerView);
//        Search View Material
        fragmentHomeBinding.searchView.setArrowIcon(R.drawable.ic_arrow_forward_black_48dp);
        fragmentHomeBinding.searchView.setOnSearchActionListener(this);
        fragmentHomeBinding.searchView.setOnClickListener(view -> onSearchClick());
//HighOrderServiceRecyclerView

//        mainRecyclerView = fragmentHomeBinding.getRoot().findViewById(R.id.main_recycler_view);
        HomeFragmentMainAdapter adapter = new HomeFragmentMainAdapter(requireContext());
        for (int i = 0; i < 6; i++) {
            adapter.recyclerViewPropertiesList.add(new HomeFragmentMainAdapter.RecyclerViewProperties(requireContext()));
        }
        adapter.notifyDataSetChanged();
        fragmentHomeBinding.mainRecyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setRecycleChildrenOnDetach(true);
        fragmentHomeBinding.mainRecyclerView.setLayoutManager(linearLayoutManager);

//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(false);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(true);
//        Set banner news
        homeViewModel.getAllBannerItems().observe(getViewLifecycleOwner(), bannerItems ->
        {
//            bannerViewAdapter.setBanners(bannerItems);
//            bannerAdapter();
        });
        homeViewModel.getHighOrderServicesAdapter().observe(getViewLifecycleOwner(),highOrderServicesAdapter1 -> {
//            highOrderRecyclerView.setAdapter(highOrderServicesAdapter1);
//            highOrderRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));
        });
//        Refresh layout
        liquidRefreshLayout();
//        Return view
        return fragmentHomeBinding.getRoot();
    }



    //    region methods
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //    region Search view bar
    private void onSearchClick() {
        final NavController navController = Navigation.findNavController(requireView());
        navController.navigate(R.id.action_homeFragment_to_searchFragment);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_NAVIGATION:
                ((NavigationManager) requireActivity()).openDrawer();
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                fragmentHomeBinding.searchView.closeSearch();
                break;
        }
    }

    //    Banner View Adapter
    private void bannerAdapter() {

        bannerViewPager
                .setOffScreenPageLimit(2)
                .setLifecycleRegistry(getLifecycle())
                .setAdapter(bannerViewAdapter)
                .registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                    }
                })
                .setOnPageClickListener(position ->
                {
                    Banner banner = bannerViewPager.getData().get(position);
                    snackBarToast.snackBarLongTime(banner.getText(), requireActivity().findViewById(R.id.bottomNavigationView));
                })
                .create(bannerViewAdapter.getBanners());
    }

    //    Refresh layout and get data
    private void liquidRefreshLayout() {
//        int TIME_REFRESH = 2000;
//        fragmentHomeBinding.liquidRefreshLayout.setOnRefreshListener(new LiquidRefreshLayout.OnRefreshListener() {
//            @Override
//            public void completeRefresh() {
//            }
//
//            @Override
//            public void refreshing() {
//                new Handler().postDelayed(() -> fragmentHomeBinding.liquidRefreshLayout.finishRefreshing(), TIME_REFRESH);
//            }
//        });
    }
//    endregion
//    Fragment navigation
//    Error/success message
//    endregion
}