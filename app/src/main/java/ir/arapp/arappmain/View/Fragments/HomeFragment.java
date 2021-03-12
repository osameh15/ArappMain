package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.zhpan.bannerview.BannerViewPager;

import java.util.ArrayList;

import ir.arapp.arappmain.Model.BannerItem;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.Util.Adapters.BannerViewAdapter;
import ir.arapp.arappmain.Util.Adapters.BannerViewHolder;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.Util.Services.SnackBarToast;
import ir.arapp.arappmain.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements MaterialSearchBar.OnSearchActionListener
{

    //    region Variable
    FragmentHomeBinding fragmentHomeBinding;
    BannerViewPager<BannerItem, BannerViewHolder> bannerViewPager;
    ArrayList<BannerItem> bannerLists;
    SnackBarToast snackBarToast;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
//        Hooks
        bannerLists = new ArrayList<>();
        snackBarToast = new SnackBarToast(fragmentHomeBinding.getRoot());
        bannerViewPager = fragmentHomeBinding.getRoot().findViewById(R.id.bannerView);
//        Search View Material
        fragmentHomeBinding.searchView.setArrowIcon(R.drawable.ic_arrow_forward_black_48dp);
        fragmentHomeBinding.searchView.setOnSearchActionListener(this);
        fragmentHomeBinding.searchView.setOnClickListener(view -> onSearchClick());
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(false);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(true);
//        Set banner news
        setBannerItems();
        bannerAdapter();
//        Return view
        return fragmentHomeBinding.getRoot();
    }

    //    region methods
    @Override
    public void onDestroy() {
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
//    endregion
//    Banner View pager
    private void setBannerItems()
    {
        bannerLists.add(new BannerItem(1, 1, R.drawable.arapp_default, "اطلاعیه شماره 1", "23 اسفند 1399"));
        bannerLists.add(new BannerItem(2, 1, R.drawable.news_one, "اطلاعیه شماره 2", "3 فروردین 1400"));
        bannerLists.add(new BannerItem(3, 1, R.drawable.news_two, "اطلاعیه شماره 3", "23 فروردین 1400"));
        bannerLists.add(new BannerItem(4, 1, R.drawable.news_three, "اطلاعیه شماره 4", "28 فروردین 1400"));
        bannerLists.add(new BannerItem(5, 1, R.drawable.news_four, "اطلاعیه شماره 5", "10 اردیبهشت 1400"));
    }
    private void bannerAdapter()
    {
        BannerViewAdapter bannerViewAdapter = new BannerViewAdapter();
        bannerViewPager
                .setOffScreenPageLimit(2)
                .setLifecycleRegistry(getLifecycle())
                .setAdapter(bannerViewAdapter)
                .registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
                {
                    @Override
                    public void onPageSelected(int position)
                    {
                        super.onPageSelected(position);
                    }
                })
                .setOnPageClickListener(position ->
                {
                    BannerItem bannerItem = bannerViewPager.getData().get(position);
                    snackBarToast.snackBarLongTime(bannerItem.getText(), requireActivity().findViewById(R.id.bottomNavigationView));
                })
                .create(bannerLists);
        bannerViewAdapter.notifyDataSetChanged();
    }
//    Fragment navigation
//    Error/success message
//    endregion
}