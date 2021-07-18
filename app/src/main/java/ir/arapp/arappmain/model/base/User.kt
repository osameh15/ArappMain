package ir.arapp.arappmain.model.base

import com.google.gson.annotations.SerializedName

class User     //    endregion
//    endregion
//    region Methods
//    Constructor
    (
    //    region Setter
    //    endregion
    //    region Getter
    //    region Variable
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("type") var type: String,
    @field:SerializedName(
        "name"
    ) var name: String,
    @field:SerializedName("phone") var phone: String,
    @field:SerializedName(
        "mail"
    ) var mail: String,
    @field:SerializedName("service") var service: String,
    @field:SerializedName(
        "password"
    ) var password: String,
    @field:SerializedName("avatar") var avatar: String,
    @field:SerializedName("gender") var gender: String,
    @field:SerializedName(
        "edu"
    ) var edu: String,
    @field:SerializedName("province") var province: String,
    @field:SerializedName(
        "city"
    ) var city: String,
    @field:SerializedName("notify") var notify: String,
    @field:SerializedName("verifiedCode") var verifiedCode: String,
    @field:SerializedName(
        "verifiedAt"
    ) var verifiedAt: String,
    @field:SerializedName("createdAt") var createdAt: String,
    @field:SerializedName(
        "updatedAt"
    ) var updatedAt: String, //    endregion
    @field:SerializedName("deletedAt") var deletedAt: String
)

class UserInfo {
    constructor(
        name: String,
        email: String,
        serviceType: String,
        password: String,
        passwordConfirmation: String,
        token: String
    ) : this(name, email, password, passwordConfirmation, token) {
        this.serviceType = serviceType
    }

    constructor(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        token: String
    ) {
        this.email = email
        this.name = name
        this.password = password
        this.passwordConfirmation = passwordConfirmation
        this.token = token
        this.serviceType = "provider"
    }

    @SerializedName("name")
    var name: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("service_type")
    var serviceType: String? = null

    @SerializedName("password")
    var password: String? = null

    @SerializedName("password_confirmation")
    var passwordConfirmation: String? = null

    @SerializedName("token")
    var token: String? = null
}