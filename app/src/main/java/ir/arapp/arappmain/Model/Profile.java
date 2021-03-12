package ir.arapp.arappmain.Model;

import com.google.gson.annotations.SerializedName;

public class Profile
{
//    region Variable
    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private int image;
    @SerializedName("name")
    private String name;
//    endregion

//    region Methods
//    Constructor
    public Profile(int id, int image, String name)
    {
        this.id = id;
        this.image = image;
        this.name = name;
    }
//    Setter
    public void setId(int id)
    {
        this.id = id;
    }
    public void setImage(int image)
    {
        this.image = image;
    }
    public void setName(String name)
    {
        this.name = name;
    }
//    Getter
    public int getId()
    {
        return id;
    }
    public int getImage()
    {
        return image;
    }
    public String getName()
    {
        return name;
    }
//    endregion
}
