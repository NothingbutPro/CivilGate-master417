package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exam_Test_Data{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("cat_id")
    @Expose
    private int catId;
    @SerializedName("test_name")
    @Expose
    private String testName;
    @SerializedName("test_type")
    @Expose
    private int testType;
    @SerializedName("test_start_date")
    @Expose
    private String testStartDate;
    @SerializedName("test_end_date")
    @Expose
    private String testEndDate;
    @SerializedName("testtime")
    @Expose
    private float testtime;
    @SerializedName("instant_result")
    @Expose
    private int instantResult;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("subject_ids")
    @Expose
    private String subjectIds;
    @SerializedName("status")
    @Expose
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTestType() {
        return testType;
    }

    public void setTestType(int testType) {
        this.testType = testType;
    }

    public String getTestStartDate() {
        return testStartDate;
    }

    public void setTestStartDate(String testStartDate) {
        this.testStartDate = testStartDate;
    }

    public String getTestEndDate() {
        return testEndDate;
    }

    public void setTestEndDate(String testEndDate) {
        this.testEndDate = testEndDate;
    }

    public Float getTesttime() {
        return testtime;
    }

    public void setTesttime(float testtime) {
        this.testtime = testtime;
    }

    public int getInstantResult() {
        return instantResult;
    }

    public void setInstantResult(int instantResult) {
        this.instantResult = instantResult;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(String subjectIds) {
        this.subjectIds = subjectIds;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}