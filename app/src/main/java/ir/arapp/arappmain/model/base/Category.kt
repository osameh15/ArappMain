package ir.arapp.arappmain.model.base

import com.google.gson.annotations.SerializedName

class Category {
    constructor()
    constructor(title:String,slug:String){
        this.slug = slug
        this.title = title
    }
    constructor(title: String,slug: String,parentId:Int):this(title,slug){
        this.parentId = parentId
    }
    constructor(id:Int,title:String){
        this.id = id
        this.title = title
    }
    @SerializedName("id")
    var id: Int? = null

    @SerializedName("title")
    var title: String? = null //    endregion

    @SerializedName("slug")
    var slug: String? = null

    @SerializedName("parent_id")
    var parentId: Int? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("parent")
    var parent: String? = null


    @SerializedName(
        "picture"
    )
    var picture: String? = null

}