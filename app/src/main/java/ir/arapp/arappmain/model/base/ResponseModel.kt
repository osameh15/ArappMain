package ir.arapp.arappmain.model.base

import com.google.gson.annotations.SerializedName

class GetService{
    @SerializedName("service")
    var service: CompletedService? = null
}
class GetAllServices{
    var services = ArrayList<CompletedService>()
}
class GetAllCategories{
    @SerializedName("categories")
    var categories : List<Category>? = null
    @SerializedName("parent_categories")
    var parentCategories : List<Category>? = null
}
class ResponseModel<T> {
    @SerializedName("Success")
    var success: Boolean = false
    var message: String = ""
    var result: T? = null
}
