package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooktheMarksData {
    @SerializedName("id")
    @Expose
    private String id;

    public String getTest_id() {
        return testId;
    }

    public void setTest_id(String test_id) {
        this.testId = test_id;
    }

    @SerializedName("sub_id")
    @Expose
    private String subId;
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
    private String minusmark;
    @SerializedName("marks")
    @Expose
    private String marks;
    @SerializedName("fromAns")
    @Expose
    private String fromAns;
    @SerializedName("toAns")
    @Expose
    private String toAns;
    @SerializedName("solution")
    @Expose
    private String solution;
    @SerializedName("multiple_ans")
    @Expose
    private String multipleAns;
    @SerializedName("Status")
    @Expose
    private String status;
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
    private String type;
    @SerializedName("test_name")
    @Expose
    private String testName;
    @SerializedName("bookmark_id")
    @Expose
    private String bookmarkId;
    @SerializedName("test_id")
    @Expose
    private String testId;

    public BooktheMarksData(String test_id,String id, String subId, String que, String ans1, String ans2, String ans3, String ans4, String ans, String minusmark, String marks, String fromAns, String toAns, String solution, String multipleAns, String status, String video, String videoUrl, String createdate, String type ,String bookmark_id) {
        this.testId = test_id;
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
        this.fromAns = fromAns;
        this.toAns = toAns;
        this.solution = solution;
        this.multipleAns = multipleAns;
        this.status = status;
        this.video = video;
        this.videoUrl = videoUrl;
        this.createdate = createdate;
        this.type = type;
        this.testName = testName;
        this.bookmarkId = bookmark_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
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

    public String getMinusmark() {
        return minusmark;
    }

    public void setMinusmark(String minusmark) {
        this.minusmark = minusmark;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getFromAns() {
        return fromAns;
    }

    public void setFromAns(String fromAns) {
        this.fromAns = fromAns;
    }

    public String getToAns() {
        return toAns;
    }

    public void setToAns(String toAns) {
        this.toAns = toAns;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getMultipleAns() {
        return multipleAns;
    }

    public void setMultipleAns(String multipleAns) {
        this.multipleAns = multipleAns;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(String bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }
}
