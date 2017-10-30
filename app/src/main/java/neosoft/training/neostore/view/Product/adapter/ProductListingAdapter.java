package neosoft.training.neostore.view.Product.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import neosoft.training.neostore.R;
import neosoft.training.neostore.model.ProductListModel;
import neosoft.training.neostore.view.Product.activity.ProductDetailedActivity;

/**
 * Created by webwerks1 on 6/10/17.
 */

public class ProductListingAdapter extends RecyclerView.Adapter<ProductListingAdapter.NumberViewHolder> {

    ProductListModel productListModel;
    private List<ProductListModel> listData = new ArrayList<>();
    private Context context;

    public ProductListingAdapter(Context context, List<ProductListModel> data) {
        this.context = context;
        this.listData = data;
    }


    @Override
    public ProductListingAdapter.NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_list, parent, false);
        NumberViewHolder numberViewHolder = new NumberViewHolder(view);
        return numberViewHolder;

    }

    @Override
    public void onBindViewHolder(ProductListingAdapter.NumberViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productName, productDescription, productPrice;
        ImageView iconImageView;
        RatingBar ratingBar;

        //AsyncTask

        public NumberViewHolder(View itemView) {
            super(itemView);
            iconImageView = (ImageView) itemView.findViewById(R.id.imgProductList);
            productName = itemView.findViewById(R.id.txtProducListName);
            productDescription = itemView.findViewById(R.id.txtProductListDescription);
            productPrice = itemView.findViewById(R.id.txtProductListPrice);
            ratingBar = itemView.findViewById(R.id.ratingBar);

            // to make toast in recycler view when click on row
            itemView.setOnClickListener(this);
            //new activity when click on particular item
        }

        void bind(int position) {
            productListModel = listData.get(position);
            productName.setText(productListModel.getName());
            productDescription.setText(productListModel.getProducer());
            productPrice.setText("Rs."+productListModel.getCost());
            ratingBar.setRating(productListModel.getRating());
            Glide.with(context).load(productListModel.getProduct_images()).into(iconImageView);

        }

        @Override
        public void onClick(View view) {
            // to make toast in recycler view when click on row
            Toast.makeText(view.getContext(), (getAdapterPosition() + 1 + " OF " + 10), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), ProductDetailedActivity.class);
            intent.putExtra("ItemTitle", productName.getText().toString());
            view.getContext().startActivity(intent);

        }
    }
}