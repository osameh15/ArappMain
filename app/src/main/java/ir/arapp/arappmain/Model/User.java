package ir.arapp.arappmain.Model;

import com.google.gson.annotations.SerializedName;

public class User
{
//    region Variable
    @SerializedName("id")
    String id;
    @SerializedName("type")
    String type;
    @SerializedName("name")
    String name;
    @SerializedName("phone")
    String phone;
    @SerializedName("mail")
    String mail;
    @SerializedName("service")
    String service;
    @SerializedName("password")
    String password;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("gender")
    String gender;
    @SerializedName("edu")
    String edu;
    @SerializedName("province")
    String province;
    @SerializedName("city")
    String city;
    @SerializedName("notify")
    String notify;
    @SerializedName("verifiedCode")
    String verifiedCode;
    @SerializedName("verifiedAt")
    String verifiedAt;
    @SerializedName("createdAt")
    String createdAt;
    @SerializedName("updatedAt")
    String updatedAt;
    @SerializedName("deletedAt")
    String deletedAt;
//    endregion

//    region Methods
//    region Getter
    public String getId()
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
//    region Setter
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
//    endregion
}
