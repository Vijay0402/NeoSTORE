package neosoft.training.neostore.view.address.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.model.AddAddressModel;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {
    Toolbar toolbarAdd;
    TextView toolbarTitle;
    Button btnSaveAddress;
    EditText edtUserAddress;

    private static final String TAG = AddAddressActivity.class.getSimpleName();

    @Override
    public int getContentView() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initView() {
        toolbarAdd = findViewById(R.id.toolbar);
        toolbarTitle = toolbarAdd.findViewById(R.id.toolbartxtViewTitle);
        btnSaveAddress = findViewById(R.id.btnSaveAddress);
        edtUserAddress = findViewById(R.id.edt_address_id);

    }

    @Override
    public void setListeners() {
        btnSaveAddress.setOnClickListener(this);
    }


    @Override
    public void setActionBar() {
        setSupportActionBar(toolbarAdd);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.reg_arrow);
        toolbarTitle.setText(R.string.save_address);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //inflate items to action bar if present
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSaveAddress:
                String userAddress = edtUserAddress.getText().toString();
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra("SavedAddress", userAddress);
                startActivity(intent);

        }

    }


}
