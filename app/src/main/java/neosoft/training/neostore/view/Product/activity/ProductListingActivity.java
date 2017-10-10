package neosoft.training.neostore.view.Product.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;
import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.Product.ProductListingAdapter;

public class ProductListingActivity extends BaseActivity {
  private Toolbar toolbarPL;
  private TextView productToolbarTitle;
  private RecyclerView mRecyclerView;
  private ProductListingAdapter mCustomRecyclerAdapter;
  private List<String > list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("Stylish Modern dining Tables");
        list.add("4 Seater Dining Table");
        list.add("6 Seater Dining Table");
        list.add("Stylish 4 Seater Dining Tables");
        list.add("Stylish Table");
        list.add("Harkness Table For Office");


        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        //add divider in recycler view
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        mCustomRecyclerAdapter=new ProductListingAdapter(this);
        mRecyclerView.setAdapter(mCustomRecyclerAdapter);

    }

    @Override
    public int getContentView() {
        return R.layout.activity_product_listing;
    }

    @Override
    public void initView() {
        mRecyclerView=findViewById(R.id.recyclerViewProduct);
        init();
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

    private void init() {
        toolbarPL=findViewById(R.id.toolbar);
        productToolbarTitle=toolbarPL.findViewById(R.id.toolbartxtViewTitle);

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
            case android.R.id.home:onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
