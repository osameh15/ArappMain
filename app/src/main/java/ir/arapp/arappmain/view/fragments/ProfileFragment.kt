package ir.arapp.arappmain.view.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentProfileBinding
import ir.arapp.arappmain.model.Profile
import ir.arapp.arappmain.adapters.ProfileItemAdapter
import ir.arapp.arappmain.util.services.*
import ir.arapp.arappmain.view.activities.AuthActivity
import ir.arapp.arappmain.viewmodel.ProfileViewModel
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

class ProfileFragment : Fragment(), SnackBarMessage, FragmentManager, ItemClickListener {
    var _fragmentProfileBinding: FragmentProfileBinding? = null
    val fragmentProfileBinding get() = _fragmentProfileBinding!!
    private var profileItemAdapter: ProfileItemAdapter? = null
    private var snackBarToast: SnackBarToast? = null
    private var sessionManager: SessionManager? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentProfileBinding = null
    }
    //    endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        //        Set View model
        val profileViewModel = ViewModelProvider(requireActivity()).get(
            ProfileViewModel::class.java
        )
        fragmentProfileBinding.setViewModel(profileViewModel)
        profileViewModel.snackBarMessage = this
        profileViewModel.fragmentManager = this
        //        Hooks
        profileItemAdapter = ProfileItemAdapter(fragmentProfileBinding.getRoot())
        snackBarToast = SnackBarToast(fragmentProfileBinding.getRoot())
        sessionManager = SessionManager(requireContext())
        profileItemAdapter!!.itemClickListener = this
        //        Set Life Cycle
        fragmentProfileBinding.setLifecycleOwner(this)
        //        Drawer Locked and visible Bottom navigation
        (requireActivity() as NavigationManager).setDrawerLocked(true)
        (requireActivity() as NavigationManager).bottomNavigationVisibility(true)
        //        Recycler View Profile items
        profileViewModel.allProfileItems.observe(
            viewLifecycleOwner,
            { profiles: ArrayList<Profile> ->
                profileItemAdapter?.profileArrayList = (profiles)
                setRecyclerView()
            })
        //        Return view
        return fragmentProfileBinding.getRoot()
    }

    //    region methods
    //    Check permission for pick image from gallery
    private fun checkPermission() {
//            check runtime permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
            ) {
//                    permission not generated
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                //                    show popup for runtime permission
                requestPermissions(permissions, PERMISSION_CODE)
            } else {
//                    permission already generated
                selectImage()
            }
        } else {
//                System os is less than marshmallow
            selectImage()
        }
    }

    //    Handle result of runtime permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                permission was generated
                selectImage()
            } else {
//                permission was denied
                onSuccess("اجازه دسترسی به حافظه داده نشد!")
            }
        }
    }

    //    Function to select an image from gallery
    private fun selectImage() {
//        intent to pick image
        val gallery = Intent()
        gallery.type = "image/*"
        gallery.action = Intent.ACTION_PICK
        //        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(
            Intent.createChooser(gallery, "تصویر کاربری خود را انتخاب کنید"),
            PICK_IMAGE
        )
    }

    //    Handle result of access to gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
//            set image to imageView
            val imageUri = Objects.requireNonNull(data).data
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUri)
                fragmentProfileBinding!!.profileImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    //    Method to convert image to String
    private fun imageToString(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imgBytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(imgBytes, Base64.DEFAULT)
    }

    //    Set profile adapter and layout manager
    private fun setRecyclerView() {
        fragmentProfileBinding!!.profileRecyclerView.setHasFixedSize(true)
        //        Add spacing between 2 item
        fragmentProfileBinding!!.profileRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                12,
                1
            )
        )
        fragmentProfileBinding!!.profileRecyclerView.adapter = profileItemAdapter
    }

    //        Fragment navigation
    override fun navigateToFragment(message: String?) {
        val navController = Navigation.findNavController(requireView())
        when (message) {
            "editUser" -> navController.navigate(R.id.action_profileFragment_to_editUserFragment)
            "addService" -> navController.navigate(R.id.action_profileFragment_to_addServiceFragment)
            "myService" -> navController.navigate(R.id.action_profileFragment_to_myServiceFragment)
            "setting" -> navController.navigate(R.id.action_profileFragment_to_settingFragment)
        }
    }

    //    Set methods for specific function
    override fun setFunction(type: String?) {
        if (type == "image") {
            checkPermission()
        }
    }

    //    Error/success message
    override fun onSuccess(message: String?) {
        snackBarToast!!.snackBarLongTime(
            message,
            requireActivity().findViewById(R.id.bottomNavigationView)
        )
    }

    override fun onFailure(message: String?) {
        snackBarToast!!.snackBarLongTime(message)
    }

    //    On Click Listener on Recycler View items
    override fun onItemClickListener(view: View?, position: Int, message: String?) {
        val intent = Intent(requireContext(), AuthActivity::class.java)
        when (position) {
            1 -> navigateToFragment("addService")
            3 -> navigateToFragment("myService")
            8 -> {
                sessionManager!!.setLogin(false)
                requireActivity().finish()
                startActivity(intent)
            }
            else -> onSuccess(message)
        }
    }

    //    endregion
    //    private inline class for spacing recycler view items
    private class GridSpacingItemDecoration     //        endregion
    //        Constructor
        (//        region Variables
        private val spanCount: Int, private val spacing: Int, private val spacing_top: Int
    ) : ItemDecoration() {
        private val includeEdge = true

        //        Get item offsets to set layout spacing
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
//             item phases_position
            val position = parent.getChildAdapterPosition(view)
            //             item column
            val column = position % spanCount
            if (includeEdge) {
//                 spacing - column * ((1f / spanCount) * spacing)
                outRect.left = spacing - column * spacing / spanCount
                //                 (column + 1) * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount
                if (position < spanCount) {
//                     top edge
                    outRect.top = spacing_top
                }
                //                 item bottom
                outRect.bottom = spacing_top
            } else {
//                 column * ((1f / spanCount) * spacing)
                outRect.left = column * spacing / spanCount
                //                 spacing - (column + 1) * ((1f /    spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount) {
//                     item top
                    outRect.top = spacing_top
                }
            }
        }
    }

    companion object {
        //    region Variable
        private const val PERMISSION_CODE = 1000
        private const val PICK_IMAGE = 1001
    }
}