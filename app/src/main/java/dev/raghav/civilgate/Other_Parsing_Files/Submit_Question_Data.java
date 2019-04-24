package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Submit_Question_Data {
    @SerializedName("s_id")
    @Expose
    private String sId;
    @SerializedName("level_id")
    @Expose
    private String levelId;
    @SerializedName("level_sub_id")
    @Expose
    private String levelSubId;
    @SerializedName("Que_id")
    @Expose
    private String queId;
    @SerializedName("que_ans")
    @Expose
    private String queAns;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("que_status")
    @Expose
    private String queStatus;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("QStatus")
    @Expose
    private String qStatus;
    @SerializedName("date")
    @Expose
    private String date;

    public String getSId() {
        return sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelSubId() {
        return levelSubId;
    }

    public void setLevelSubId(String levelSubId) {
        this.levelSubId = levelSubId;
    }

    public String getQueId() {
        return queId;
    }

    public void setQueId(String queId) {
        this.queId = queId;
    }

    public String getQueAns() {
        return queAns;
    }

    public void setQueAns(String queAns) {
        this.queAns = queAns;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getQueStatus() {
        return queStatus;
    }

    public void setQueStatus(String queStatus) {
        this.queStatus = queStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQStatus() {
        return qStatus;
    }

    public void setQStatus(String qStatus) {
        this.qStatus = qStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
