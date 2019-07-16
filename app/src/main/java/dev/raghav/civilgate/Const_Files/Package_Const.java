package dev.raghav.civilgate.Const_Files;

public class Package_Const {
    int id,status;
    String package_name, level_id,package_mr, package_type ,package_image,package_des;

    public Package_Const(int id, String level_id, int status, String package_name, String package_mr, String package_type, String package_image,String package_des) {
        this.id = id;
        this.level_id = level_id;
        this.status = status;
        this.package_name = package_name;
        this.package_mr = package_mr;
        this.package_type = package_type;
        this.package_image = package_image;
        this.package_des = package_des;
    }

    public String getPackage_des() {
        return package_des;
    }

    public void setPackage_des(String package_des) {
        this.package_des = package_des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel_id() {
        return level_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_mr() {
        return package_mr;
    }

    public void setPackage_mr(String package_mr) {
        this.package_mr = package_mr;
    }

    public String getPackage_type() {
        return package_type;
    }

    public void setPackage_type(String package_type) {
        this.package_type = package_type;
    }

    public String getPackage_image() {
        return package_image;
    }

    public void setPackage_image(String package_image) {
        this.package_image = package_image;
    }
}
