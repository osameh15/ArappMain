package ir.arapp.arappmain.util.services

interface FragmentManager {
    //    Set enabled button and set theme
    fun setFunction(type: String?)

    //    Navigate to another fragment when call this function
    fun navigateToFragment(message: String?)
}