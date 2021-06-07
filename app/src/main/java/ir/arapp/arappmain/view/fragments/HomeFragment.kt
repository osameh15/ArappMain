package ir.arapp.arappmain.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.mancj.materialsearchbar.MaterialSearchBar
import com.mancj.materialsearchbar.MaterialSearchBar.OnSearchActionListener
import com.zhpan.bannerview.BannerViewPager
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentHomeBinding
import ir.arapp.arappmain.model.base.Banner
import ir.arapp.arappmain.util.adapters.banner.BannerViewAdapter
import ir.arapp.arappmain.util.adapters.banner.BannerViewHolder
import ir.arapp.arappmain.util.adapters.services.AllServicesCategoryAdapter
import ir.arapp.arappmain.util.services.NavigationManager
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.HomeViewModel

class HomeFragment : Fragment(), OnSearchActionListener {
    //    region Variable
    var _fragmentHomeBinding: FragmentHomeBinding? = null
    val fragmentHomeBinding get() = _fragmentHomeBinding!!
    var homeViewModel: HomeViewModel? = null
    var bannerViewPager: BannerViewPager<Banner, BannerViewHolder>? = null
    var bannerViewAdapter: BannerViewAdapter? = null
    var snackBarToast: SnackBarToast? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentHomeBinding = null
    }

    //
    //    SingleDirectionRecyclerView mainRecyclerView;
    //    endregion
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        Inflate the layout for this fragment
        _fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        //        Set view model
        homeViewModel = ViewModelProvider(requireActivity()).get(
            HomeViewModel::class.java
        )
        //        Hooks
        bannerViewAdapter = BannerViewAdapter()
        snackBarToast = SnackBarToast(fragmentHomeBinding.root)
        //        bannerViewPager = fragmentHomeBinding.getRoot().findViewById(R.id.bannerView);
        setUpViews()
        //        Return view
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()


    }

    private fun setUpObservers() {
        homeViewModel?.apply {
            homeAdapterDataLiveData.observe(viewLifecycleOwner) {
                fragmentHomeBinding.mainRecyclerView.adapter =
                    AllServicesCategoryAdapter(requireContext(), this@HomeFragment, it)
                fragmentHomeBinding.mainRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    private fun setUpViews() {
        //        Search View Material
        fragmentHomeBinding.searchView.setArrowIcon(R.drawable.ic_arrow_forward_black_48dp)
        fragmentHomeBinding.searchView.setOnSearchActionListener(this)
        fragmentHomeBinding.searchView.setOnClickListener { view: View? -> onSearchClick() }
        //        Drawer Locked and visible Bottom navigation
        (requireActivity() as NavigationManager).setDrawerLocked(false)
        (requireActivity() as NavigationManager).bottomNavigationVisibility(true)
        //        Set banner news
        //        Refresh layout
        liquidRefreshLayout()
    }

    //    region methods
    override fun onDestroy() {
        super.onDestroy()
    }

    //    region Search view bar
    private fun onSearchClick() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_homeFragment_to_searchFragment)
    }

    override fun onSearchStateChanged(enabled: Boolean) {}
    override fun onSearchConfirmed(text: CharSequence) {}
    override fun onButtonClicked(buttonCode: Int) {
        when (buttonCode) {
            MaterialSearchBar.BUTTON_NAVIGATION -> (requireActivity() as NavigationManager).openDrawer()
            MaterialSearchBar.BUTTON_SPEECH -> {
            }
            MaterialSearchBar.BUTTON_BACK -> fragmentHomeBinding!!.searchView.closeSearch()
        }
    }

    //    Banner View Adapter
    private fun bannerAdapter() {
        bannerViewPager?.apply {
            setOffScreenPageLimit(2)
                .setLifecycleRegistry(lifecycle)
                .setAdapter(bannerViewAdapter)
                .registerOnPageChangeCallback(object : OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }
                })
                .setOnPageClickListener { position: Int ->
                    val banner = bannerViewPager!!.data[position]
                    snackBarToast!!.snackBarLongTime(
                        banner.text,
                        requireActivity().findViewById(R.id.bottomNavigationView)
                    )
                }
                .create(bannerViewAdapter!!.banners)
        }

    }

    //    Refresh layout and get data
    private fun liquidRefreshLayout() {
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
    } //    endregion
    //    Fragment navigation
    //    Error/success message
    //    endregion
}