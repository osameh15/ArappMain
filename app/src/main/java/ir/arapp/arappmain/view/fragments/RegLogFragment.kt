package ir.arapp.arappmain.view.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentRegLogBinding

class RegLogFragment : Fragment() {
    //    region Variables
    var _fragmentRegLogBinding: FragmentRegLogBinding? = null
    val fragmentRegLogBinding get() = _fragmentRegLogBinding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentRegLogBinding = null
    }
    //    Permission not generated
    var permissions =
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION)
    var grantResult = intArrayOf(1, 2)

    //    endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentRegLogBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_reg_log, container, false)
        //        Check permission
        checkPermission()
        //        Return view
        return fragmentRegLogBinding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //        Nav Controller
        val navController = Navigation.findNavController(view)
        //        OnClick
        fragmentRegLogBinding!!.signUpRegLog.setOnClickListener { view1: View? ->
            goToRegisterFragment(
                navController
            )
        }
        fragmentRegLogBinding!!.loginRegLog.setOnClickListener { view1: View? ->
            goToLoginFragment(
                navController
            )
        }
    }

    //    region methods
    //    On Resume
    override fun onResume() {
        super.onResume()
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    //    Check permission
    private fun checkPermission() {
//        Check runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
                || ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_DENIED
            ) {
//                Show popup for runtime permission
                requestPermissions(permissions, PERMISSION_CODE)
            }
        }
        //        Handle result of runtime permission
        onRequestPermissionsResult(PERMISSION_CODE, permissions, grantResult)
    }

    //   Fragment navigation
    private fun goToLoginFragment(navController: NavController) {
        navController.navigate(R.id.action_regLogFragment_to_loginFragment)
    }

    private fun goToRegisterFragment(navController: NavController) {
        navController.navigate(R.id.action_regLogFragment_to_registerPhoneFragment)
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or WindowManager.LayoutParams.FLAG_FULLSCREEN)
    } //    endregion

    companion object {
        private const val PERMISSION_CODE = 1001
    }
}