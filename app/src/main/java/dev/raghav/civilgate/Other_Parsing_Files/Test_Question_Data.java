package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test_Question_Data {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("sub_id")
    @Expose
    private int subId;
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
    @SerializedName("Ans")
    @Expose
    private String ans;
    @SerializedName("minusmark")
    @Expose
    private int minusmark;
    @SerializedName("marks")
    @Expose
    private int marks;
    @SerializedName("solution")
    @Expose
    private String solution;
    @SerializedName("multiple_ans")
    @Expose
    private int multipleAns;
    @SerializedName("Status")
    @Expose
    private int status;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("Createdate")
    @Expose
    private String createdate;
    @SerializedName("type")
    @Expose
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Test_Question_Data(int id, int subId, String que, String ans1, String ans2, String ans3, String ans4, String ans, int minusmark, int marks, String solution, int multipleAns, int status,int type) {
        this.id = id;
        this.subId = subId;
        this.que = que;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.ans = ans;
        this.minusmark = minusmark;
        this.marks = marks;
        this.solution = solution;
        this.multipleAns = multipleAns;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
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

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public int getMinusmark() {
        return minusmark;
    }

    public void setMinusmark(int minusmark) {
        this.minusmark = minusmark;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getMultipleAns() {
        return multipleAns;
    }

    public void setMultipleAns(int multipleAns) {
        this.multipleAns = multipleAns;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
