package neosoft.training.neostore.view.address.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;

public class AddAddressActivity extends BaseActivity {
   Toolbar toolbarAdd;
   TextView toolbarTitle;

    @Override
    public int getContentView() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initView() {
        toolbarAdd=findViewById(R.id.toolbar);
        toolbarTitle=toolbarAdd.findViewById(R.id.toolbartxtViewTitle);
    }

    @Override
    public void setListeners() {

    }


    @Override
    public void setActionBar() {
        setSupportActionBar(toolbarAdd);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
        toolbarTitle.setText(R.string.save_address);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //inflate items to action bar if present
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

}
