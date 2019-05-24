package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Percentage {

    @SerializedName("data")
    @Expose
    private Double data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

}
