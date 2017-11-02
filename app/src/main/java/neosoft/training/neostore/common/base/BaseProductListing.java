package neosoft.training.neostore.common.base;

/**
 * Created by webwerks1 on 1/11/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseProductListing {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<ProductListingData> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProductListingData> getData() {
        return data;
    }

    public void setData(List<ProductListingData> data) {
        this.data = data;
    }

}
