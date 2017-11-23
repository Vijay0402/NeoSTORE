package neosoft.training.neostore.view.address.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.data.remote.APIService;
import neosoft.training.neostore.data.remote.ApiUtils;
import neosoft.training.neostore.model.AddAddressModel;
import neosoft.training.neostore.view.address.adapter.AddressListAdapter;
import neosoft.training.neostore.view.order.activity.MyOrderActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressListActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView txtToolbarTitle;
    RecyclerView recyclerView;
    Button btnPlaceOrder;
    String userToken, userAddress;
    AddressListAdapter addressListAdapter;
    APIService apiService;
    AddAddressModel addAddressModel;

    ArrayList<AddAddressData> arrayListAddress=new ArrayList<>();

    private static final String TAG = AddressListActivity.class.getSimpleName();

    @Override
    public int getContentView() {
        return R.layout.activity_address_list;

    }

    @Override
    public void initView() {
        toolbar = findViewById(R.id.toolbar);
        txtToolbarTitle = toolbar.findViewById(R.id.toolbartxtViewTitle);
        recyclerView = findViewById(R.id.recyclerView);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        apiService= ApiUtils.getAPIService();

        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", 0);
        userToken = sharedPreferences.getString("AccessToken", "");

        userAddress = getIntent().getStringExtra("SavedAddress");

        AddAddressData addAddressData=new AddAddressData();
        addAddressData.setUserAddress(userAddress);

        arrayListAddress.add(addAddressData);

        init();

    }

    private void init() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        addressListAdapter = new AddressListAdapter(this,arrayListAddress);

        recyclerView.setAdapter(addressListAdapter);
//        addressListAdapter.notifyData(arrayListAddress);
    }

    @Override
    public void setListeners() {
        btnPlaceOrder.setOnClickListener(this);
    }

    @Override
    public void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
        txtToolbarTitle.setText(R.string.address_list);

    }

    public void addUserAddress(String userToken, String userAddress) {
        apiService.addAddress(userToken, userAddress).enqueue(new Callback<AddAddressModel>() {
            @Override
            public void onResponse(Call<AddAddressModel> call, Response<AddAddressModel> response) {
                addAddressModel = response.body();
                Log.e(TAG,"onResponse: " + response.body());

            }

            @Override
            public void onFailure(Call<AddAddressModel> call, Throwable t) {
                Toast.makeText(AddressListActivity.this, "Error in Api", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnPlaceOrder:
                addUserAddress(userToken, userAddress);
                Intent intent = new Intent(this, MyOrderActivity.class);
                intent.putExtra("SavedAddress", userAddress);
                startActivity(intent);
        }
    }
}
