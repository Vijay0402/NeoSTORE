package neosoft.training.neostore.view.mycart.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.widget.TextView;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.mycart.adapter.MyCartAdapter;

public class MyCartActivity extends BaseActivity {
    private RecyclerView rv;
    private MyCartAdapter myCartAdapter;
    private Toolbar toolbar;
    private TextView txtTitleToolbar;

    @Override
    public int getContentView() {
        return R.layout.activity_my_cart;
    }

    @Override
    public void initView() {
        rv=findViewById(R.id.recyclerView);
        toolbar=findViewById(R.id.toolbar);
        txtTitleToolbar=toolbar.findViewById(R.id.toolbartxtViewTitle);
        init();
        initSwipe();
    }

    private void initSwipe() {


    }

    private void init() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                linearLayoutManager.getOrientation());
        rv.addItemDecoration(mDividerItemDecoration);

        myCartAdapter=new MyCartAdapter(this);
        rv.setAdapter(myCartAdapter);

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
    txtTitleToolbar.setText(R.string.mycart);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //inflate items i.e search icon in toolbar
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
}
