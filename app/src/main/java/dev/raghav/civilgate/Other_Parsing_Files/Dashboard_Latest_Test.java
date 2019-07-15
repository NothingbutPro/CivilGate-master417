package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dashboard_Latest_Test {

    @SerializedName("data")
    @Expose
    private List<Dashboard_Latest_Test_Data> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<Dashboard_Latest_Test_Data> getData() {
        return data;
    }

    public void setData(List<Dashboard_Latest_Test_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

}
