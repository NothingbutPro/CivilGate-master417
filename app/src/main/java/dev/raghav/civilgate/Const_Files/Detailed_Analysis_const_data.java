package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detailed_Analysis_const_data {

    @SerializedName("id")
    @Expose
    private String id;
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

    public Detailed_Analysis_const_data(String id, String queAns, String ans, String marks, String time) {
        this.id = id;
        this.queAns = queAns;
        this.ans = ans;
        this.marks = marks;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
