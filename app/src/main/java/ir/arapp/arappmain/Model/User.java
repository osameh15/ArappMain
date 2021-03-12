package ir.arapp.arappmain.Model;

import com.google.gson.annotations.SerializedName;

public class User
{
//    region Variable
    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;
    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("mail")
    private String mail;
    @SerializedName("service")
    private String service;
    @SerializedName("password")
    private String password;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("gender")
    private String gender;
    @SerializedName("edu")
    private String edu;
    @SerializedName("province")
    private String province;
    @SerializedName("city")
    private String city;
    @SerializedName("notify")
    private String notify;
    @SerializedName("verifiedCode")
    private String verifiedCode;
    @SerializedName("verifiedAt")
    private String verifiedAt;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("deletedAt")
    private String deletedAt;
//    endregion

//    region Methods
//    Constructor
    public User(int id, String type, String name, String phone, String mail, String service, String password,
                String avatar, String gender, String edu, String province, String city, String notify,
                String verifiedCode, String verifiedAt, String createdAt, String updatedAt, String deletedAt)
    {
        this.id = id;
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.service = service;
        this.password = password;
        this.avatar = avatar;
        this.gender = gender;
        this.edu = edu;
        this.province = province;
        this.city = city;
        this.notify = notify;
        this.verifiedCode = verifiedCode;
        this.verifiedAt = verifiedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }
//    region Setter
    public void setId(int id)
    {
        this.id = id;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public void setName(String name)
{
    this.name = name;
}
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public void setMail(String mail)
    {
        this.mail = mail;
    }
    public void setService(String service)
    {
        this.service = service;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    public void setEdu(String edu)
    {
        this.edu = edu;
    }
    public void setProvince(String province)
    {
        this.province = province;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    public void setNotify(String notify)
    {
        this.notify = notify;
    }
    public void setVerifiedCode(String verifiedCode)
    {
        this.verifiedCode = verifiedCode;
    }
    public void setVerifiedAt(String verifiedAt)
    {
        this.verifiedAt = verifiedAt;
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
    public String getType()
    {
        return type;
    }
    public String getName()
    {
        return name;
    }
    public String getPhone()
    {
        return phone;
    }
    public String getMail()
    {
        return mail;
    }
    public String getService()
    {
        return service;
    }
    public String getPassword()
    {
        return password;
    }
    public String getAvatar()
    {
        return avatar;
    }
    public String getGender()
    {
        return gender;
    }
    public String getEdu()
    {
        return edu;
    }
    public String getProvince()
    {
        return province;
    }
    public String getCity()
    {
        return city;
    }
    public String getNotify()
    {
        return notify;
    }
    public String getVerifiedCode()
    {
        return verifiedCode;
    }
    public String getVerifiedAt()
    {
        return verifiedAt;
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
