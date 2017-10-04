package neosoft.training.neostore.view.login;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;

public class RegistrationActivity extends BaseActivity {
   private TextView txtCondition,txtNeostore,txtTerms;
   EditText edtFirstname,edtLastname,edtEmail,edtPassword,edtConfirmPassword,edtPhoneno;
   Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();
        txtCondition.setPaintFlags(txtCondition.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
        //Medium fonts
        Typeface tfbold=Typeface.createFromAsset(getAssets(),"fonts/bold.otf")  ;
        Typeface tfmed=Typeface.createFromAsset(getAssets(),"fonts/medium.otf")  ;
        txtNeostore.setTypeface(tfbold);
        txtCondition.setTypeface(tfmed);
        txtTerms.setTypeface(tfmed);
        edtFirstname.setTypeface(tfmed);
        edtLastname.setTypeface(tfmed);
        edtEmail.setTypeface(tfmed);
        edtPassword.setTypeface(tfmed);
        edtConfirmPassword.setTypeface(tfmed);
        edtPhoneno.setTypeface(tfmed);

    }

    private void initView() {
        txtCondition=findViewById(R.id.txtRegCondition);
        txtNeostore=findViewById(R.id.txtNeoStore);
        txtTerms=findViewById(R.id.txtRegTerms);
        edtFirstname=findViewById(R.id.edtRegFirstName);
        edtLastname=findViewById(R.id.edtRegLastName);
        edtEmail=findViewById(R.id.edtRegEmail);
        edtPassword=findViewById(R.id.edtRegPassword);
        edtConfirmPassword=findViewById(R.id.edtRegConfirmPassword);
        edtPhoneno=findViewById(R.id.edtRegPhone);

        toolbar=(Toolbar)findViewById(R.id.toolbar);

    }
}
