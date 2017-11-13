package neosoft.training.neostore.view.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseAsyncTask;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.model.RegistrationModel;
import neosoft.training.neostore.view.Product.fragment.EnterQuantityFragment;
import neosoft.training.neostore.view.home.activity.HomeActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener, BaseAsyncTask.onAsyncRequestComplete {
    private TextView txtNeoStore, txtAccount;
    private EditText edtUserHint, edtPassHint;
    private Button btnLogin;
    private ImageView txtPlus;
    private TextView txtForgot;
    private Context context = this;
    private String url = "http://staging.php-dev.in:8844/trainingapp/api/users/login";
    private static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    public void initView() {
        txtNeoStore = findViewById(R.id.circleImage);
        txtForgot = findViewById(R.id.txtForget);
        txtAccount = findViewById(R.id.txtAccount);
        edtUserHint = findViewById(R.id.edtLogUsername);
        edtPassHint = findViewById(R.id.edtNewPassword);
        txtPlus = findViewById(R.id.txtPlus);
        btnLogin = findViewById(R.id.btnLogin);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Login", 0);
        if (sharedPreferences.contains("Email")) {
            Intent intent = new Intent(this, HomeActivity.class);
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
        switch (view.getId()) {
            case R.id.btnLogin:
                validate();
                break;
            case R.id.txtPlus:
                intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.txtForget:
                intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void validate() {

        if (edtUserHint.getText().toString().equals("")) {
            edtUserHint.setError("Please Enter Username");
        } else if (edtPassHint.getText().toString().equals("")) {
            edtPassHint.setError("Please Enter Password");
        } else {

            //for Http post
            Map<String, Object> loginData = new HashMap<>();
            loginData.put("email", edtUserHint.getText().toString());
            loginData.put("password", edtPassHint.getText().toString());
            BaseAsyncTask loginHttpPost = new BaseAsyncTask(this, "POST", loginData);
            loginHttpPost.execute(url);

        }
    }

    @Override
    public void asyncResponse(Object response) {
        try {

            Log.e(TAG, "asyncResponse: "+response );

                Gson gson = new GsonBuilder().serializeNulls().create();
                RegistrationModel sampleModel = gson.fromJson(response.toString(), RegistrationModel.class);
                Log.e(TAG, "asyncResponse: "+sampleModel.data.getUsername());

                /*sampleModel.setId(dataObject.optInt("id"));
                sampleModel.setRole_id(dataObject.optInt("role_id"));
                sampleModel.setFirst_name(dataObject.optString("first_name"));
                sampleModel.setLast_name(dataObject.optString("last_name"));
                sampleModel.setEmail(dataObject.optString("email"));
                sampleModel.setUsername(dataObject.optString("username"));
                sampleModel.setProfile_pic(dataObject.optString("profile_pic"));
                sampleModel.setCountry_id(dataObject.optString("country_id"));
                sampleModel.setGender(dataObject.optString("gender"));
                sampleModel.setPhone_no(dataObject.optInt("phone_no"));
                sampleModel.setDob(dataObject.optString("dob"));
                sampleModel.setIs_active(dataObject.optBoolean("is_active"));
                sampleModel.setCreated(dataObject.optString("created"));
                sampleModel.setModified(dataObject.optString("modified"));
                sampleModel.setAccess_token(dataObject.optString("access_token"));
*/
               // Log.e(TAG, "asyncResponse: "+sampleModel.getEmail());
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Username", String.valueOf(sampleModel.data.getUsername()));
                editor.putString("Email", String.valueOf(sampleModel.data.getEmail()));
                editor.putString("AccessToken",String.valueOf(sampleModel.data.getAccess_token()));
                editor.commit();
                Log.e(TAG, "LoginActivity: "+sampleModel.data.getAccess_token());

//              EnterQuantityFragment enterQuantityFragment = new EnterQuantityFragment();
//              Bundle bundle=new Bundle();
//              bundle.putString("accessToken",sampleModel.data.getAccess_token());
//              enterQuantityFragment.setArguments(bundle);


                //to pass navigation textview
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Object response) {
        Toast.makeText(this, "Email or password is wrong. try again", Toast.LENGTH_SHORT).show();

    }
}
