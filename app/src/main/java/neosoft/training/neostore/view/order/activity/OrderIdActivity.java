package neosoft.training.neostore.view.order.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.order.adapter.OrderIdAdapter;

public class OrderIdActivity extends BaseActivity {
   private Toolbar toolbar;
   TextView txtToolbar;
    private RecyclerView recyclerView;
   private OrderIdAdapter orderIdAdapter;

    @Override
    public int getContentView() {
        return R.layout.activity_order_id;
    }

    @Override
    public void initView() {
        recyclerView=findViewById(R.id.recyclerView);
        toolbar=findViewById(R.id.toolbar);
        txtToolbar=toolbar.findViewById(R.id.toolbartxtViewTitle);
        init();
    }

    private void init() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);


        orderIdAdapter=new OrderIdAdapter(this);
        recyclerView.setAdapter(orderIdAdapter);

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

     txtToolbar.setText("ORDER ID: ");

    }
}
