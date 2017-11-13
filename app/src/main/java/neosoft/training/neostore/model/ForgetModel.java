package neosoft.training.neostore.model;

/**
 * Created by webwerks on 9/11/17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_msg")
    @Expose
    private String userMsg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserMsg() {
        return userMsg;
    }

    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg;
    }

    @Override
    public String toString() {
        return "ForgetModel{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", userMsg='" + userMsg + '\'' +
                '}';
    }
}