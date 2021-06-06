package ir.arapp.arappmain.model

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