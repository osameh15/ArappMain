package ir.arapp.arappmain.Model;

import com.google.gson.annotations.SerializedName;

public class CategoryItem
{
//    region Variable
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("picture")
    int picture;
//    endregion
//    region Methods
//    Constructor
    public CategoryItem(String id, String name, int picture)
    {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }
    //    region Setter
    public void setId(String id)
    {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setPicture(int picture)
    {
        this.picture = picture;
    }
//    endregion
//    region Getter
    public String getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public int getPicture()
    {
        return picture;
    }
//    endregion
//    endregion
}