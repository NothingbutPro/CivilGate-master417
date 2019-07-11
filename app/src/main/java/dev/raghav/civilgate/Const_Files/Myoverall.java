package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Myoverall {
    @SerializedName("data")
    @Expose
    private MyoverallData data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public MyoverallData getData() {
        return data;
    }

    public void setData(MyoverallData data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }


}
