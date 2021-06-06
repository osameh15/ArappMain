package ir.arapp.arappmain.model

import com.google.gson.annotations.SerializedName

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

    constructor(picture: Int, title: String, location: String) {
        id = 0
        user_id = 0
        this.picture = picture
        this.title = title
        subtitle = ""
        text = ""
        rate = 0.0
        comments = 0
        this.location = location
        createdAt = ""
        updatedAt = ""
        deletedAt = ""
    }
    //    endregion
}