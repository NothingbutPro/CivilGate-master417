package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Detailed_Analysis_const {
    @SerializedName("data")
    @Expose
    private List<Detailed_Analysis_const_data> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<Detailed_Analysis_const_data> getData() {
        return data;
    }

    public void setData(List<Detailed_Analysis_const_data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
