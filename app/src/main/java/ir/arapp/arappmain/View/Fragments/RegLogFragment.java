package ir.arapp.arappmain.View.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import ir.arapp.arappmain.R;
import ir.arapp.arappmain.databinding.FragmentRegLogBinding;

public class RegLogFragment extends Fragment
{

//    region Variables
    FragmentRegLogBinding fragmentRegLogBinding;
    private static final int PERMISSION_CODE = 1001;
//    Permission not generated
    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};
    int[] grantResult = {1, 2};
//    endregion

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentRegLogBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reg_log, container, false);
//        Check permission
        checkPermission();
//        Return view
        return fragmentRegLogBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
//        Nav Controller
        final NavController navController = Navigation.findNavController(view);
//        OnClick
        fragmentRegLogBinding.signUpRegLog.setOnClickListener(view1 -> goToRegisterFragment(navController));
        fragmentRegLogBinding.loginRegLog.setOnClickListener(view1 -> goToLoginFragment(navController));
    }

//    region methods
//    On Resume
    @Override
    public void onResume()
    {
        super.onResume();
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
//    Check permission
    private void checkPermission()
    {
//        Check runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_DENIED)
            {
//                Show popup for runtime permission
                requestPermissions(permissions, PERMISSION_CODE);
            }
        }
//        Handle result of runtime permission
        onRequestPermissionsResult(PERMISSION_CODE, permissions, grantResult);
    }
//   Fragment navigation
    private void goToLoginFragment(NavController navController)
    {
        navController.navigate(R.id.action_regLogFragment_to_loginFragment);
    }
    private void goToRegisterFragment(NavController navController)
    {
        navController.navigate(R.id.action_regLogFragment_to_registerPhoneFragment);
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
//    endregion
}