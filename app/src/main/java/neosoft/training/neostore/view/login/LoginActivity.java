package neosoft.training.neostore.view.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.home.HomeActivity;

public class LoginActivity extends BaseActivity {
   TextView txtNeoStore,txtForget,txtAccount;
   EditText edtUserHint,edtPassHint;
   Button btnLogin;
   ImageView txtPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtNeoStore=findViewById(R.id.txtNeoStore);
        txtForget=findViewById(R.id.txtForget);
        txtAccount=findViewById(R.id.txtAccount);
        edtUserHint=findViewById(R.id.edtLogUsername);
        edtPassHint=findViewById(R.id.edtLogPassword);
        txtPlus=findViewById(R.id.txtPlus);
        btnLogin=findViewById(R.id.btnLogin);

        //value from assets
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/bold.otf");
        txtNeoStore.setTypeface(tf,Typeface.BOLD);
        Typeface tfUserHint = Typeface.createFromAsset(getAssets(),"fonts/medium.otf");
        edtUserHint.setTypeface(tfUserHint);
        edtPassHint.setTypeface(tfUserHint);
        txtForget.setTypeface(tfUserHint);
        txtAccount.setTypeface(tfUserHint);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });

        txtPlus.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
               startActivity(intent);
           }
       });


    }
}
