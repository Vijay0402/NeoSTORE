package neosoft.training.neostore.view.mycart.activity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.data.remote.APIService;
import neosoft.training.neostore.data.remote.ApiUtils;
import neosoft.training.neostore.model.DeleteCartModel;
import neosoft.training.neostore.model.MyCartData;
import neosoft.training.neostore.model.MyCartModel;
import neosoft.training.neostore.view.address.activity.AddAddressActivity;
import neosoft.training.neostore.view.mycart.adapter.MyCartAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCartActivity extends BaseActivity implements MyCartItemTouchHelper.MyCartItemTouchHelperListener, View.OnClickListener {
    private RecyclerView rv;
    private MyCartAdapter myCartAdapter;
    private Toolbar toolbar;
    private TextView txtTitleToolbar;
    APIService apiService;
    MyCartModel myCartModel;
    String userToken;
    TextView productCartTotal;
    List<MyCartData> listData = new ArrayList<>();
    Button btnOrderNow;
    int totalCartProduct;

    private static final String TAG = MyCartActivity.class.getSimpleName();

    DeleteCartModel deleteCartModel;

    @Override
    public int getContentView() {
        return R.layout.activity_my_cart;
    }

    @Override
    public void initView() {
        rv = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);
        txtTitleToolbar = toolbar.findViewById(R.id.toolbartxtViewTitle);
        productCartTotal = findViewById(R.id.cartTotalPrice);
        btnOrderNow = findViewById(R.id.btnOrderNow);

        btnOrderNow.setOnClickListener(this);

        apiService = ApiUtils.getAPIService();

        SharedPreferences sharedPreferences = this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        userToken = sharedPreferences.getString("AccessToken", "");

        displayUserItemInCart(userToken);

    }

    public void deleteItemFromCart(String userToken, String productId) {
        apiService.postDeleteItem(userToken, productId).enqueue(new Callback<DeleteCartModel>() {
            @Override
            public void onResponse(Call<DeleteCartModel> call, Response<DeleteCartModel> response) {
                deleteCartModel = response.body();
                if (deleteCartModel != null) {
                    productLeftInCart(deleteCartModel);
                } else
                    Toast.makeText(MyCartActivity.this, "Error null", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DeleteCartModel> call, Throwable t) {
                Toast.makeText(MyCartActivity.this, "Error in response ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void productLeftInCart(DeleteCartModel deleteCartModel) {
        totalCartProduct = deleteCartModel.getTotalCarts();

        if (totalCartProduct > 0)
            Log.e(TAG, "productLeftInCart: " + totalCartProduct);
        else
            Toast.makeText(this, "Nothing left in Cart", Toast.LENGTH_SHORT).show();
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
        txtTitleToolbar.setText(R.string.mycart);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //inflate items i.e search icon in toolbar
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public void displayUserItemInCart(String userToken) {
        apiService.getItemListCart(userToken).enqueue(new Callback<MyCartModel>() {
            @Override
            public void onResponse(Call<MyCartModel> call, Response<MyCartModel> response) {
                myCartModel = response.body();
                cartData(myCartModel);
            }

            @Override
            public void onFailure(Call<MyCartModel> call, Throwable t) {
                Toast.makeText(MyCartActivity.this, "Your request not submitted to Api", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cartData(MyCartModel myCartModel) {

        if (myCartModel.getTotal() != null)
            productCartTotal.setText("Rs." + myCartModel.getTotal());
        else
            productCartTotal.setText("Rs. " + 0);

        listData = myCartModel.getData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                linearLayoutManager.getOrientation());
        rv.addItemDecoration(mDividerItemDecoration);

        myCartAdapter = new MyCartAdapter(this, listData);
        rv.setAdapter(myCartAdapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new MyCartItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rv);

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        // remove the item from recycler view
        myCartAdapter.onItemRemove(viewHolder.getAdapterPosition());

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AddAddressActivity.class);
        startActivity(intent);

    }
}