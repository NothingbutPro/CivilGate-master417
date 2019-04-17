package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Test_Question {
    @SerializedName("data")
    @Expose
    private List<Test_Question_Data> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<Test_Question_Data> getData() {
        return data;
    }

    public void setData(List<Test_Question_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}