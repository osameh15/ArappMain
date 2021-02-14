package ir.arapp.arappmain.Util.Services;

public interface SnackBarMessage
{
    //call Method when on Success activity
    void onSuccess();
    //call Method when on Failure activity
    void onFailure(String message);
}
