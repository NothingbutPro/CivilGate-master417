package dev.raghav.civilgate.Parsingfiles.LoginReg;

public class RegisPars_responce {
    Boolean responce;
    Reg_User_Info user_info;

    public RegisPars_responce(Boolean responce, Reg_User_Info user_info) {
        this.responce = responce;
        this.user_info = user_info;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
