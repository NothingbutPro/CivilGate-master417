package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Full_Solution_Data {
    @SerializedName("queid")
    @Expose
    private String queid;
    @SerializedName("Que")
    @Expose
    private String que;
    @SerializedName("Ans_1")
    @Expose
    private String ans1;
    @SerializedName("Ans_2")
    @Expose
    private String ans2;
    @SerializedName("Ans_3")
    @Expose
    private String ans3;
    @SerializedName("Ans_4")
    @Expose
    private String ans4;
    @SerializedName("solution")
    @Expose
    private String solution;
    @SerializedName("que_ans")
    @Expose
    private String queAns;
    @SerializedName("Ans")
    @Expose
    private String ans;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("marks")
    @Expose
    private String marks;
    @SerializedName("que_status")
    @Expose
    private String queStatus;
    @SerializedName("time")
    @Expose
    private String time;

    public Full_Solution_Data(String queid ,String  queAns , String ans ) {
        this.queid = queid;
        this.queAns =  queAns;
        this.ans = ans;

    }

    public String getQueid() {
        return queid;
    }

    public void setQueid(String queid) {
        this.queid = queid;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getQueAns() {
        return queAns;
    }

    public void setQueAns(String queAns) {
        this.queAns = queAns;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
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

}
