package ir.arapp.arappmain.model

import com.google.gson.annotations.SerializedName

class Category     //    endregion
//    endregion
//    region Methods
//    Constructor
    (//    region Setter
    //    endregion
    //    region Getter
    //    region Variable
    @field:SerializedName("id") var id: Int,
    @field:SerializedName("name") var name: String, //    endregion
    @field:SerializedName(
        "picture"
    ) var picture: Int
)