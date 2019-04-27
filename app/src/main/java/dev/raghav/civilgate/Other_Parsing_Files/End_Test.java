package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class End_Test {


    @SerializedName("data")
    @Expose
    public List<End_Test_Data> data;
    @SerializedName("responce")
    @Expose
    public Boolean responce;

    public List<End_Test_Data> getData() {
        return data;
    }

    public void setData(List<End_Test_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

}
