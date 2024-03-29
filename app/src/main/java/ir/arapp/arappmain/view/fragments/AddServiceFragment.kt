package ir.arapp.arappmain.view.fragments

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.arappmain.radialtimepicker.PageData.ClockArrow
import com.arappmain.radialtimepicker.TimePickerBottomSheetFragment
import ir.arapp.arappmain.R
import ir.arapp.arappmain.databinding.FragmentAddServiceBinding
import ir.arapp.arappmain.model.base.Category
import ir.arapp.arappmain.util.adapters.SpinnerAdapter
import ir.arapp.arappmain.util.services.FragmentManager
import ir.arapp.arappmain.util.services.NavigationManager
import ir.arapp.arappmain.util.services.SnackBarMessage
import ir.arapp.arappmain.util.services.SnackBarToast
import ir.arapp.arappmain.viewmodel.AddServiceViewModel
import java.lang.Exception
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class AddServiceFragment : Fragment(),SnackBarMessage,FragmentManager {
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
    ): View {
//        Inflate the layout for this fragment
        _fragmentAddServiceBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_service, container, false)
        //        Set view model
        addServiceViewModel = ViewModelProvider(requireActivity()).get(
            AddServiceViewModel::class.java
        )
        addServiceViewModel!!.update()
        addServiceViewModel?.snackBarMessage = this
        addServiceViewModel?.fragmentManager = this
        var array = arrayOf(R.drawable.hotels,R.drawable.cafe,R.drawable.restaurant,R.drawable.fast_food,R.drawable.restaurant_1,R.drawable.restaurant_2,R.drawable.restaurant_3,R.drawable.coffe_1,R.drawable.coffe_2,R.drawable.coffe_3,R.drawable.hotel_1,R.drawable.hotel_2,R.drawable.hotel_3)
        var random = kotlin.random.Random(java.util.Calendar.getInstance().timeInMillis)
        var bitmap =
            ResourcesCompat.getDrawable(resources, array[random.nextInt(from = 0,until = array.lastIndex)], null)?.toBitmap()
        addServiceViewModel?.image = MutableLiveData(bitmap)

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
        fragmentAddServiceBinding.categorySpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                addServiceViewModel!!.category_id.value =
                    addServiceViewModel!!.allCategory.value!!.get(position).id

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        //        Time Picker
        fragmentAddServiceBinding.openingYearSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                addServiceViewModel!!.allOpeningYear.value?.get(position)?.let {
                    try {
                        addServiceViewModel!!.birth.value = it.toInt()
                    } catch (e:Exception){
                        addServiceViewModel!!.birth.value = 0
                    }

                }
                Log.i("TAG", "onItemSelected: ")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        timePicker()

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
            { category: ArrayList<Category> ->
                var categories  = ArrayList<String>()
                category.forEach {
                    categories.add(it.title!!)
                }
                val categorySpinner =
                    SpinnerAdapter(requireContext(), R.layout.custom_spinner_layout, categories)
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
        val numberFormat: NumberFormat = DecimalFormat.getInstance(Locale.US).apply {
            this as DecimalFormat
            this.applyLocalizedPattern("00")
        }
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

            addServiceViewModel?.startTime?.value = "${numberFormat.format(i)}:${numberFormat.format(i1)}"
            addServiceViewModel?.endTime?.value = "${numberFormat.format(i2)}:${numberFormat.format(i3)}"
        }
    } //    endregion

    override fun onSuccess(message: String?) {
        Log.i("TAG123123", "onResponse body: onSuccess")
    }

    override fun onFailure(message: String?) {
        snackBarToast!!.snackBarShortTime(message, fragmentAddServiceBinding.root)
    }

    override fun setFunction(type: String?) {

    }

    override fun navigateToFragment(message: String?) {

    }
}