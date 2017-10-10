package neosoft.training.neostore.view.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.home.activity.HomeActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
   TextView txtNeoStore,txtForget,txtAccount;
   EditText edtUserHint,edtPassHint;
   Button btnLogin;
   ImageView txtPlus;


    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    public void initView() {
        txtNeoStore=findViewById(R.id.txtNeoStore);
        txtForget=findViewById(R.id.txtForget);
        txtAccount=findViewById(R.id.txtAccount);
        edtUserHint=findViewById(R.id.edtLogUsername);
        edtPassHint=findViewById(R.id.edtLogPassword);
        txtPlus=findViewById(R.id.txtPlus);
        btnLogin=findViewById(R.id.btnLogin);

    }

    @Override
    public void setListeners() {
        btnLogin.setOnClickListener(this);
        txtPlus.setOnClickListener(this);

    }

    @Override
    public void setActionBar() {

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.btnLogin:
                intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.txtPlus:
                intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                break;


        }
    }
}
