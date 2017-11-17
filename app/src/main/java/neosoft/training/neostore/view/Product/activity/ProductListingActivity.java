package neosoft.training.neostore.view.Product.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.ItemClickSupport;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.common.base.BaseAsyncTask;
import neosoft.training.neostore.common.base.BaseProductListing;
import neosoft.training.neostore.common.base.ProductListingData;
import neosoft.training.neostore.view.Product.adapter.ProductListingAdapter;

public class ProductListingActivity extends BaseActivity implements BaseAsyncTask.onAsyncRequestComplete {
  private Toolbar toolbarPL;
  private TextView productToolbarTitle;
  private RecyclerView mRecyclerView;
  private ProductListingAdapter mCustomRecyclerAdapter;
  private LinearLayoutManager mLayoutManager;
  private Map<String, Object > mapData=new HashMap<>();
  private Context context=this;
  private String productId;
  private static final String TAG = ProductListingActivity.class.getSimpleName();
  private String url="http://staging.php-dev.in:8844/trainingapp/api/products/getList";
  BaseProductListing sampleModel;

    @Override
    public int getContentView() {
        return R.layout.activity_product_listing;
    }

    @Override
    public void initView() {
        mRecyclerView=findViewById(R.id.recyclerViewProduct);
        toolbarPL=findViewById(R.id.toolbar);
        productToolbarTitle=toolbarPL.findViewById(R.id.toolbartxtViewTitle);

        Intent intent=getIntent();
        productId=intent.getStringExtra("product_category_id");
        mapData.put("product_category_id",productId);
        Log.e(TAG, "initView: "+mapData );

        BaseAsyncTask baseAsyncTask=new BaseAsyncTask(this,"GET",mapData);
        baseAsyncTask.execute(url);

//        ItemClickSupport.addTo(mRecyclerView)
//                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//                    @Override
//                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                        ProductListingData productListingData = sampleModel.getData().get(position);
//                        Toast.makeText(ProductListingActivity.this, (position + 1 + " OF " + sampleModel.getData().size()), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(context, ProductDetailedActivity.class);
//                        intent.putExtra("ItemTitle", productListingData.getName());
//                        intent.putExtra("product_category_id",productListingData.getProductCategoryId().toString());
//                        intent.putExtra("product_id",productListingData.getId().toString());
//                        context.startActivity(intent);
//                    }
//                });
     }

    @Override
    public void setListeners() {

    }
    @Override
    public void setActionBar() {

        setSupportActionBar(toolbarPL);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);

        String str=getIntent().getStringExtra("Title");
        productToolbarTitle.setText(str);
        //productToolbarTitle.setText(R.string.toolar_product_header);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //inflate items i.e search icon in toolbar
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;

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
    public void asyncResponse(Object response) {

        Gson gson = new GsonBuilder().serializeNulls().create();
        sampleModel = gson.fromJson(response.toString(), BaseProductListing.class);
        Log.e("ProductListActivity", "asyncResponse: "+sampleModel.getData());

                mCustomRecyclerAdapter = new ProductListingAdapter(this,sampleModel.getData(),productId);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(mLayoutManager);

                DividerItemDecoration mDividerItemDecoration =
                        new DividerItemDecoration(this,mLayoutManager.getOrientation());
                mRecyclerView.addItemDecoration(mDividerItemDecoration);

                mRecyclerView.setAdapter(mCustomRecyclerAdapter);
                mCustomRecyclerAdapter.notifyDataSetChanged();

            }



    @Override
    public void onFailure(Object response) {
        Toast.makeText(context, "ForgetModel fetching error ", Toast.LENGTH_SHORT).show();
    }
}















/* List<ProductListModel> data = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject((String) response);
            int statusid = jsonObject.optInt("status");
            if(statusid==200){
                JSONArray jsonArray = jsonObject.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject dataObject = jsonArray.getJSONObject(i);
                    ProductListModel productListModel = new ProductListModel();
                    productListModel.setId(dataObject.optInt("id"));
                    productListModel.setProduct_category_id(dataObject.optInt("product_category_id"));
                    productListModel.setName(dataObject.optString("name"));
                    productListModel.setProducer(dataObject.optString("producer"));
                    productListModel.setDescription(dataObject.optString("description"));
                    productListModel.setCost(dataObject.optInt("cost"));
                    productListModel.setRating(dataObject.optInt("rating"));
                    productListModel.setView_count(dataObject.optInt("view_count"));
                    productListModel.setCreated(dataObject.optInt("created"));
                    productListModel.setModified(dataObject.optInt("modified"));
                    productListModel.setProduct_images(dataObject.optString("product_images"));

                    data.add(productListModel);

                    Log.e("json", "" + statusid);
                }
*/














