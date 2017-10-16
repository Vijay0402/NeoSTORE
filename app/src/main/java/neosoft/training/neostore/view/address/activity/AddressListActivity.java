package neosoft.training.neostore.view.address.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.address.adapter.AddressListAdapter;

public class AddressListActivity extends BaseActivity {

   private Toolbar toolbar;
   private TextView txtToolbarTitle;
   RecyclerView recyclerView;
   AddressListAdapter addressListAdapter;

    @Override
    public int getContentView() {
        return R.layout.activity_address_list;

    }

    @Override
    public void initView() {
     toolbar=findViewById(R.id.toolbar);
     txtToolbarTitle=toolbar.findViewById(R.id.toolbartxtViewTitle);
     recyclerView=findViewById(R.id.recyclerView);
     init();
    }

    private void init() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        addressListAdapter=new AddressListAdapter(this);
        recyclerView.setAdapter(addressListAdapter);

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
       txtToolbarTitle.setText(R.string.address_list);


    }
}
