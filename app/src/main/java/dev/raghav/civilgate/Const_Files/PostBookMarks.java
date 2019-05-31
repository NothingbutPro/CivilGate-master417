package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostBookMarks {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("massage")
    @Expose
    private PostBookMarksData massage;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public PostBookMarksData getMassage() {
        return massage;
    }

    public void setMassage(PostBookMarksData massage) {
        this.massage = massage;
    }


}
