package neosoft.training.neostore.view.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView homeToolbarTitle;
    private ViewPager mViewPager;
    Integer[] viewPagerImages={R.drawable.slider_img1,R.drawable.slider_img2,R.drawable.slider_img3,R.drawable.slider_img4};
    ArrayList<Integer> imgViewPagerArray=new ArrayList<>();

    int numPages=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        init();// call for view pager imgViewPagerArray
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        homeToolbarTitle.setText(R.string.mainLoginHeader);

        //custom view pager Adapter
       /* mPagerAdapter=new ViewPagerSliderAdapter(this,imgViewPagerArray);
        mViewPager.setAdapter(mPagerAdapter);*/


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
                        return true;
                    case R.id.navigation_item_logout:
                        return true;
                }
                return true;
            }
        });
    }

    private void init() {
        for(int i=0; i<viewPagerImages.length;i++)
            imgViewPagerArray.add(viewPagerImages[i]);
        mViewPager=findViewById(R.id.view_pager);
        mViewPager.setAdapter(new ViewPagerSliderAdapter(HomeActivity.this,imgViewPagerArray));
    }

    private void initView() {
        toolbar=findViewById(R.id.toolbar);
        mDrawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigation_view);
        homeToolbarTitle=toolbar.findViewById(R.id.toolbarTitle);

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


    private class ViewPagerSliderAdapter extends PagerAdapter{

        ArrayList<Integer> imgViewPagerArray =new ArrayList<>();
        LayoutInflater inflater;
        Context context;
        ImageView imageView;

        public ViewPagerSliderAdapter(Context context, ArrayList<Integer> imgViewPagerArray) {
           this.context=context;
           this.imgViewPagerArray =imgViewPagerArray;
           inflater=LayoutInflater.from(context);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return imgViewPagerArray.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           View imgLayout=inflater.inflate(R.layout.fragment_home,container,false);
            imageView=imgLayout.findViewById(R.id.imgViewPager);
            imageView.setImageResource(imgViewPagerArray.get(position));
            container.addView(imgLayout,0);
            return imgLayout;

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
}
