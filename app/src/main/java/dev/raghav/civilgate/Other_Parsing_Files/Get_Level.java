package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Get_Level {
    @SerializedName("data")
    @Expose
    private List<New_Level_Data> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<New_Level_Data> getData() {
        return data;
    }

    public void setData(List<New_Level_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
