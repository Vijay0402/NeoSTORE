package neosoft.training.neostore.view.order.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.ItemClickSupport;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.data.remote.APIService;
import neosoft.training.neostore.data.remote.ApiUtils;
import neosoft.training.neostore.model.OrderListData;
import neosoft.training.neostore.model.OrderListModel;
import neosoft.training.neostore.view.order.adapter.MyOrderAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderActivity extends BaseActivity {
    Toolbar toolbarTitle;
    TextView txtToolbar;
    RecyclerView recyclerView;
    MyOrderAdapter myOrderAdapter;
    APIService apiService;
    String userToken;
    OrderListModel orderListModel;
    List<OrderListData> listOrderData;

    private static final String TAG = MyOrderActivity.class.getSimpleName();

    @Override
    public int getContentView() {
        return R.layout.activity_my_order;
    }

    @Override
    public void initView() {

        recyclerView = findViewById(R.id.recyclerView);
        toolbarTitle = findViewById(R.id.toolbar);
        txtToolbar = toolbarTitle.findViewById(R.id.toolbartxtViewTitle);

        apiService = ApiUtils.getAPIService();

        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", 0);
        userToken = sharedPreferences.getString("AccessToken", "");

        orderList(userToken);


    }

    private void orderList(String userToken) {

        apiService.orderList(userToken).enqueue(new Callback<OrderListModel>() {
            @Override
            public void onResponse(Call<OrderListModel> call, Response<OrderListModel> response) {
                orderListModel = response.body();
                userOrderList(orderListModel);
                Log.e(TAG, "onResponse: " + orderListModel.toString());
            }

            @Override
            public void onFailure(Call<OrderListModel> call, Throwable t) {
                Toast.makeText(MyOrderActivity.this, "Error in Api", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void userOrderList(OrderListModel orderListModel) {

        listOrderData = orderListModel.getData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);

        myOrderAdapter = new MyOrderAdapter(this, listOrderData);
        recyclerView.setAdapter(myOrderAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                String orderId = listOrderData.get(position).getId().toString();

                Intent intent = new Intent(MyOrderActivity.this, OrderIdActivity.class);
                intent.putExtra("OrderID", orderId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void setActionBar() {
        setSupportActionBar(toolbarTitle);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtToolbar.setText(R.string.my_orders);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}