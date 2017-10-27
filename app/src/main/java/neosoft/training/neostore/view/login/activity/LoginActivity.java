package neosoft.training.neostore.view.login.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.LoginAsyncTask;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.home.activity.HomeActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
   private TextView txtNeoStore,txtAccount;
   private EditText edtUserHint,edtPassHint;
   private Button btnLogin;
   private ImageView txtPlus;
   private TextView txtForgot;
   private Context context=this;
   private String url="http://staging.php-dev.in:8844/trainingapp/api/users/login";

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    public void initView() {
        txtNeoStore=findViewById(R.id.circleImage);
        txtForgot=findViewById(R.id.txtForget);
        txtAccount=findViewById(R.id.txtAccount);
        edtUserHint=findViewById(R.id.edtLogUsername);
        edtPassHint=findViewById(R.id.edtNewPassword);
        txtPlus=findViewById(R.id.txtPlus);
        btnLogin=findViewById(R.id.btnLogin);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("Login",0);
        if(sharedPreferences.contains("Email")){
            Intent intent=new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void setListeners() {
        btnLogin.setOnClickListener(this);
        txtPlus.setOnClickListener(this);
        txtForgot.setOnClickListener(this);
    }

    @Override
    public void setActionBar() {

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()) {
            case R.id.btnLogin:
                validate();
                break;
            case R.id.txtPlus:
                intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.txtForget:
                intent=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
                break;
        }

}

    private void validate() {

        if (edtUserHint.getText().toString().equals("")){
            edtUserHint.setError("Please Enter Username");
        }
        else if(edtPassHint.getText().toString().equals("")){
            edtPassHint.setError("Please Enter Password");
        }
        else{

            //for Http post
            Map<String ,Object> loginData= new HashMap<>();
            loginData.put("email",edtUserHint.getText().toString());
            loginData.put("password",edtPassHint.getText().toString());
            LoginAsyncTask loginHttpPost=new LoginAsyncTask(loginData,context);
            loginHttpPost.execute(url);

        }
    }
}
