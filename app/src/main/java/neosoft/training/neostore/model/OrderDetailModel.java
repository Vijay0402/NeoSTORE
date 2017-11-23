package neosoft.training.neostore.model;

/**
 * Created by webwerks on 22/11/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailModel {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private OrderDetailData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OrderDetailData getData() {
        return data;
    }

    public void setData(OrderDetailData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrderDetailModel{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}