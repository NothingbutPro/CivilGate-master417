package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Full_Solutions {

    @SerializedName("data")
    @Expose
    private List<Full_Solution_Data> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<Full_Solution_Data> getData() {
        return data;
    }

    public void setData(List<Full_Solution_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
