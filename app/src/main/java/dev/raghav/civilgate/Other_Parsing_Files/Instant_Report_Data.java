package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instant_Report_Data {
    @SerializedName("totcandidate")
    @Expose
    private Integer totcandidate;
    @SerializedName("totalQue")
    @Expose
    private Integer totalQue;
    @SerializedName("mark")
    @Expose
    private String mark;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("rightmark")
    @Expose
    private Integer rightmark;
    @SerializedName("negative")
    @Expose
    private String negative;
    @SerializedName("left")
    @Expose
    private Integer left;
    @SerializedName("Mytime")
    @Expose
    private String mytime;
    @SerializedName("unproductive")
    @Expose
    private Integer unproductive;
    @SerializedName("idleTime")
    @Expose
    private String idleTime;
    @SerializedName("correct")
    @Expose
    private Integer correct;
    @SerializedName("incorrect")
    @Expose
    private Integer incorrect;

    public Integer getTotcandidate() {
        return totcandidate;
    }

    public void setTotcandidate(Integer totcandidate) {
        this.totcandidate = totcandidate;
    }

    public Integer getTotalQue() {
        return totalQue;
    }

    public void setTotalQue(Integer totalQue) {
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

    public Integer getRightmark() {
        return rightmark;
    }

    public void setRightmark(Integer rightmark) {
        this.rightmark = rightmark;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public String getMytime() {
        return mytime;
    }

    public void setMytime(String mytime) {
        this.mytime = mytime;
    }

    public Integer getUnproductive() {
        return unproductive;
    }

    public void setUnproductive(Integer unproductive) {
        this.unproductive = unproductive;
    }

    public String getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(String idleTime) {
        this.idleTime = idleTime;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Integer getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(Integer incorrect) {
        this.incorrect = incorrect;
    }
}
