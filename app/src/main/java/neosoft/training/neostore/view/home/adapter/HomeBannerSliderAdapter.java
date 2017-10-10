package neosoft.training.neostore.view.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import neosoft.training.neostore.R;

/**
 * Created by webwerks1 on 9/10/17.
 */

public class HomeBannerSliderAdapter extends PagerAdapter {


    LayoutInflater inflater;
    Context context;
    ImageView imageView;

    public HomeBannerSliderAdapter(Context context) {
        this.context=context;

        inflater=LayoutInflater.from(context);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imgLayout=inflater.inflate(R.layout.fragment_home,container,false);
        imageView=imgLayout.findViewById(R.id.imgViewPager);
        imageView.setImageResource(R.drawable.slider_img1);
        container.addView(imgLayout,0);
        return imgLayout;
    }
    // view is matched with key returned from instantiateItem
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}