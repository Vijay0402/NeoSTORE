package neosoft.training.neostore.view.Product;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;


import neosoft.training.neostore.R;
import neosoft.training.neostore.view.home.HomeBannerSliderAdapter;

public class ProductDetailedActivity extends AppCompatActivity {
   ViewPager viewPager;
   Toolbar mToolbar;
   TextView mTxtToolbar;

   RecyclerView mRecyclerView;
   ProductListingAdapter mCustomRecyclerAdapter;

  // Integer[] imgArray={R.drawable.slider_img1,R.drawable.slider_img2,R.drawable.slider_img3,R.drawable.slider_img4};
 //  List<String> imgList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);
        initView();
        init();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
        String str=getIntent().getStringExtra("ItemTitle");
        mTxtToolbar.setText(str);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mCustomRecyclerAdapter=new ProductListingAdapter(this);
        mRecyclerView.setAdapter(mCustomRecyclerAdapter);


    }

    public void initView() {
        viewPager=findViewById(R.id.view_pager_prod_detailed);
        mToolbar=findViewById(R.id.toolbar);
        mTxtToolbar=mToolbar.findViewById(R.id.toolbartxtViewTitle);
        mRecyclerView=findViewById(R.id.recyclerViewProduct);

    }

    public void init() {

        viewPager.setAdapter(new HomeBannerSliderAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // display search icon in toolbar
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
}
