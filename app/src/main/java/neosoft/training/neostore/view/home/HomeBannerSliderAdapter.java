package neosoft.training.neostore.view.home;

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

    ArrayList<Integer> imgViewPagerArray =new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    ImageView imageView;

    public HomeBannerSliderAdapter(Context context) {
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
    // view is matched with key returned from instantiateItem
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}