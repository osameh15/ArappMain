package ir.arapp.arappmain.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arappmain.radialtimepicker.PageData.ClockArrow
import com.arappmain.radialtimepicker.TimePickerBottomSheetFragment
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentEditServiceBinding
import ir.arapp.arappmain.util.services.NavigationManager
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.EditServiceViewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class EditServiceFragment : Fragment() {
    //    region Variables
    var _fragmentEditServiceBinding: FragmentEditServiceBinding? = null
    val fragmentEditServiceBinding get() = _fragmentEditServiceBinding!!
    var editServiceViewModel: EditServiceViewModel? = null
    var snackBarToast: SnackBarToast? = null
    private var timePickerBottomSheetFragment: TimePickerBottomSheetFragment? = null

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
        _fragmentEditServiceBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_service, container, false)
        //        Set view model
        editServiceViewModel = ViewModelProvider(requireActivity()).get(
            EditServiceViewModel::class.java
        )
        fragmentEditServiceBinding.setViewModel(editServiceViewModel)
        //        Hooks
        snackBarToast = SnackBarToast(fragmentEditServiceBinding.getRoot())
        timePickerBottomSheetFragment = TimePickerBottomSheetFragment()
        //        Set LifeCycle
        fragmentEditServiceBinding.setLifecycleOwner(this)
        //        Drawer Locked and visible Bottom navigation
        (requireActivity() as NavigationManager).setDrawerLocked(true)
        (requireActivity() as NavigationManager).bottomNavigationVisibility(false)
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentEditServiceBinding.profileToolbar)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentEditServiceBinding.profileToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Time Picker
        timePicker()
        //        Return View
        return fragmentEditServiceBinding.getRoot()
    }

    //    region Methods
    //    Option menu and manage it
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.setGroupVisible(R.id.bottomNavigationMenu, false)
    }

    //    Back button navigation
    private fun onNavigateUp() {
        requireActivity().onBackPressed()
    }

    //    Time Picker
    @SuppressLint("SetTextI18n")
    private fun timePicker() {
        val typeface = ResourcesCompat.getFont(requireContext(), R.font.iransans_farsi_num)
        val numberFormat: NumberFormat = DecimalFormat("00")
        timePickerBottomSheetFragment!!.set24Hour(true)
        timePickerBottomSheetFragment!!.setTextTypeFace(typeface)
        timePickerBottomSheetFragment!!.initTime(8, 0, 21, 0)
        fragmentEditServiceBinding!!.changeTimeActivity.setOnClickListener { v: View? ->
            timePickerBottomSheetFragment!!.setClockArrowMode(ClockArrow.Hour)
            timePickerBottomSheetFragment!!.show(childFragmentManager, "tag")
        }
        timePickerBottomSheetFragment!!.setOnTimeResultListener { aBoolean: Boolean?, i: Int?, i1: Int?, i2: Int?, i3: Int? ->
            fragmentEditServiceBinding!!.textTimeActivity.text = """
     آغاز ارائه خدمات ساعت: ${numberFormat.format(i)}:${numberFormat.format(i1)}
     پایان ارائه خدمات ساعت: ${numberFormat.format(i2)}:${numberFormat.format(i3)}
     """.trimIndent()
            Unit
        }
    } //    endregion
}