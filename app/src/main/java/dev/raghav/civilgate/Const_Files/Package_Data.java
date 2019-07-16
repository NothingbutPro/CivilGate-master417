package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package_Data {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("level_id")
    @Expose
    private String levelId;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("testtime")
    @Expose
    private String testtime;
    @SerializedName("total_que")
    @Expose
    private String totalQue;
    @SerializedName("package_mrp")
    @Expose
    private String packageMrp;
    @SerializedName("package_type")
    @Expose
    private String packageType;
    @SerializedName("package_image")
    @Expose
    private String packageImage;
    @SerializedName("fromdate")
    @Expose
    private String fromdate;
    @SerializedName("todate")
    @Expose
    private String todate;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTesttime() {
        return testtime;
    }

    public void setTesttime(String testtime) {
        this.testtime = testtime;
    }

    public String getTotalQue() {
        return totalQue;
    }

    public void setTotalQue(String totalQue) {
        this.totalQue = totalQue;
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

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
