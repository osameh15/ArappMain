package ir.arapp.arappmain.model.base

import com.google.gson.annotations.SerializedName

data class Banner     //    endregion
//    region Methods
//    Constructor
    (//    Setter
    //    Getter
    //    region Variables
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("visible") var visible: Int,
    @field:SerializedName(
        "picture"
    ) var picture: Int,
    @field:SerializedName("text") var text: String, //    endregion
    @field:SerializedName(
        "createdAt"
    ) var createdAt: String
)