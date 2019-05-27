package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brain_Questions {

    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("massage")
    @Expose
    private Brain_Questions_Data massage;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public Brain_Questions_Data getMassage() {
        return massage;
    }

    public void setMassage(Brain_Questions_Data massage) {
        this.massage = massage;
    }
}
