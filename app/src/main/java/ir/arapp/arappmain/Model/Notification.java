package ir.arapp.arappmain.Model;

import com.google.gson.annotations.SerializedName;

public class Notification
{
//    region Variables
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("text")
    private String text;
    @SerializedName("image")
    private int image;
    @SerializedName("imageContext")
    private int imageContext;
    @SerializedName("createdAt")
    private String createdAt;
//    endregion

//    region Methods
//    Constructor
    public Notification(int id, String title, String subtitle, String text, int image, int imageContext, String createdAt)
    {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.text = text;
        this.image = image;
        this.imageContext = imageContext;
        this.createdAt = createdAt;
    }
//    Setter
    public void setId(int id)
    {
        this.id = id;
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
    public void setImage(int image)
    {
        this.image = image;
    }
    public void setImageContext(int imageContext)
    {
        this.imageContext = imageContext;
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
    public String getTitle()
    {
        return title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public String getText() {
        return text;
    }
    public int getImage() {
        return image;
    }
    public int getImageContext()
    {
        return imageContext;
    }
    public String getCreatedAt() {
        return createdAt;
    }
//    endregion
}
