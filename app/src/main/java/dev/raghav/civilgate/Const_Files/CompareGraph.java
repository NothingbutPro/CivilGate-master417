package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompareGraph {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("data")
    @Expose
    private List<CompareGraphData> data ;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<CompareGraphData> getData() {
        return data;
    }

    public void setData(List<CompareGraphData> data) {
        this.data = data;
    }
}
