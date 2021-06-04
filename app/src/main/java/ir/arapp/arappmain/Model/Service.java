package ir.arapp.arappmain.Model;

import com.google.gson.annotations.SerializedName;

public class Service
{
//    region Variable
    @SerializedName("id")
    private int id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("picture")
    private int picture;
    @SerializedName("title")
    private String title;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("text")
    private String text;
    @SerializedName("rate")
    private double rate;
    @SerializedName("comments")
    private int comments;
    @SerializedName("location")
    private String location;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("deletedAt")
    private String deletedAt;
//    endregion

//    region Methods
//    Constructor

    public Service(int id, int user_id, int picture, String title, String subtitle, String text, double rate, int comments, String location,
                   String createdAt, String updatedAt, String deletedAt)
    {
        this.id = id;
        this.user_id = user_id;
        this.picture = picture;
        this.title = title;
        this.subtitle = subtitle;
        this.text = text;
        this.rate = rate;
        this.comments = comments;
        this.location = location;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public Service(int picture,String title,String location){
        this.id = 0;
        this.user_id = 0;
        this.picture = picture;
        this.title = title;
        this.subtitle = "";
        this.text = "";
        this.rate = 0.0;
        this.comments = 0;
        this.location = location;
        this.createdAt = "";
        this.updatedAt = "";
        this.deletedAt = "";
    }

//    region Setter
    public void setId(int id)
    {
        this.id = id;
    }
    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }
    public void setPicture(int picture)
    {
        this.picture = picture;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setSubtitle(String subtitle)
    {
        this.subtitle = subtitle;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public void setRate(double rate)
    {
        this.rate = rate;
    }
    public void setComments(int comments)
    {
        this.comments = comments;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }
    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(String updatedAt)
    {
        this.updatedAt = updatedAt;
    }
    public void setDeletedAt(String deletedAt)
    {
        this.deletedAt = deletedAt;
    }
//    endregion
//    region Getter
    public int getId()
    {
        return id;
    }
    public int getUser_id()
    {
        return user_id;
    }
    public int getPicture()
    {
        return picture;
    }
    public String getTitle()
    {
        return title;
    }
    public String getSubtitle()
    {
        return subtitle;
    }
    public String getText()
    {
        return text;
    }
    public double getRate()
    {
        return rate;
    }
    public int getComments()
    {
        return comments;
    }
    public String getLocation()
    {
        return location;
    }
    public String getCreatedAt()
    {
        return createdAt;
    }
    public String getUpdatedAt()
    {
        return updatedAt;
    }
    public String getDeletedAt()
    {
        return deletedAt;
    }
//    endregion
//    endregion
}
