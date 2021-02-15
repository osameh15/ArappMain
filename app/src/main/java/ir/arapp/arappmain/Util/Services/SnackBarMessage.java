package ir.arapp.arappmain.Util.Services;

public interface SnackBarMessage
{
    //call Method when on Success activity
    void onSuccess(String message);
    //call Method when on Failure activity
    void onFailure(String message);
}
