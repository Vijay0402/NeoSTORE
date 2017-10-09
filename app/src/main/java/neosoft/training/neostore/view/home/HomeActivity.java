package neosoft.training.neostore.view.home;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;
import neosoft.training.neostore.view.Product.ProductListingActivity;
import neosoft.training.neostore.view.address.AddAddressActivity;

public class HomeActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbarH;
    private TextView homeToolbarTitle;
    private ViewPager mViewPager;
    ImageView imgTable,imgSofa,imgChair,imgCupboard;


    Integer[] viewPagerImagesArray ={R.drawable.slider_img1,R.drawable.slider_img2,R.drawable.slider_img3,R.drawable.slider_img4};
    ArrayList<Integer> imgViewPager =new ArrayList<>();

    int currentPages=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        init();
        circleIndicator();
        productListeners();
        // call for view pager imgViewPager
        setSupportActionBar(toolbarH);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        homeToolbarTitle.setText(R.string.mainLoginHeader);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.navigation_item_mycart:
                        return true;
                    case R.id.navigation_item_tables:
                        return true;
                    case R.id.navigation_item_sofas:
                        return true;
                    case R.id.navigation_item_chairs:
                        return true;
                    case R.id.navigation_item_cupboards:
                        return true;
                    case R.id.navigation_item_myaccount:
                        return true;
                    case R.id.navigation_item_storelocator:
                        return true;
                    case R.id.navigation_item_myorders:
                        Intent intent=new Intent(HomeActivity.this, AddAddressActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_item_logout:
                        return true;
                }
                return true;
            }
        });
    }

    private void initView() {
        toolbarH =findViewById(R.id.toolbar);
        mDrawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigation_view);
        homeToolbarTitle= toolbarH.findViewById(R.id.toolbartxtViewTitle);
        imgTable=findViewById(R.id.imgTable);
        imgChair=findViewById(R.id.imgChair);
        imgSofa=findViewById(R.id.imgSofa);
        imgCupboard=findViewById(R.id.imgCupboard);

    }
    private void productListeners() {
        imgTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ProductListingActivity.class);
                intent.putExtra("Title","Tables");
                startActivity(intent);
            }
        });
        imgSofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ProductListingActivity.class);
                intent.putExtra("Title","Sofa");
                startActivity(intent);
            }
        });
        imgChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ProductListingActivity.class);
                intent.putExtra("Title","Chair");
                startActivity(intent);
            }
        });
        imgCupboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ProductListingActivity.class);
                intent.putExtra("Title","Cupboard");
                startActivity(intent);
            }
        });

    }

    //Auto start of image slider
    private void circleIndicator() {

        //circle indicator
        CircleIndicator indicator=findViewById(R.id.circle_indicator);
        indicator.setViewPager(mViewPager);
        // circleIndicator();
        final Handler handler=new Handler();
        final Runnable runnableUpdate=new Runnable() {
            @Override
            public void run() {
                if(currentPages == viewPagerImagesArray.length)
                {
                    currentPages=0;
                }
                mViewPager.setCurrentItem(currentPages++,true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnableUpdate);
            }
        },2500,2500);

    }

    private void init() {
        for(int i = 0; i< viewPagerImagesArray.length; i++)
            imgViewPager.add(viewPagerImagesArray[i]);
        mViewPager=findViewById(R.id.view_pager);
        mViewPager.setAdapter(new HomeBannerSliderAdapter(HomeActivity.this, imgViewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //inflate items to action bar if present
       getMenuInflater().inflate(R.menu.toolbar_menu,menu);
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


    }
