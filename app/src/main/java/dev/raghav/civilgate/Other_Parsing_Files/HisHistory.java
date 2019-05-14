package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HisHistory {

    @SerializedName("data")
    @Expose
    private List<HisHistoryData> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<HisHistoryData> getData() {
        return data;
    }

    public void setData(List<HisHistoryData> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
