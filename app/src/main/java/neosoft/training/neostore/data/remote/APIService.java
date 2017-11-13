package neosoft.training.neostore.data.remote;

import neosoft.training.neostore.model.EnterQuantityModel;
import neosoft.training.neostore.model.ForgetModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by webwerks on 9/11/17.
 */

public interface APIService {

    @POST("users/forgot")
    @FormUrlEncoded
    Call<ForgetModel> savePost(@Field("email") String email);

    @POST("addToCart")
    @FormUrlEncoded
    Call<EnterQuantityModel> cartPost(@Header("access_token") String access,
                                      @Field("product_id") String productId,
                                      @Field("quantity") String quantity);


}
