package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooktheMarks {


    @SerializedName("data")
    @Expose
    private List<BooktheMarksData> data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<BooktheMarksData> getData() {
        return data;
    }

    public void setData(List<BooktheMarksData> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
