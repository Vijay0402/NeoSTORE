package neosoft.training.neostore.view.Product.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import neosoft.training.neostore.common.base.BaseProductDetail;
import neosoft.training.neostore.common.base.ProductDetailDataModel;
import neosoft.training.neostore.common.base.ProductDetailImage;

import neosoft.training.neostore.view.Product.adapter.ProductDetailedAdapter;
import neosoft.training.neostore.view.home.adapter.HomeBannerSliderAdapter;
import neosoft.training.neostore.view.Product.fragment.EnterQuantityFragment;
import neosoft.training.neostore.view.Product.fragment.RatingPopupFragment;

public class ProductDetailedActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, BaseAsyncTask.onAsyncRequestComplete {
    
    Toolbar mToolbar;
    TextView mTxtToolbar;
    RecyclerView mRecyclerView;
    ProductDetailedAdapter mCustomRecyclerAdapter;
    ImageView imgShare, imgProduct;
    private Button btnBuynow, btnRatenow;
    TextView txtProductName, txtProductCategory, txtProductDesc, txtPrice, txtSummary;
    RatingBar ratingBar;
    Map<String, Object> mapData = new HashMap<>();
    String str;
    BaseProductDetail baseProductDetail;
    String url = "http://staging.php-dev.in:8844/trainingapp/api/products/getDetail";

    // Integer[] imgArray={R.drawable.slider_img1,R.drawable.slider_img2,R.drawable.slider_img3,R.drawable.slider_img4};
    //  List<String> imgList=new ArrayList<>();
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
        
        ItemClickSupport.addTo(mRecyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                       Glide.with(ProductDetailedActivity.this).load(setImage(position)).into(imgProduct);
                    }
                });


        String productCategory = getIntent().getStringExtra("product_category");

        String productId = getIntent().getStringExtra("product_Id");

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
                enterQuantityFragment.show(getSupportFragmentManager(), "Quantity Dialog");
                break;
            case R.id.ratenow:
                RatingPopupFragment ratingPopupFragment = new RatingPopupFragment();
                ratingPopupFragment.show(getSupportFragmentManager(), "Rating Dialog");
                break;

        }
    }

    @Override
    public void asyncResponse(Object response) {

        try {
            Gson gson = new Gson();
            baseProductDetail = gson.fromJson(response.toString(), BaseProductDetail.class);

            ProductDetailDataModel productDetailDataModel = baseProductDetail.getData();
            Utils utils = new Utils();

            txtProductName.setText(productDetailDataModel.getName());
            txtProductCategory.setText(utils.categoryFromId(productDetailDataModel.getProductCategoryId()));
            txtProductDesc.setText(productDetailDataModel.getProducer());
            txtPrice.setText("Rs." + productDetailDataModel.getCost());
            txtSummary.setText(productDetailDataModel.getDescription());
            ratingBar.setRating(productDetailDataModel.getRating());


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);

            mCustomRecyclerAdapter = new ProductDetailedAdapter(this, baseProductDetail.getData().getProductImages());

            mRecyclerView.setAdapter(mCustomRecyclerAdapter);
            mCustomRecyclerAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void onFailure(Object response) {
        Toast.makeText(this, "Data Missing", Toast.LENGTH_SHORT).show();

    }

    public String setImage(int position) {
        ProductDetailImage productDetailImage;
        productDetailImage = baseProductDetail.getData().getProductImages().get(position);
        String image = productDetailImage.getImage();
        return image;

    }

}