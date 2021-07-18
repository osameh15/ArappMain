package ir.arapp.arappmain.view.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arappmain.radialtimepicker.PageData.ClockArrow
import com.arappmain.radialtimepicker.TimePickerBottomSheetFragment
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentAddServiceBinding
import ir.arapp.arappmain.model.base.PostServiceData
import ir.arapp.arappmain.util.adapters.SpinnerAdapter
import ir.arapp.arappmain.util.server.Retrofit
import ir.arapp.arappmain.util.services.NavigationManager
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.AddServiceViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class AddServiceFragment : Fragment() {
    //    region Variables
    var _fragmentAddServiceBinding: FragmentAddServiceBinding? = null
    val fragmentAddServiceBinding get() = _fragmentAddServiceBinding!!
    var addServiceViewModel: AddServiceViewModel? = null
    var snackBarToast: SnackBarToast? = null
    private var timePickerBottomSheetFragment: TimePickerBottomSheetFragment? = null

    //    endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentAddServiceBinding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Inflate the layout for this fragment
        _fragmentAddServiceBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_service, container, false)
        //        Set view model
        addServiceViewModel = ViewModelProvider(requireActivity()).get(
            AddServiceViewModel::class.java
        )
        fragmentAddServiceBinding.setViewModel(addServiceViewModel)
        //        Hooks
        timePickerBottomSheetFragment = TimePickerBottomSheetFragment()
        snackBarToast = SnackBarToast(fragmentAddServiceBinding.getRoot())
        //        Set LifeCycle
        fragmentAddServiceBinding.setLifecycleOwner(this)
        //        Drawer Locked and visible Bottom navigation
        (requireActivity() as NavigationManager).setDrawerLocked(true)
        (requireActivity() as NavigationManager).bottomNavigationVisibility(false)
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentAddServiceBinding.profileToolbar)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentAddServiceBinding.profileToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Spinner Adapter
        spinnerAdapter()
        //        Time Picker
        timePicker()


        fragmentAddServiceBinding.addServiceConfirmButton.setOnClickListener {
            Toast.makeText(requireContext(), "pressConfirmation", Toast.LENGTH_SHORT).show()
            val serviceData = PostServiceData()
            serviceData.title = fragmentAddServiceBinding.serviceTitleEditText.text?.toString()
            serviceData.address = fragmentAddServiceBinding.serviceAddressEditText.text?.toString()
            serviceData.description =
                fragmentAddServiceBinding.serviceDescriptionEditText.text?.toString()
            serviceData.summary =
                fragmentAddServiceBinding.addServiceSummaryEditText.text?.toString()
            serviceData.startTime = addServiceViewModel?.startTime
            serviceData.endTime = addServiceViewModel?.endTime
            var bitmap =
                ResourcesCompat.getDrawable(resources, R.drawable.add_service, null)?.toBitmap()
            bitmap?.let { bitmap ->
                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
                val encoded: String =
                    encodeToString(byteArray, DEFAULT)
                serviceData.pictureBase64 = encoded
                Retrofit.api.addNewService(serviceData).enqueue(object : Callback<PostServiceData> {
                    override fun onResponse(
                        call: Call<PostServiceData>,
                        response: Response<PostServiceData>
                    ) {
                        Toast.makeText(requireContext(), "start test", Toast.LENGTH_SHORT).show()
                        Log.i(
                            "TAG0912",
                            "onResponse: ${response.isSuccessful}   ${response.body()}"
                        )
                    }

                    override fun onFailure(call: Call<PostServiceData>, t: Throwable) {

                    }
                })
            }
            //        Return View
        }
        return fragmentAddServiceBinding.getRoot()
    }

    //    region Methods
    //    Menu option
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.setGroupVisible(R.id.bottomNavigationMenu, false)
    }

    //    Back button navigation
    private fun onNavigateUp() {
        requireActivity().onBackPressed()
    }

    //    Spinner adapter
    private fun spinnerAdapter() {
//        Set category Spinner
        addServiceViewModel!!.allCategory.observe(
            viewLifecycleOwner,
            { category: ArrayList<String> ->
                val categorySpinner =
                    SpinnerAdapter(requireContext(), R.layout.custom_spinner_layout, category!!)
                categorySpinner.setDropDownViewResource(R.layout.custom_spinner_layout_dropdown)
                fragmentAddServiceBinding!!.categorySpinner.adapter = categorySpinner
            })
        //        Opening year spinner
        addServiceViewModel!!.allOpeningYear.observe(
            viewLifecycleOwner,
            { openingYear: ArrayList<String> ->
                val yearAdapter =
                    SpinnerAdapter(requireContext(), R.layout.custom_spinner_layout, openingYear!!)
                yearAdapter.setDropDownViewResource(R.layout.custom_spinner_layout_dropdown)
                fragmentAddServiceBinding!!.openingYearSpinner.adapter = yearAdapter
            })
    }

    //    Time Picker
    @SuppressLint("SetTextI18n")
    private fun timePicker() {
        val typeface = ResourcesCompat.getFont(requireContext(), R.font.iransans_farsi_num)
        val numberFormat: NumberFormat = DecimalFormat("00")
        timePickerBottomSheetFragment!!.set24Hour(true)
        timePickerBottomSheetFragment!!.setTextTypeFace(typeface)
        timePickerBottomSheetFragment!!.initTime(8, 0, 21, 0)
        fragmentAddServiceBinding!!.setTimeActivity.setOnClickListener { v: View? ->
            timePickerBottomSheetFragment!!.setClockArrowMode(ClockArrow.Hour)
            timePickerBottomSheetFragment!!.show(childFragmentManager, "tag")
        }
        timePickerBottomSheetFragment!!.setOnTimeResultListener { aBoolean: Boolean?, i: Int?, i1: Int?, i2: Int?, i3: Int? ->
            fragmentAddServiceBinding!!.textTimeActivity.text = """
     آغاز ارائه خدمات ساعت: ${numberFormat.format(i)}:${numberFormat.format(i1)}
     پایان ارائه خدمات ساعت: ${numberFormat.format(i2)}:${numberFormat.format(i3)}
     """.trimIndent()
            Unit
            addServiceViewModel?.startTime = "${numberFormat.format(i)}:${numberFormat.format(i1)}"
            addServiceViewModel?.endTime = "${numberFormat.format(i2)}:${numberFormat.format(i3)}"
        }
    } //    endregion
}