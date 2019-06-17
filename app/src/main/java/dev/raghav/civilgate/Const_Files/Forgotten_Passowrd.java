package dev.raghav.civilgate.Const_Files;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forgotten_Passowrd {
    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("massage")
    @Expose
    private String massage;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }


}
