package com.example.coms_3009a_banking_system.Registration;

public class Model {
    private String name, id, email, phoneNo,image;

    public Model(String name, String id, String email, String phoneNo, String image)
    {
        this.name =name;
        this.id = id;
        this.email =email;
        this.phoneNo =phoneNo;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
