package ir.arapp.arappmain.Model;

import com.google.gson.annotations.SerializedName;

public class User
{
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

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getEdu()
    {
        return edu;
    }

    public void setEdu(String edu)
    {
        this.edu = edu;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getNotify()
    {
        return notify;
    }

    public void setNotify(String notify)
    {
        this.notify = notify;
    }

    public String getVerifiedCode()
    {
        return verifiedCode;
    }

    public void setVerifiedCode(String verifiedCode)
    {
        this.verifiedCode = verifiedCode;
    }

    public String getVerifiedAt()
    {
        return verifiedAt;
    }

    public void setVerifiedAt(String verifiedAt)
    {
        this.verifiedAt = verifiedAt;
    }

    public String getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt()
    {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt)
    {
        this.deletedAt = deletedAt;
    }
}
