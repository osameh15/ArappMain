package ir.arapp.arappmain.util.services

interface NavigationManager {
    //    Lock drawer in some fragments
    fun setDrawerLocked(shouldLock: Boolean)

    //    Open Drawer when Nav Icon clicked
    fun openDrawer()

    //    Bottom Navigation manager
    fun bottomNavigationVisibility(setVisible: Boolean)
}