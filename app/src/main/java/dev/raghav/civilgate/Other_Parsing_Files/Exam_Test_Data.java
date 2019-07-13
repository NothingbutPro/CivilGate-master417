package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exam_Test_Data{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("test_name")
    @Expose
    private String testName;
    @SerializedName("testtime")
    @Expose
    private String testtime;
    @SerializedName("subject_ids")
    @Expose
    private String subjectIds;
    @SerializedName("test_end_date")
    @Expose
    private String testEndDate;
    @SerializedName("total_mark")
    @Expose
    private String totalMark;
    @SerializedName("total_que")
    @Expose
    private String totalQue;
    @SerializedName("QStatus")
    @Expose
    private String qStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTesttime() {
        return testtime;
    }

    public void setTesttime(String testtime) {
        this.testtime = testtime;
    }

    public String getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(String subjectIds) {
        this.subjectIds = subjectIds;
    }

    public String getTestEndDate() {
        return testEndDate;
    }

    public void setTestEndDate(String testEndDate) {
        this.testEndDate = testEndDate;
    }

    public String getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(String totalMark) {
        this.totalMark = totalMark;
    }

    public String getTotalQue() {
        return totalQue;
    }

    public void setTotalQue(String totalQue) {
        this.totalQue = totalQue;
    }

    public String getQStatus() {
        return qStatus;
    }

    public void setQStatus(String qStatus) {
        this.qStatus = qStatus;
    }
}