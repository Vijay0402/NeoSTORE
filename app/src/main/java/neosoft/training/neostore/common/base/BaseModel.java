package neosoft.training.neostore.common.base;

import com.google.gson.annotations.SerializedName;

import neosoft.training.neostore.model.ProductListModel;
import neosoft.training.neostore.model.RegistrationModel;

/**
 * Created by webwerks1 on 31/10/17.
 */

public class BaseModel {

    @SerializedName("status")
    private int status;

    @SerializedName("data")
    public RegistrationModel data;

    @SerializedName("dataa")
    public ProductListModel dataa;

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status;
    }
}
