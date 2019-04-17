package dev.raghav.civilgate.Parsingfiles.LoginReg;

import java.io.File;

public class Reg_User_Info {
int id,status;
String name ,mobile,email,password,passout_year,collage_name,address;
File profile_image ,sign_image;

    public Reg_User_Info(int id, int status, String name, String mobile, String email, String password, String passout_year, String collage_name, String address) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.passout_year = passout_year;
        this.collage_name = collage_name;
        this.address = address;
    }

    public Reg_User_Info(int id, int status, String name, String mobile, String email, String password, String passout_year, String collage_name, String address, File profile_image, File sign_image) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.passout_year = passout_year;
        this.collage_name = collage_name;
        this.address = address;
        this.profile_image = profile_image;
        this.sign_image = sign_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassout_year() {
        return passout_year;
    }

    public void setPassout_year(String passout_year) {
        this.passout_year = passout_year;
    }

    public String getCollage_name() {
        return collage_name;
    }

    public void setCollage_name(String collage_name) {
        this.collage_name = collage_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public File getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(File profile_image) {
        this.profile_image = profile_image;
    }

    public File getSign_image() {
        return sign_image;
    }

    public void setSign_image(File sign_image) {
        this.sign_image = sign_image;
    }
}
