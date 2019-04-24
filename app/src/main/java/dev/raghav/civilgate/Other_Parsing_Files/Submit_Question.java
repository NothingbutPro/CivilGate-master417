package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Submit_Question {
    @SerializedName("data")
    @Expose
    private Submit_Question_Data data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public Submit_Question_Data getData() {
        return data;
    }

    public void setData(Submit_Question_Data data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
