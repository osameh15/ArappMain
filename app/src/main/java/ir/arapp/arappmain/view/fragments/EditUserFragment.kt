package ir.arapp.arappmain.view.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentEditUserBinding
import ir.arapp.arappmain.util.adapters.SpinnerAdapter
import ir.arapp.arappmain.util.services.NavigationManager
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.EditUserViewModel
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import ir.hamsaa.persiandatepicker.api.PersianPickerListener
import java.util.*

class EditUserFragment : Fragment() {
    //    region Variables
    var _fragmentEditUserBinding: FragmentEditUserBinding? = null
    val fragmentEditUserBinding get() = _fragmentEditUserBinding!!
    var editUserViewModel: EditUserViewModel? = null
    var snackBarToast: SnackBarToast? = null
    private var typeface: Typeface? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentEditUserBinding = null
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
        _fragmentEditUserBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_user, container, false)
        //        Set view model
        editUserViewModel = ViewModelProvider(requireActivity()).get(
            EditUserViewModel::class.java
        )
        fragmentEditUserBinding.setViewModel(editUserViewModel)
        //        Hooks
        snackBarToast = SnackBarToast(fragmentEditUserBinding.getRoot())
        typeface = ResourcesCompat.getFont(requireContext(), R.font.iransans_bold)
        //        Set LifeCycle
        fragmentEditUserBinding.setLifecycleOwner(this)
        //        Drawer Locked and visible Bottom navigation
        (requireActivity() as NavigationManager).setDrawerLocked(true)
        (requireActivity() as NavigationManager).bottomNavigationVisibility(false)
        //        Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentEditUserBinding.profileToolbar)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)!!
            .setDisplayHomeAsUpEnabled(true)
        fragmentEditUserBinding.profileToolbar.setNavigationOnClickListener { view1: View? -> onNavigateUp() }
        //        Spinner adapter
        spinnerAdapter()
        //        Set Birthday
        fragmentEditUserBinding.setBirthday.setOnClickListener { v: View? -> setBirthday() }
        //        Return View
        return fragmentEditUserBinding.getRoot()
    }

    //    region Methods
    //    Option menu and manage it
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.setGroupVisible(R.id.bottomNavigationMenu, false)
    }

    //    Spinner adapter
    private fun spinnerAdapter() {
//        Edu Spinner
        editUserViewModel!!.allEduList.observe(viewLifecycleOwner, { edu: ArrayList<String> ->
            val eduAdapter = SpinnerAdapter(requireContext(), R.layout.custom_spinner_layout, edu!!)
            eduAdapter.setDropDownViewResource(R.layout.custom_spinner_layout_dropdown)
            fragmentEditUserBinding!!.educationSpinner.adapter = eduAdapter
        })
        //        Province Spinner
        editUserViewModel!!.allProvinceList.observe(
            viewLifecycleOwner,
            { province: ArrayList<String> ->
                val provinceAdapter =
                    SpinnerAdapter(requireContext(), R.layout.custom_spinner_layout, province!!)
                provinceAdapter.setDropDownViewResource(R.layout.custom_spinner_layout_dropdown)
                fragmentEditUserBinding!!.provinceSpinner.adapter = provinceAdapter
            })
        //        City Spinner
        fragmentEditUserBinding!!.provinceSpinner.onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (position == 1) {
                        editUserViewModel!!.setCityList(1)
                    } else if (position == 2) {
                        editUserViewModel!!.setCityList(2)
                    } else {
                        editUserViewModel!!.setCityList(0)
                    }
                    editUserViewModel!!.allCityList.observe(
                        viewLifecycleOwner,
                        { city: ArrayList<String> ->
                            val cityAdapter = SpinnerAdapter(
                                requireContext(),
                                R.layout.custom_spinner_layout,
                                city!!
                            )
                            cityAdapter.setDropDownViewResource(R.layout.custom_spinner_layout_dropdown)
                            fragmentEditUserBinding!!.citySpinner.adapter = cityAdapter
                        })
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    //    set Birthday
    private fun setBirthday() {
        val persianDatePickerDialog = PersianDatePickerDialog(requireContext())
        persianDatePickerDialog
            .setPositiveButtonString("تایید")
            .setNegativeButton("صرف نظر")
            .setTodayButton("امروز")
            .setTodayButtonVisible(true)
            .setMinYear(1300)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setInitDate(1330, 1, 15)
            .setActionTextColor(Color.GRAY)
            .setTypeFace(typeface)
            .setActionTextColor(resources.getColor(R.color.colorPrimary))
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setShowInBottomSheet(true)
            .setListener(object : PersianPickerListener {
                override fun onDateSelected(persianPickerDate: PersianPickerDate) {
                    fragmentEditUserBinding!!.textBirthday.text = persianPickerDate.persianLongDate
                }

                override fun onDismissed() {}
            })
        persianDatePickerDialog.show()
    }

    //    Back button navigation
    private fun onNavigateUp() {
        requireActivity().onBackPressed()
    } //    endregion
}