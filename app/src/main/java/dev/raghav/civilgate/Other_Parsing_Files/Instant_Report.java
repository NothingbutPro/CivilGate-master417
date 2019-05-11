package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instant_Report {
    @SerializedName("data")
    @Expose
    private Instant_Report_Data data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public Instant_Report_Data getData() {
        return data;
    }

    public void setData(Instant_Report_Data data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

}
