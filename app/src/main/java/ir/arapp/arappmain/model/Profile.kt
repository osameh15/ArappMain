package ir.arapp.arappmain.model

import com.google.gson.annotations.SerializedName

class Profile     //    endregion
//    region Methods
//    Constructor
    (//    Setter
    //    Getter
    //    region Variable
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("image") var image: Int, //    endregion
    @field:SerializedName(
        "name"
    ) var name: String
)