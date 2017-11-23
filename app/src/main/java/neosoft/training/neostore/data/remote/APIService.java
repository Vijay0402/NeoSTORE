package neosoft.training.neostore.data.remote;

import neosoft.training.neostore.model.AddAddressModel;
import neosoft.training.neostore.model.DeleteCartModel;
import neosoft.training.neostore.model.EnterQuantityModel;
import neosoft.training.neostore.model.ForgetModel;
import neosoft.training.neostore.model.MyCartModel;
import neosoft.training.neostore.model.OrderDetailModel;
import neosoft.training.neostore.model.OrderListModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

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


    @GET("cart")
    Call<MyCartModel> getItemListCart(@Header("access_token") String accessToken);

    @POST("deleteCart")
    @FormUrlEncoded
    Call<DeleteCartModel> postDeleteItem(@Header("access_token") String accessToken,
                                         @Field("product_id") String productId);

    @POST("order")
    @FormUrlEncoded
    Call<AddAddressModel> addAddress(@Header("access_token") String accessToken,
                                     @Field("address") String address);

    @GET("orderList")
    Call<OrderListModel> orderList(@Header("access_token") String accessToken);

    @GET("orderDetail")
    Call<OrderDetailModel> orderDetail(@Header("access_token") String accessToken,
                                       @Query("order_id") String order_id);


}
