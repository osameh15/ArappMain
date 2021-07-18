package ir.arapp.arappmain.model.base

import com.google.gson.annotations.SerializedName

class GetService {
    @SerializedName("service")
    var serviceData: GetServiceData? = null
}

class GetAllServices {
    var services = ArrayList<GetServiceData>()
}

class NewPassword{
    constructor(password: String,passwordConfirmation:String,token:String){
        this.password = password
        this.passwordConfirmation = passwordConfirmation
        this.token = token
    }
    @SerializedName("password")
    var password:String? = null
    @SerializedName("password-confirmation")
    var passwordConfirmation :String? = null
    @SerializedName("token")
    var token :String? = null
}

class RegisterResult {
    constructor()
    constructor(id: Int) {
        this.id = id
    }

    var id: Int = 0
}

class RegisterBody {
    private constructor()
    constructor(mobileNumber: String) {
        this.mobileNumber = mobileNumber
    }

    @SerializedName("mobile")
    var mobileNumber: String? = null
}

class Verify {
    constructor(id: Int,code:Int){
        this.id =  id
        this.code  = code
    }
    var id: Int? = null
    var code:Int? = null
}

class LoginMobile{
    constructor(mobileNumber: String,password:String){
        this.mobile = mobileNumber
        this.password = password
    }
    var mobile:String? = null
    var password:String? = null
}

class GetAllCategories {
    @SerializedName("categories")
    var categories: List<Category>? = null

    @SerializedName("parent_categories")
    var parentCategories: List<Category>? = null
}

class ResponseModel<T> {
    @SerializedName("Success")
    var success: Boolean = false
    @SerializedName("message")
    var message: String = ""
    @SerializedName("result")
    var result: T? = null
}
