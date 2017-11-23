package neosoft.training.neostore.view.Product.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.productmodel.ProductDetailImage;
import neosoft.training.neostore.common.base.productmodel.ProductDetailModel;

/**
 * Created by webwerks1 on 10/10/17.
 */
public class ProductDetailedAdapter extends RecyclerView.Adapter<ProductDetailedAdapter.NumberViewHolder> {
    List<ProductDetailImage> data;
    ProductDetailImage productDetailImage;
    private Context context;
    ProductDetailModel productDetailModel;
    ImageView iconImageView, imgProduct;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;


    public ProductDetailedAdapter(Context context, List<ProductDetailImage> data, ImageView imgProduct) {
        this.context = context;
        this.data = data;
        this.imgProduct = imgProduct;
        mPref = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        mEditor = mPref.edit();

    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_detail_image_recycler, parent, false);
        NumberViewHolder numberViewHolder = new NumberViewHolder(view);
        return numberViewHolder;

    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, final int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();

    }

    public void setSelected(int pos) {
        try {
            if (data.size() > 1) {
                data.get(mPref.getInt("position", 0)).setSelected(false);
                mEditor.putInt("position", pos);
                mEditor.commit();
            }
            data.get(pos).setSelected(true);
            notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout llRecyclerView;

        public NumberViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.imgProductDetailRecycler);
            llRecyclerView = itemView.findViewById(R.id.ll_recyclerview);
            itemView.setOnClickListener(this);

        }

        void bind(final int position) {

            if (data.get(position).isSelected()) {
                llRecyclerView.setBackgroundColor(Color.RED);
            } else {
                llRecyclerView.setBackgroundColor(Color.TRANSPARENT);
            }

            productDetailImage = data.get(position);
            String image = productDetailImage.getImage();
            Glide.with(context).load(image).into(iconImageView);
        }

        @Override
        public void onClick(View view) {

        }
    }

}