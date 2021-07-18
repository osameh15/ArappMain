package ir.arapp.arappmain.model.base

import com.google.gson.annotations.SerializedName
import java.util.*

class Service {
    //    region Setter
    //    endregion
    //    region Getter
    //    region Variable
    @SerializedName("id")
    var id: Int

    @SerializedName("user_id")
    var user_id: Int

    @SerializedName("picture")
    var picture: Int

    @SerializedName("title")
    var title: String

    @SerializedName("subtitle")
    var subtitle: String

    @SerializedName("text")
    var text: String

    @SerializedName("rate")
    var rate: Double

    @SerializedName("comments")
    var comments: Int

    @SerializedName("location")
    var location: String

    @SerializedName("createdAt")
    var createdAt: String

    @SerializedName("updatedAt")
    var updatedAt: String

    //    endregion
    @SerializedName("deletedAt")
    var deletedAt: String

    //    endregion
    //    region Methods
    //    Constructor
    constructor(
        id: Int,
        user_id: Int,
        picture: Int,
        title: String,
        subtitle: String,
        text: String,
        rate: Double,
        comments: Int,
        location: String,
        createdAt: String,
        updatedAt: String,
        deletedAt: String
    ) {
        this.id = id
        this.user_id = user_id
        this.picture = picture
        this.title = title
        this.subtitle = subtitle
        this.text = text
        this.rate = rate
        this.comments = comments
        this.location = location
        this.createdAt = createdAt
        this.updatedAt = updatedAt
        this.deletedAt = deletedAt
    }

    constructor(picture: Int, title: String, location: String, rate: Double) {
        id = 0
        user_id = 0
        this.picture = picture
        this.title = title
        subtitle = ""
        text = ""
        this.rate = rate
        comments = 0
        this.location = location
        createdAt = ""
        updatedAt = ""
        deletedAt = ""
    }
    //    endregion
}


class PostServiceData {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("start_time")
    var startTime :String? = null

    @SerializedName("end_time")
    var endTime :String? = null

    @SerializedName("category_id")
    var categoryId: Int = 1

    @SerializedName("address")
    var address: String? = null

    @SerializedName("summry")
    var summary: String? = null

    @SerializedName("birth")
    var birth: Int = 10

    @SerializedName("description")
    var description: String? = null


    @SerializedName("avatar")
    var pictureBase64: String? = null

    constructor(pictureBase64: String, title: String) {
        this.pictureBase64 = pictureBase64
        this.title = title
    }

    constructor()

}

class GetServiceData {

    @SerializedName("created_at")
    var createdAt: String = ""

    @SerializedName("rate")
    var rate: String = ""


    @SerializedName("updated_at")
    var updatedAt: String = ""

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("user_id")
    var userId: Int = 0

    @SerializedName("status")
    var status: String = ""


    @SerializedName("title")
    var title: String = ""

    @SerializedName("start_time")
    var startTime = "22:30"

    @SerializedName("end_time")
    var endTime = "23:30"

    @SerializedName("category_id")
    var categoryId: Int = 1

    @SerializedName("address")
    var address: String = "تهران - میدان انقلاب"

    @SerializedName("summry")
    var summry: String = "summry"

    @SerializedName("birth")
    var birth: Int = 10

    @SerializedName("description")
    var description: String = "description"


    @SerializedName("avatar")
    var pictureUrl: String? = null

}