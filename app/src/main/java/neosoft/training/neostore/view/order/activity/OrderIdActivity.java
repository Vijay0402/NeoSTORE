package neosoft.training.neostore.view.order.activity;

import android.content.SharedPreferences;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.data.remote.APIService;
import neosoft.training.neostore.data.remote.ApiUtils;
import neosoft.training.neostore.model.OrderDetail;
import neosoft.training.neostore.model.OrderDetailModel;
import neosoft.training.neostore.view.order.adapter.OrderIdAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderIdActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView txtToolbar, total;
    private RecyclerView recyclerView;
    private OrderIdAdapter orderIdAdapter;
    private String orderId,userToken;
    OrderDetailModel orderDetailModel;
    List<OrderDetail> listOrderDetail;
    private static final String TAG = OrderIdActivity.class.getSimpleName();

    APIService apiService;


    @Override
    public int getContentView() {
        return R.layout.activity_order_id;
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);
        txtToolbar = toolbar.findViewById(R.id.toolbartxtViewTitle);
        total=findViewById(R.id.totalPrice);
        apiService= ApiUtils.getAPIService();

        orderId = getIntent().getStringExtra("OrderID");

        SharedPreferences sharedPreferences=this.getSharedPreferences("Login",0);
        userToken=sharedPreferences.getString("AccessToken","");

        orderIdDetail(userToken,orderId);
    }

    public void orderIdDetail(String userToken, String orderId) {
       apiService.orderDetail(userToken,orderId).enqueue(new Callback<OrderDetailModel>() {
           @Override
           public void onResponse(Call<OrderDetailModel> call, Response<OrderDetailModel> response) {
               orderDetailModel=response.body();
               Log.e(TAG, "onResponse: "+orderDetailModel.getData().toString());
               listOrderDetail=orderDetailModel.getData().getOrderDetails();
               orderDetail(listOrderDetail);
               total.setText("Rs. "+orderDetailModel.getData().getCost());

           }

           @Override
           public void onFailure(Call<OrderDetailModel> call, Throwable t) {
               Toast.makeText(OrderIdActivity.this, "Request Not submitted to Api", Toast.LENGTH_SHORT).show();
           }
       });

    }

    private void orderDetail(List<OrderDetail> listOrderDetail) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);

        orderIdAdapter = new OrderIdAdapter(this,listOrderDetail);
        recyclerView.setAdapter(orderIdAdapter);

    }


    @Override
    public void setListeners() {

    }

    @Override
    public void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);

        txtToolbar.setText("ORDER ID: " + orderId);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id=item.getItemId();

         switch (id){
             case android.R.id.home:
                 onBackPressed();
                 return true;
         }
        return super.onOptionsItemSelected(item);
    }
}
