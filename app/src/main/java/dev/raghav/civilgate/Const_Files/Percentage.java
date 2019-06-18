package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Percentage {

    @SerializedName("data")
    @Expose
    private float data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

}
