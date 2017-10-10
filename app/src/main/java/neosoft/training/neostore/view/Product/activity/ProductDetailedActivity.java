package neosoft.training.neostore.view.Product.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.Product.ProductDetailedAdapter;
import neosoft.training.neostore.view.home.adapter.HomeBannerSliderAdapter;

public class ProductDetailedActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
   ViewPager viewPager;
   Toolbar mToolbar;
   TextView mTxtToolbar;
   RecyclerView mRecyclerView;
   ProductDetailedAdapter mCustomRecyclerAdapter;
   ImageView imgShare;

  // Integer[] imgArray={R.drawable.slider_img1,R.drawable.slider_img2,R.drawable.slider_img3,R.drawable.slider_img4};
 //  List<String> imgList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAdapter();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mCustomRecyclerAdapter=new ProductDetailedAdapter(this);
        mRecyclerView.setAdapter(mCustomRecyclerAdapter);



    }

    @Override
    public int getContentView() {
        return R.layout.activity_product_detailed;
    }

    public void initView() {
        viewPager=findViewById(R.id.view_pager_prod_detailed);
        mToolbar=findViewById(R.id.toolbar);
        mTxtToolbar=mToolbar.findViewById(R.id.toolbartxtViewTitle);
        mRecyclerView=findViewById(R.id.recyclerViewDetail);
        imgShare=findViewById(R.id.imgShare);

    }

    @Override
    public void setListeners() {
       imgShare.setOnClickListener(this);
       viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void setActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
        String str=getIntent().getStringExtra("ItemTitle");
        mTxtToolbar.setText(str);
    }

    public void setAdapter() {
        viewPager.setAdapter(new HomeBannerSliderAdapter(this));


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // display search icon in toolbar
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
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
            case android.R.id.home:onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgShare:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String title=getResources().getString(R.string.chooser_title);
                Intent chooser= Intent.createChooser(intent,title);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
                break;

        }
    }
}
