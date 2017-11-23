package neosoft.training.neostore.model;

/**
 * Created by webwerks on 22/11/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderListModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<OrderListData> data = null;
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

    public List<OrderListData> getData() {
        return data;
    }

    public void setData(List<OrderListData> data) {
        this.data = data;
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
        return "OrderListModel{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", userMsg='" + userMsg + '\'' +
                '}';
    }
}