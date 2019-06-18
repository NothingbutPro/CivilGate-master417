package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompareGraphData {

    @SerializedName("test_id")
    @Expose
    private String testId;
    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("totalmark")
    @Expose
    private String totalmark;
    @SerializedName("rank")
    @Expose
    private Integer rank;

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTotalmark() {
        return totalmark;
    }

    public void setTotalmark(String totalmark) {
        this.totalmark = totalmark;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
