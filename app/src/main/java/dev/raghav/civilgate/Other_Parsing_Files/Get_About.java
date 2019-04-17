package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Get_About {

    @SerializedName("data")
    @Expose
    public Get_About_Data data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public Get_About_Data getData() {
        return data;
    }

    public void setData(Get_About_Data data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
