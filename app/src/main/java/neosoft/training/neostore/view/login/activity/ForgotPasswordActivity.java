package neosoft.training.neostore.view.login.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.data.remote.APIService;
import neosoft.training.neostore.data.remote.ApiUtils;
import neosoft.training.neostore.model.ForgetModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText edtEmail;
    private Button btnSubmit;
    APIService apiService;//interface variable

    private static final String TAG = ForgotPasswordActivity.class.getSimpleName();


    @Override
    public int getContentView() {
        return R.layout.activity_forgot_password;
    }

    @Override
    public void initView() {
     edtEmail=findViewById(R.id.edtForgotEmail);
     btnSubmit=findViewById(R.id.btnForgotSubmit);
     apiService= ApiUtils.getAPIService();

    }

    @Override
    public void setListeners() {
    btnSubmit.setOnClickListener(this);

    }

    @Override
    public void setActionBar() {

    }

    @Override
    public void onClick(View view) {
        String userEmail=edtEmail.getText().toString();
        sendPost(userEmail);

    }

    public void sendPost(String email) {
        apiService.savePost(email).enqueue(new Callback<ForgetModel>() {

            @Override
            public void onResponse(Call<ForgetModel> call, Response<ForgetModel> response) {

                if(response.isSuccessful()) {
                    ForgetModel data=response.body();
                    String msg=data.getUserMsg();
                    Log.i(TAG, "Post submitted to API." + response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<ForgetModel> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");

            }
        });
    }

}
