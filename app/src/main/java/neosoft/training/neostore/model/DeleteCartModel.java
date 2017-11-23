package neosoft.training.neostore.model;

/**
 * Created by webwerks on 21/11/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteCartModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private Boolean data;
    @SerializedName("total_carts")
    @Expose
    private Integer totalCarts;
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

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }

    public Integer getTotalCarts() {
        return totalCarts;
    }

    public void setTotalCarts(Integer totalCarts) {
        this.totalCarts = totalCarts;
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
        return "DeleteCartModel{" +
                "status=" + status +
                ", data=" + data +
                ", totalCarts=" + totalCarts +
                ", message='" + message + '\'' +
                ", userMsg='" + userMsg + '\'' +
                '}';
    }
}
