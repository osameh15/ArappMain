package ir.arapp.arappmain.model.base

import com.google.gson.annotations.SerializedName

class Notification     //    endregion
//    region Methods
//    Constructor
    (//    Setter
    //    Getter
    //    region Variables
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("title") var title: String,
    @field:SerializedName(
        "subtitle"
    ) var subtitle: String,
    @field:SerializedName("text") var text: String,
    @field:SerializedName(
        "image"
    ) var image: Int,
    @field:SerializedName("imageContext") var imageContext: Int, //    endregion
    @field:SerializedName(
        "createdAt"
    ) var createdAt: String
)