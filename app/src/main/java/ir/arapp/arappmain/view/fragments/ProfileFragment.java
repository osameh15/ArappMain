package ir.arapp.arappmain.view.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import ir.arapp.arappmain.R;
import ir.arapp.arappmain.util.adapter.ProfileItemAdapter;
import ir.arapp.arappmain.util.services.FragmentManager;
import ir.arapp.arappmain.util.services.ItemClickListener;
import ir.arapp.arappmain.util.services.NavigationManager;
import ir.arapp.arappmain.util.services.SessionManager;
import ir.arapp.arappmain.util.services.SnackBarMessage;
import ir.arapp.arappmain.util.services.SnackBarToast;
import ir.arapp.arappmain.view.activities.AuthActivity;
import ir.arapp.arappmain.databinding.FragmentProfileBinding;
import ir.arapp.arappmain.viewmodel.ProfileViewModel;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements SnackBarMessage, FragmentManager, ItemClickListener
{

//    region Variable
    private static final int PERMISSION_CODE = 1000;
    private static final int PICK_IMAGE = 1001;
    FragmentProfileBinding fragmentProfileBinding;
    private ProfileItemAdapter profileItemAdapter;
    private SnackBarToast snackBarToast;
    private SessionManager sessionManager;
    //    endregion

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        Inflate the layout for this fragment
        fragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
//        Set View model
        ProfileViewModel profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        fragmentProfileBinding.setViewModel(profileViewModel);
        profileViewModel.snackBarMessage = this;
        profileViewModel.fragmentManager = this;
//        Hooks
        profileItemAdapter = new ProfileItemAdapter(fragmentProfileBinding.getRoot());
        snackBarToast = new SnackBarToast(fragmentProfileBinding.getRoot());
        sessionManager = new SessionManager(requireContext());
        profileItemAdapter.itemClickListener = this;
//        Set Life Cycle
        fragmentProfileBinding.setLifecycleOwner(this);
//        Drawer Locked and visible Bottom navigation
        ((NavigationManager) requireActivity()).setDrawerLocked(true);
        ((NavigationManager) requireActivity()).bottomNavigationVisibility(true);
//        Recycler View Profile items
        profileViewModel.getAllProfileItems().observe(getViewLifecycleOwner(), profiles ->
        {
            profileItemAdapter.setProfileArrayList(profiles);
            setRecyclerView();
        });
//        Return view
        return fragmentProfileBinding.getRoot();
    }

//    region methods
//    Check permission for pick image from gallery
        private void checkPermission()
        {
//            check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if (ActivityCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {
//                    permission not generated
                    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
//                    show popup for runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else
                {
//                    permission already generated
                    selectImage();
                }
            }
            else
            {
//                System os is less than marshmallow
                selectImage();
            }
        }
//    Handle result of runtime permission

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults)
    {
        if (requestCode == PERMISSION_CODE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
//                permission was generated
                selectImage();
            }
            else
            {
//                permission was denied
               onSuccess("اجازه دسترسی به حافظه داده نشد!");
            }
        }
    }
//    Function to select an image from gallery
    private void selectImage()
    {
//        intent to pick image
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_PICK);
//        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(gallery, "تصویر کاربری خود را انتخاب کنید"), PICK_IMAGE);
    }
//    Handle result of access to gallery
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data)
    {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null)
        {
//            set image to imageView
            Uri imageUri = Objects.requireNonNull(data).getData();
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imageUri);
                fragmentProfileBinding.profileImage.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
//    Method to convert image to String
    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
//    Set profile adapter and layout manager
    private void setRecyclerView()
    {
        fragmentProfileBinding.profileRecyclerView.setHasFixedSize(true);
//        Add spacing between 2 item
        fragmentProfileBinding.profileRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 12, 1));
        fragmentProfileBinding.profileRecyclerView.setAdapter(profileItemAdapter);
    }
//        Fragment navigation
    @Override
    public void navigateToFragment(String message)
    {
        final NavController navController = Navigation.findNavController(requireView());
        switch (message)
        {
            case "editUser":
                navController.navigate(R.id.action_profileFragment_to_editUserFragment);
                break;
            case "addService":
                navController.navigate(R.id.action_profileFragment_to_addServiceFragment);
                break;
            case "myService":
                navController.navigate(R.id.action_profileFragment_to_myServiceFragment);
                break;
            case "setting":
                navController.navigate(R.id.action_profileFragment_to_settingFragment);
                break;
        }
    }
//    Set methods for specific function
    @Override
    public void setFunction(String type)
    {
        if (type.equals("image"))
        {
            checkPermission();
        }
    }
//    Error/success message
    @Override
    public void onSuccess(String message)
    {
        snackBarToast.snackBarLongTime(message, requireActivity().findViewById(R.id.bottomNavigationView));
    }
    @Override
    public void onFailure(String message)
    {
        snackBarToast.snackBarLongTime(message);
    }
//    On Click Listener on Recycler View items
    @Override
    public void onItemClickListener(View view, int position, String message)
    {
        Intent intent = new Intent(requireContext(), AuthActivity.class);
        switch (position)
        {
            case 1:
                navigateToFragment("addService");
                break;
            case 3:
                navigateToFragment("myService");
                break;
            case 8:
                sessionManager.setLogin(false);
                requireActivity().finish();
                startActivity(intent);
                break;
            default:
                onSuccess(message);
        }
    }
//    endregion

//    private inline class for spacing recycler view items
    private static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration
    {
//        region Variables
        final private int spanCount;
        final private int spacing;
        final private int spacing_top;
        final private boolean includeEdge;
//        endregion
//        Constructor
        public GridSpacingItemDecoration(int spanCount, int spacing_left, int spacing_top)
        {
            this.spanCount = spanCount;
            this.spacing = spacing_left;
            this.includeEdge = true;
            this.spacing_top=spacing_top;
        }
//        Get item offsets to set layout spacing
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state)
        {
//             item phases_position
            int position = parent.getChildAdapterPosition(view);
//             item column
            int column = position % spanCount;
            if (includeEdge)
            {
//                 spacing - column * ((1f / spanCount) * spacing)
                outRect.left = spacing - column * spacing / spanCount;
//                 (column + 1) * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount;
                if (position < spanCount)
                {
//                     top edge
                    outRect.top = spacing_top;
                }
//                 item bottom
                outRect.bottom = spacing_top;
            }
            else
            {
//                 column * ((1f / spanCount) * spacing)
                outRect.left = column * spacing / spanCount;
//                 spacing - (column + 1) * ((1f /    spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount)
                {
//                     item top
                    outRect.top = spacing_top;
                }
            }
        }
    }
}