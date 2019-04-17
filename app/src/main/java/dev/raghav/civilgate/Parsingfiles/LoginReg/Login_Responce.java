package dev.raghav.civilgate.Parsingfiles.LoginReg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.File;

import dev.raghav.civilgate.Api.Long_Login;

public class Login_Responce  {

        @SerializedName("data")
        @Expose
        private Long_Login data;
        @SerializedName("responce")
        @Expose
        private Boolean responce;

        public Long_Login getData() {
            return data;
        }

        public void setData(Long_Login data) {
            this.data = data;
        }

        public Boolean getResponce() {
            return responce;
        }

        public void setResponce(Boolean responce) {
            this.responce = responce;
        }

}
