package neosoft.training.neostore.view.Product.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.ProductDetailDataModel;
import neosoft.training.neostore.common.base.ProductDetailImage;
import neosoft.training.neostore.common.base.ProductListingData;
import neosoft.training.neostore.view.Product.activity.ProductDetailedActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by webwerks1 on 10/10/17.
 */
public class ProductDetailedAdapter extends RecyclerView.Adapter<ProductDetailedAdapter.NumberViewHolder> {
    List<ProductDetailImage>  data;
    ViewPager viewPager;
    ProductDetailedActivity productDetailedActivity;

    private Context context;

    public ProductDetailedAdapter(Context context, List<ProductDetailImage> data) {
        this.context=context;
        this.data=data;
    }

    public ProductDetailedAdapter(ProductDetailedActivity productDetailedActivity) {
      this.productDetailedActivity=productDetailedActivity;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.product_detail_image_recycler,parent,false);
        NumberViewHolder numberViewHolder=new NumberViewHolder(view);
        return numberViewHolder;

    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();

    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView iconImageView;

        public NumberViewHolder(View itemView){
            super(itemView);
            iconImageView =  itemView.findViewById(R.id.imgProductDetailRecycler);
            // to make toast in recycler view when click on row
            itemView.setOnClickListener(this);
            //new activity when click on particular item

        }
        void bind(int position){
             ProductDetailImage productDetailDataModel=data.get(position);

             Glide.with(context).load(productDetailDataModel.getImage()).into(iconImageView);

        }

        @Override
        public void onClick(View view) {

        }
    }
}