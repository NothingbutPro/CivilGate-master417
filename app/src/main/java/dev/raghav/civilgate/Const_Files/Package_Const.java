package dev.raghav.civilgate.Const_Files;

public class Package_Const {
    String id,status;
    String package_name, level_id,package_mr, package_type ,package_image,package_des ,package_time,package_ques;

    public String getPackage_time() {
        return package_time;
    }

    public void setPackage_time(String package_time) {
        this.package_time = package_time;
    }

    public String getPackage_ques() {
        return package_ques;
    }

    public void setPackage_ques(String package_ques) {
        this.package_ques = package_ques;
    }

    public Package_Const(String id, String level_id, String status, String package_name, String package_mr, String package_type, String package_image, String package_des, String testtime, String totalQue) {
        this.id = id;
        this.level_id = level_id;
        this.status = status;
        this.package_name = package_name;
        this.package_mr = package_mr;
        this.package_type = package_type;
        this.package_image = package_image;
        this.package_des = package_des;
        this.package_time = testtime;
        this.package_ques = totalQue;
    }

    public String getPackage_des() {
        return package_des;
    }

    public void setPackage_des(String package_des) {
        this.package_des = package_des;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel_id() {
        return level_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
