package neosoft.training.neostore.view.login.activity;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;

public class ResetPasswordActivity extends BaseActivity {
   Toolbar toolbar;
   TextView txtToolbarTitle;
    @Override
    public int getContentView() {
        return R.layout.activity_reset_password;
    }

    @Override
    public void initView() {
     toolbar=findViewById(R.id.toolbar);
     txtToolbarTitle=toolbar.findViewById(R.id.toolbartxtViewTitle);
     init();

    }

    public void init() {
        txtToolbarTitle.setText(R.string.reset_password);

    }

    @Override
    public void setListeners() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void setActionBar() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
