package ir.arapp.arappmain.Model;

import com.google.gson.annotations.SerializedName;

public class BannerItem
{
//    region Variables
    @SerializedName("id")
    private int id;
    @SerializedName("visible")
    private int visible;
    @SerializedName("picture")
    private int picture;
    @SerializedName("text")
    private String text;
    @SerializedName("createdAt")
    private String createdAt;
//    endregion
//    region Methods
//    Constructor

    public BannerItem(int id, int visible, int picture, String text, String createdAt)
    {
        this.id = id;
        this.visible = visible;
        this.picture = picture;
        this.text = text;
        this.createdAt = createdAt;
    }
//    Setter
    public void setId(int id)
    {
        this.id = id;
    }
    public void setVisible(int visible)
    {
        this.visible = visible;
    }
    public void setPicture(int picture)
    {
        this.picture = picture;
    }
    public void setText(String text)
    {
        this.text = text;
    }
    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }
//    Getter
    public int getId()
    {
        return id;
    }
    public int getVisible()
    {
        return visible;
    }
    public int getPicture()
    {
        return picture;
    }
    public String getText()
    {
        return text;
    }
    public String getCreatedAt()
    {
        return createdAt;
    }

//    endregion
}
