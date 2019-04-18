package dev.raghav.civilgate.Test_Activities.Test_Types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestStart {

    @SerializedName("data")
    @Expose
    private List<TestStartData> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<TestStartData> getData() {
        return data;
    }

    public void setData(List<TestStartData> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
