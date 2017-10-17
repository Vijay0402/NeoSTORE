package neosoft.training.neostore.view.login.activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;

public class RegistrationActivity extends BaseActivity {
   private TextView txtCondition,txtNeostore,txtTerms;
   EditText edtFirstname,edtLastname,edtEmail,edtPassword,edtConfirmPassword,edtPhoneno;
   Toolbar toolbarR;

    @Override
    public int getContentView() {
        return R.layout.activity_registration;
    }

    public void initView() {
        txtCondition=findViewById(R.id.txtRegCondition);
        txtNeostore=findViewById(R.id.circleImage);
        txtTerms=findViewById(R.id.txtRegTerms);
        edtFirstname=findViewById(R.id.edtRegFirstName);
        edtLastname=findViewById(R.id.edtRegLastName);
        edtEmail=findViewById(R.id.edtRegEmail);
        edtPassword=findViewById(R.id.edtPhoneno);
        edtConfirmPassword=findViewById(R.id.edtDOB);
        edtPhoneno=findViewById(R.id.edtRegPhone);

        toolbarR =(Toolbar)findViewById(R.id.toolbar);
    }

    @Override
    public void setListeners() {

        setSupportActionBar(toolbarR);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
    }

    @Override
    public void setActionBar() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
