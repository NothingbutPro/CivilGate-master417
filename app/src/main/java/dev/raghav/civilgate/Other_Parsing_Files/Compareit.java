package dev.raghav.civilgate.Other_Parsing_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Compareit {
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("data")
    @Expose
    private List<Comareit_data> data = null;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<Comareit_data> getData() {
        return data;
    }

    public void setData(List<Comareit_data> data) {
        this.data = data;
    }
}
