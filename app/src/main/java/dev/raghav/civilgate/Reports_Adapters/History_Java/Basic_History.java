package dev.raghav.civilgate.Reports_Adapters.History_Java;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Basic_History {



    @SerializedName("levelname")
    @Expose
    private String levelname;
    @SerializedName("test_name")
    @Expose
    private String testName;
    @SerializedName("t_id")
    @Expose
    private String tId;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("level_id")
    @Expose
    private String levelId;
    @SerializedName("level_sub_id")
    @Expose
    private String levelSubId;
    @SerializedName("totcandidate")
    @Expose
    private String totcandidate;
    @SerializedName("totalQue")
    @Expose
    private String totalQue;
    @SerializedName("mark")
    @Expose
    private String mark;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("rightmark")
    @Expose
    private String rightmark;
    @SerializedName("negative")
    @Expose
    private String negative;
    @SerializedName("nagtivemarkone")
    @Expose
    private String nagtivemarkone;
    @SerializedName("nagtivemarktwo")
    @Expose
    private String nagtivemarktwo;
    @SerializedName("left")
    @Expose
    private String left;
    @SerializedName("Mytime")
    @Expose
    private String mytime;
    @SerializedName("unproductive")
    @Expose
    private String unproductive;
    @SerializedName("idleTime")
    @Expose
    private String idleTime;
    @SerializedName("correct")
    @Expose
    private String correct;
    @SerializedName("incorrect")
    @Expose
    private String incorrect;
    @SerializedName("totalmark")
    @Expose
    private String totalmark;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private String status;
    public Basic_History(String levelname, String testName, String tId, String studentId, String levelId, String levelSubId, String date) {
        this.levelname = levelname;
        this.testName = testName;
        this.tId = tId;
        this.studentId = studentId;
        this.levelId = levelId;
        this.levelSubId = levelSubId;
        this.date = date;
    }
    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTId() {
        return tId;
    }

    public void setTId(String tId) {
        this.tId = tId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getTotcandidate() {
        return totcandidate;
    }

    public void setTotcandidate(String totcandidate) {
        this.totcandidate = totcandidate;
    }

    public String getTotalQue() {
        return totalQue;
    }

    public void setTotalQue(String totalQue) {
        this.totalQue = totalQue;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRightmark() {
        return rightmark;
    }

    public void setRightmark(String rightmark) {
        this.rightmark = rightmark;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public String getNagtivemarkone() {
        return nagtivemarkone;
    }

    public void setNagtivemarkone(String nagtivemarkone) {
        this.nagtivemarkone = nagtivemarkone;
    }

    public String getNagtivemarktwo() {
        return nagtivemarktwo;
    }

    public void setNagtivemarktwo(String nagtivemarktwo) {
        this.nagtivemarktwo = nagtivemarktwo;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getMytime() {
        return mytime;
    }

    public void setMytime(String mytime) {
        this.mytime = mytime;
    }

    public String getUnproductive() {
        return unproductive;
    }

    public void setUnproductive(String unproductive) {
        this.unproductive = unproductive;
    }

    public String getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(String idleTime) {
        this.idleTime = idleTime;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(String incorrect) {
        this.incorrect = incorrect;
    }

    public String getTotalmark() {
        return totalmark;
    }

    public void setTotalmark(String totalmark) {
        this.totalmark = totalmark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
