package ir.arapp.arappmain.Util.Services;

public interface NavigationManager
{
//    Lock drawer in some fragments
    void setDrawerLocked(boolean shouldLock);
//    Open Drawer when Nav Icon clicked
    void openDrawer();
//    Bottom Navigation manager
    void bottomNavigationVisibility(boolean setVisible);
}
