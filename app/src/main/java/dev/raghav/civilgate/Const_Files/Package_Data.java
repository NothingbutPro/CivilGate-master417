package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package_Data {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("level_id")
    @Expose
    private String levelId;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("package_mrp")
    @Expose
    private String packageMrp;
    @SerializedName("package_type")
    @Expose
    private String packageType;
    @SerializedName("package_image")
    @Expose
    private String packageImage;
    @SerializedName("status")
    @Expose
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageMrp() {
        return packageMrp;
    }

    public void setPackageMrp(String packageMrp) {
        this.packageMrp = packageMrp;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageImage() {
        return packageImage;
    }

    public void setPackageImage(String packageImage) {
        this.packageImage = packageImage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
