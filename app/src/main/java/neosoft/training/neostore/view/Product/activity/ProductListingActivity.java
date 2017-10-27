package neosoft.training.neostore.view.Product.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.Product.adapter.ProductListingAdapter;

public class ProductListingActivity extends BaseActivity {
  private Toolbar toolbarPL;
  private TextView productToolbarTitle;
  private RecyclerView mRecyclerView;
  private ProductListingAdapter mCustomRecyclerAdapter;

  private Context context=this;
  private String url="http://staging.php-dev.in:8844/trainingapp/api/products/getList";

    @Override
    public int getContentView() {
        return R.layout.activity_product_listing;
    }

    @Override
    public void initView() {
        mRecyclerView=findViewById(R.id.recyclerViewProduct);
        toolbarPL=findViewById(R.id.toolbar);
        productToolbarTitle=toolbarPL.findViewById(R.id.toolbartxtViewTitle);

        Map<String, Object > data=new HashMap<>();
        Intent intent=getIntent();
        String str=intent.getStringExtra("product_category_id");
        data.put("product_category_id",str);
        ProductListAsyncTask productListAsyncTask=new ProductListAsyncTask(data,context);
        productListAsyncTask.execute(url);

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
        productToolbarTitle.setText(str);  //productToolbarTitle.setText(R.string.toolar_product_header);

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


}
