package neosoft.training.neostore.view.order.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.order.adapter.MyOrderAdapter;

public class MyOrderActivity extends BaseActivity {
    Toolbar toolbarTitle;
    TextView txtToolbar;
    RecyclerView recyclerView;
    MyOrderAdapter myOrderAdapter;

    @Override
    public int getContentView() {
        return R.layout.activity_my_order;
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        toolbarTitle = findViewById(R.id.toolbar);
        txtToolbar = toolbarTitle.findViewById(R.id.toolbartxtViewTitle);
        init();
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);

        myOrderAdapter = new MyOrderAdapter(this);
        recyclerView.setAdapter(myOrderAdapter);

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