package neosoft.training.neostore.common.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by webwerks1 on 4/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        setListeners();
        setActionBar();

    }

    public abstract int  getContentView();
    public abstract void initView();
    public abstract void setListeners();
    public abstract void setActionBar();
}
