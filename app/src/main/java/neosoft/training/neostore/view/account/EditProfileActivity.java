package neosoft.training.neostore.view.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;

public class EditProfileActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView txtToolbarTitle;

    @Override
    public int getContentView() {
        return R.layout.activity_edit_profile;
    }

    @Override
    public void initView() {
     toolbar=findViewById(R.id.toolbar);
     txtToolbarTitle=toolbar.findViewById(R.id.toolbartxtViewTitle);
     txtToolbarTitle.setText(R.string.edit_profile);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void setActionBar() {
     setSupportActionBar(toolbar);
     getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.action_search:
               return true;
           case android.R.id.home:
               onBackPressed();
               return true;
       }
          return super.onOptionsItemSelected(item);
    }
}
