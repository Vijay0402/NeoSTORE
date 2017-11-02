package neosoft.training.neostore.view.login.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.common.base.BaseAsyncTask;
import neosoft.training.neostore.model.RegistrationModel;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, BaseAsyncTask.onAsyncRequestComplete{
    private TextView txtCondition, txtNeostore, txtTerms;
    private EditText edtFirstname, edtLastname, edtEmail, edtPassword, edtConfirmPassword, edtPhoneno;
    private Toolbar toolbarR;
    private RadioGroup rgGender;
    private String strGender;
    private CheckBox cbTC;
    private Button btnRegister;
    private Boolean bTerms = false;
    private static final String TAG = RegistrationActivity.class.getSimpleName();

    private String url="http://staging.php-dev.in:8844/trainingapp/api/users/register";

    @Override
    public int getContentView() {

        return R.layout.activity_registration;
    }

    public void initView() {

        txtCondition = findViewById(R.id.txtRegCondition);
        txtNeostore = findViewById(R.id.circleImage);
        txtTerms = findViewById(R.id.cbRegTerms);
        edtFirstname = findViewById(R.id.edtRegFirstName);
        edtLastname = findViewById(R.id.edtRegLastName);
        edtEmail = findViewById(R.id.edtRegEmail);
        edtPassword = findViewById(R.id.edtPhoneno);
        edtConfirmPassword = findViewById(R.id.edtDOB);
        edtPhoneno = findViewById(R.id.edtRegPhone);
        rgGender = findViewById(R.id.rgGrpGender);
        cbTC = findViewById(R.id.cbRegTerms);
        btnRegister = findViewById(R.id.btnRegRegister);
        toolbarR = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public void setListeners() {
        btnRegister.setOnClickListener(this);
        rgGender.setOnCheckedChangeListener(this);
        cbTC.setOnCheckedChangeListener(this);
    }

    @Override
    public void setActionBar() {
        setSupportActionBar(toolbarR);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        if (bTerms) {
            if (validate()) {
                Map<String, Object> userData = new HashMap<>();
                userData.put("first_name", edtFirstname.getText().toString());
                userData.put("last_name", edtLastname.getText().toString());
                userData.put("email", edtEmail.getText().toString());
                userData.put("password", edtPassword.getText().toString());
                userData.put("confirm_password", edtConfirmPassword.getText().toString());
                userData.put("gender", strGender);
                userData.put("phone_no",edtPhoneno.getText().toString());
                BaseAsyncTask httpPostAsyncTask=new BaseAsyncTask(this,"POST",userData);
                httpPostAsyncTask.execute(url);

            }
        }
        else {
            Toast.makeText(this, "Please Accept the Terms and Condition", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate() {
        if (edtFirstname.getText().toString().equals(""))
            edtFirstname.setError("Please Enter First Name");
        else if (edtLastname.getText().toString().equals(""))
            edtLastname.setError("Please Enter Last Name");
        else if(edtEmail.getText().toString().equals(""))
            edtEmail.setError("Please Enter Email");
        else if (edtPassword.getText().toString().equals(""))
            edtPassword.setError("Please Enter Password");
        else if (edtConfirmPassword.getText().toString().equals(""))
            edtConfirmPassword.setError("Please Enter value in Confirm Password Field");
        else if (edtPhoneno.getText().toString().equals(""))
            edtPhoneno.setError("Please Enter value in Phone Field");

        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rdMale:
                strGender = "M";
                break;
            case R.id.rdFemale:
                strGender = "F";
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b)
            bTerms = true;
        else
            bTerms = false;

    }

    @Override
    public void asyncResponse(Object response) {

        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(response));
            int statusid = jsonObject.optInt("status");
//          JSONObject dataObject = jsonObject.optJSONObject("data");

            Gson gson=new Gson();
            RegistrationModel sampleModel=gson.fromJson(response.toString(),RegistrationModel.class);
            Log.e(TAG, "asyncResponse: "+sampleModel.data.getUsername() );

            /*sampleModel.data.setId(dataObject.optInt("id"));
            sampleModel.data.setRole_id(dataObject.optInt("role_id"));
            sampleModel.data.setFirst_name(dataObject.optString("first_name"));
            sampleModel.data.setLast_name(dataObject.optString("last_name"));
            sampleModel.data.setEmail(dataObject.optString("email"));
            sampleModel.data.setUsername(dataObject.optString("username"));
            sampleModel.data.setProfile_pic(dataObject.optString("profile_pic"));
            sampleModel.data.setCountry_id(dataObject.optString("country_id"));
            sampleModel.data.setGender(dataObject.optString("gender"));
            sampleModel.data.setPhone_no(dataObject.optString("phone_no"));
            sampleModel.data.setPhone_no(dataObject.optString("phone_no"));
            sampleModel.data.setDob(dataObject.optString("dob"));
            sampleModel.data.setIs_active(dataObject.optBoolean("is_active"));
            sampleModel.data.setCreated(dataObject.optString("created"));
            sampleModel.data.setModified(dataObject.optString("modified"));
            sampleModel.data.setAccess_token(dataObject.optString("access_token"));*/

            Log.e("json",""+statusid);
            Intent intent=new Intent(this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
        catch (JSONException e){

            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(Object response) {
        Toast.makeText(this, "Registration not Successful", Toast.LENGTH_SHORT).show();
    }
}
