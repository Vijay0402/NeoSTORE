package neosoft.training.neostore.common.base.productmodel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import neosoft.training.neostore.common.base.productmodel.ProductDetailDataModel;

/**
 * Created by webwerks1 on 2/11/17.
 */



    public class BaseProductDetail {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("data")
        @Expose
        private ProductDetailDataModel data;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public ProductDetailDataModel getData() {
            return data;
        }

        public void setData(ProductDetailDataModel data) {
            this.data = data;
        }

    }

