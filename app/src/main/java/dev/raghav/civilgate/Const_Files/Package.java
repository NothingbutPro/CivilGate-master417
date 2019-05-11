package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Package {
    @SerializedName("data")
    @Expose
    private List<Package_Data> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;



    public List<Package_Data> getData() {
        return data;
    }

    public void setData(List<Package_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
