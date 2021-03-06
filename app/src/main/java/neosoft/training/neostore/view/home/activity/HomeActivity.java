package neosoft.training.neostore.view.home.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.Product.activity.ProductListingActivity;
import neosoft.training.neostore.view.home.adapter.HomeBannerSliderAdapter;
import neosoft.training.neostore.view.login.activity.LoginActivity;
import neosoft.training.neostore.view.mycart.activity.MyCartActivity;
import neosoft.training.neostore.view.order.activity.MyOrderActivity;

public class HomeActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbarH;
    private TextView homeToolbarTitle, txtUsername, txtEmail;
    private ViewPager mViewPager;
    private ImageView imgTable, imgSofa, imgChair, imgCupboard;

    int currentPages = 0;
    int maxPages = 5;

    @Override
    public int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        mViewPager = findViewById(R.id.view_pager);
        toolbarH = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        homeToolbarTitle = toolbarH.findViewById(R.id.toolbartxtViewTitle);
        imgTable = findViewById(R.id.imgTable);
        imgChair = findViewById(R.id.imgChair);
        imgSofa = findViewById(R.id.imgSofa);
        imgCupboard = findViewById(R.id.imgCupboard);

        View headerView = navigationView.getHeaderView(0);
        txtUsername = headerView.findViewById(R.id.user_name);
        txtEmail = headerView.findViewById(R.id.user_email);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Login", 0);
        txtUsername.setText(sharedPreferences.getString("Username", ""));
        txtEmail.setText(sharedPreferences.getString("Email", ""));
        setAdapter();
        circleIndicator();
    }

    private void setAdapter() {
        mViewPager.setAdapter(new HomeBannerSliderAdapter(HomeActivity.this));
    }

    @Override
    public void setListeners() {
        navigationView.setNavigationItemSelectedListener(this);
        imgTable.setOnClickListener(this);
        imgChair.setOnClickListener(this);
        imgSofa.setOnClickListener(this);
        imgCupboard.setOnClickListener(this);

    }

    @Override
    public void setActionBar() {
        setSupportActionBar(toolbarH);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        homeToolbarTitle.setText(R.string.mainLoginHeader);

    }

    //Auto start of image slider
    private void circleIndicator() {

        //circle indicator
        CircleIndicator indicator = findViewById(R.id.circle_indicator);
        indicator.setViewPager(mViewPager);
        // circleIndicator();
        final Handler handler = new Handler();
        final Runnable runnableUpdate = new Runnable() {
            @Override
            public void run() {
                if (currentPages == maxPages) {
                    currentPages = 0;
                }
                mViewPager.setCurrentItem(currentPages++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnableUpdate);
            }
        }, 2500, 2500);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //inflate items to action bar if present
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_search:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        int id = view.getId();
        switch (id) {
            case R.id.imgTable:
                intent = new Intent(this, ProductListingActivity.class);
                intent.putExtra("Title", "Tables");
                intent.putExtra("product_category_id","1");
                startActivity(intent);
                break;
            case R.id.imgSofa:
                intent = new Intent(this, ProductListingActivity.class);
                intent.putExtra("Title", "Sofas");
                intent.putExtra("product_category_id","3");
                startActivity(intent);
                break;
            case R.id.imgChair:
                intent = new Intent(this, ProductListingActivity.class);
                intent.putExtra("Title", "Chairs");
                intent.putExtra("product_category_id","2");
                startActivity(intent);
                break;
            case R.id.imgCupboard:
                intent = new Intent(this, ProductListingActivity.class);
                intent.putExtra("Title", "Cupboards");
                intent.putExtra("product_category_id","4");
                startActivity(intent);
                break;

        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.navigation_item_mycart:
                intent = new Intent(HomeActivity.this, MyCartActivity.class);
                startActivity(intent);
                return true;
            case R.id.navigation_item_tables:
                intent = new Intent(HomeActivity.this, ProductListingActivity.class);
                intent.putExtra("product_category_id","1");
                startActivity(intent);
                return true;
            case R.id.navigation_item_sofas:
                intent = new Intent(HomeActivity.this, ProductListingActivity.class);
                intent.putExtra("product_category_id","3");
                startActivity(intent);
                return true;
            case R.id.navigation_item_chairs:
                intent = new Intent(HomeActivity.this, ProductListingActivity.class);
                intent.putExtra("product_category_id","2");
                startActivity(intent);
                return true;
            case R.id.navigation_item_cupboards:
                intent = new Intent(HomeActivity.this, ProductListingActivity.class);
                intent.putExtra("product_category_id","4");
                startActivity(intent);
                return true;
            case R.id.navigation_item_myaccount:
                return true;
            case R.id.navigation_item_storelocator:
                return true;
            case R.id.navigation_item_myorders:
                intent = new Intent(HomeActivity.this, MyOrderActivity.class);
                startActivity(intent);
                return true;
            case R.id.navigation_item_logout:
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().apply();
                intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}
