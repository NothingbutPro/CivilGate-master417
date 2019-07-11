package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewBookMark {
    @SerializedName("data")
    @Expose
    private List<NewBookMarkData> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<NewBookMarkData> getData() {
        return data;
    }

    public void setData(List<NewBookMarkData> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}