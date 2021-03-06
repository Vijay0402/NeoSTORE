package neosoft.training.neostore.common.base.productmodel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by webwerks1 on 2/11/17.
 */



    public class ProductDetailModel {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("data")
        @Expose
        private ProductDetailData data;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public ProductDetailData getData() {
            return data;
        }

        public void setData(ProductDetailData data) {
            this.data = data;
        }

    }

