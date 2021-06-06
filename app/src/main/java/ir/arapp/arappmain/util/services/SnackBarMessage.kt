package ir.arapp.arappmain.util.services

interface SnackBarMessage {
    //    Call Method when on Success activity
    fun onSuccess(message: String?)

    //    Call Method when on Failure activity
    fun onFailure(message: String?)
}