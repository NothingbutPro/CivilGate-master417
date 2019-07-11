package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyoverallData {


    @SerializedName("totaltest")
    @Expose
    private String totaltest;
    @SerializedName("solved")
    @Expose
    private String solved;
    @SerializedName("credit")
    @Expose
    private String credit;
    @SerializedName("score")
    @Expose
    private String score;

    public String getTotaltest() {
        return totaltest;
    }

    public void setTotaltest(String totaltest) {
        this.totaltest = totaltest;
    }

    public String getSolved() {
        return solved;
    }

    public void setSolved(String solved) {
        this.solved = solved;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
