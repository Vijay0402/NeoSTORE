package neosoft.training.neostore.view.Product.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.ItemClickSupport;
import neosoft.training.neostore.common.Utils;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.common.base.BaseAsyncTask;
import neosoft.training.neostore.common.base.productmodel.ProductDetailModel;
import neosoft.training.neostore.common.base.productmodel.ProductDetailData;
import neosoft.training.neostore.common.base.productmodel.ProductDetailImage;

import neosoft.training.neostore.common.base.ratingmodel.BaseRatingModel;
import neosoft.training.neostore.common.base.ratingmodel.RatingData;
import neosoft.training.neostore.view.Product.adapter.ProductDetailedAdapter;
import neosoft.training.neostore.view.Product.fragment.EnterQuantityFragment;

public class ProductDetailedActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, BaseAsyncTask.onAsyncRequestComplete, RatingBar.OnRatingBarChangeListener {

    Toolbar mToolbar;
    TextView mTxtToolbar;
    RecyclerView mRecyclerView;
    ProductDetailedAdapter mCustomRecyclerAdapter;
    ImageView imgShare, imgProduct;
    private Button btnBuynow, btnRatenow;
    TextView txtProductName, txtProductCategory, txtProductDesc, txtPrice, txtSummary;
    RatingBar ratingBar;
    Map<String, Object> mapData = new HashMap<>();
    Map<String, Object> mapData1 = new HashMap<>();
    String str;
    String productId;
    String imgURl;
    String firstRecyclerImage;
    ProductDetailModel productDetailModel;
    String url = "http://staging.php-dev.in:8844/trainingapp/api/products/getDetail";
    String urlRating = "http://staging.php-dev.in:8844/trainingapp/api/products/setRating";
    int oldPos = 0, newPos = 0;


    boolean selected;
    List<ProductDetailImage> data;

    @Override
    public int getContentView() {
        return R.layout.activity_product_detailed;
    }

    public void initView() {

//        viewPager=findViewById(R.id.view_pager_prod_detailed);
        mToolbar = findViewById(R.id.toolbar);
        mTxtToolbar = mToolbar.findViewById(R.id.toolbartxtViewTitle);
        mRecyclerView = findViewById(R.id.recyclerView);
        imgShare = findViewById(R.id.imgShare);
        btnBuynow = findViewById(R.id.btnBuynow);
        btnRatenow = findViewById(R.id.ratenow);

        ratingBar = findViewById(R.id.product_rating);
        imgProduct = findViewById(R.id.imgZoomProductDetail);

        txtProductName = findViewById(R.id.txtProductDetailName);
        txtProductCategory = findViewById(R.id.txtProductDetailCategory);
        txtProductDesc = findViewById(R.id.txtProductDetailDescription);
        txtPrice = findViewById(R.id.prd_detail_price);
        txtSummary = findViewById(R.id.txtDescriptionText);




        /*ItemClickSupport.addTo(mRecyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        imgURl = setImage(position);

                        Glide.with(ProductDetailedActivity.this).load(imgURl).into(imgProduct);
                    }
                });*/
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        try {
                            mCustomRecyclerAdapter.setSelected(position);
                            imgURl = setImage(position);
                            Glide.with(ProductDetailedActivity.this).load(imgURl).into(imgProduct);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
        );

        productId = getIntent().getStringExtra("product_id");
        mapData.put("product_id", productId);

        Log.e("ProductDetailedActivity", "initView: " + mapData);
        BaseAsyncTask baseAsyncTask = new BaseAsyncTask(this, "GET", mapData);
        baseAsyncTask.execute(url);

    }

    @Override
    public void setListeners() {
        imgShare.setOnClickListener(this);
        btnBuynow.setOnClickListener(this);
        btnRatenow.setOnClickListener(this);

    }

    @Override
    public void setActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);

        str = getIntent().getStringExtra("ItemTitle");
        mTxtToolbar.setText(str);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // display search icon in toolbar
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mRecyclerView.smoothScrollToPosition(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imgShare:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String title = getResources().getString(R.string.chooser_title);
                Intent chooser = Intent.createChooser(intent, title);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
                break;
            case R.id.btnBuynow:
                EnterQuantityFragment enterQuantityFragment = new EnterQuantityFragment();
                Bundle bundle = new Bundle();
                bundle.putString("productId", productId);
                bundle.putString("ItemName", str);
                bundle.putString("Image", imgURl);
                bundle.putString("SetFirstImage", firstRecyclerImage);
                enterQuantityFragment.setArguments(bundle);
                enterQuantityFragment.show(getSupportFragmentManager(), "Quantity Dialog");

                break;
            case R.id.ratenow:
                dialogShow();

                break;

        }
    }

    private void dialogShow() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.rating_popup);

        TextView txtProduct = dialog.findViewById(R.id.txtItemName);
        txtProduct.setText(str);

        ImageView imgProduct = dialog.findViewById(R.id.imgItem);
        Glide.with(this).load(imgURl).into(imgProduct);

        dialog.getWindow().setLayout(1275, 1800);

        RatingBar ratingProduct = dialog.findViewById(R.id.ratingBarPopup);
        ratingProduct.setOnRatingBarChangeListener(ProductDetailedActivity.this);

        Button btnRate = dialog.findViewById(R.id.btnSubmitRate);
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailedActivity.this, "Thank for your response", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        dialog.show();

    }

    @Override
    public void asyncResponse(Object response) {

        try {
            Gson gson = new Gson();
            productDetailModel = gson.fromJson(response.toString(), ProductDetailModel.class);

            ProductDetailData productDetailData = productDetailModel.getData();

            Utils utils = new Utils();

            txtProductName.setText(productDetailData.getName());
            txtProductCategory.setText(utils.categoryFromId(productDetailData.getProductCategoryId()));
            txtProductDesc.setText(productDetailData.getProducer());
            txtPrice.setText("Rs." + productDetailData.getCost());
            txtSummary.setText(productDetailData.getDescription());
            ratingBar.setRating(productDetailData.getRating());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);


            mCustomRecyclerAdapter = new ProductDetailedAdapter(this, productDetailModel.getData().getProductImages(),imgProduct);

            firstRecyclerImage = setImage(0);
            Glide.with(ProductDetailedActivity.this).load(firstRecyclerImage).into(imgProduct);//data is in json response so it set automatically
            mRecyclerView.setAdapter(mCustomRecyclerAdapter);

            //Rating Pop up Dialog
            Gson gson1 = new Gson();
            BaseRatingModel baseRatingModel = new BaseRatingModel();
            baseRatingModel = gson1.fromJson(response.toString(), BaseRatingModel.class);

            RatingData ratingData = baseRatingModel.getData();
            ratingBar.setRating(ratingData.getRating());

            mCustomRecyclerAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void onFailure(Object response) {
        Toast.makeText(this, "ForgetModel Missing", Toast.LENGTH_SHORT).show();

    }

    public String setImage(int position) {

        ProductDetailImage productDetailImage;
        productDetailImage = productDetailModel.getData().getProductImages().get(position);
        String image = productDetailImage.getImage();
        return image;

    }

    @Override
    public void onRatingChanged(RatingBar ratingProduct, float rated, boolean b) {

        mapData1.put("rating", ratingProduct.getRating());
        mapData1.put("product_id", productId);

        BaseAsyncTask baseAsyncTask = new BaseAsyncTask(ProductDetailedActivity.this, "POST", mapData1);
        baseAsyncTask.execute(urlRating);

    }
}