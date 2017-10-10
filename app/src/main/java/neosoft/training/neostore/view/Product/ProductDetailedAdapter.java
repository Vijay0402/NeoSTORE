package neosoft.training.neostore.view.Product;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import neosoft.training.neostore.R;

/**
 * Created by webwerks1 on 10/10/17.
 */
public class ProductDetailedAdapter extends RecyclerView.Adapter<ProductDetailedAdapter.NumberViewHolder> {

    private Context context;

    public ProductDetailedAdapter(Context context) {
        this.context=context;
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
        return 4;
    }



    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView iconImageView;
        public NumberViewHolder(View itemView){
            super(itemView);
            iconImageView = (ImageView) itemView.findViewById(R.id.imgProductDetailRecycler);
            // to make toast in recycler view when click on row
            itemView.setOnClickListener(this);
            //new activity when click on particular item



        }
        void bind(int position){
            iconImageView.setImageResource(R.drawable.slider_img1);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
