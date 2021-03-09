package ir.arapp.arappmain.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import ir.arapp.arappmain.Util.Adapters.NotificationAdapter;
import ir.arapp.arappmain.Util.Services.NavigationManager;
import ir.arapp.arappmain.databinding.FragmentNotificationBinding;
import ir.arapp.arappmain.viewmodel.NotificationViewModel;

public class NotificationFragment extends Fragment
{

//    region Variable
    FragmentNotificationBinding fragmentNotificationBinding;
    private NotificationAdapter notificationAdapter;
//    endregion

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentNotificationBinding = FragmentNotificationBinding.inflate(inflater, container, false);
//        Set view model
        NotificationViewModel notificationViewModel = ViewModelProviders.of(requireActivity()).get(NotificationViewModel.class);
//        Hooks
        notificationAdapter = new NotificationAdapter();
//        Set life cycle
        fragmentNotificationBinding.setLifecycleOwner(this);
//        Recycler View notification items
        notificationViewModel.getAllNotificationList().observe(getViewLifecycleOwner(), notifications ->
        {
            notificationAdapter.setNotificationArrayList(notifications);
            setRecyclerView();
        });
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(true);
//        Return view
        return fragmentNotificationBinding.getRoot();
    }

//    region methods
//    Set notification adapter and layout manager
    private void setRecyclerView()
    {
        fragmentNotificationBinding.notificationRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        fragmentNotificationBinding.notificationRecyclerView.setHasFixedSize(true);
        fragmentNotificationBinding.notificationRecyclerView.setAdapter(notificationAdapter);
    }
//    endregion
}