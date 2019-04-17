package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Exam_Test{
    @SerializedName("data")
    @Expose
    private List<Exam_Test_Data> data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<Exam_Test_Data> getData() {
        return data;
    }

    public void setData(List<Exam_Test_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}