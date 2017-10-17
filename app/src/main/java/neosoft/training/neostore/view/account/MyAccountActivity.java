package neosoft.training.neostore.view.account;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.login.activity.ResetPasswordActivity;

public class MyAccountActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView txtToolbarTitle;
    Button btnReset;

    @Override
    public int getContentView() {
        return R.layout.activity_my_account;
    }

    @Override
    public void initView() {
        toolbar=findViewById(R.id.toolbar);
        txtToolbarTitle=toolbar.findViewById(R.id.toolbartxtViewTitle);
        txtToolbarTitle.setText(R.string.my_account);
        btnReset=findViewById(R.id.btnReset);
    }

    @Override
    public void setListeners() {
     btnReset.setOnClickListener(this);
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
            case R.id.btnReset:
                Intent intent=new Intent(MyAccountActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnReset:
                Intent intent = new Intent(MyAccountActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
