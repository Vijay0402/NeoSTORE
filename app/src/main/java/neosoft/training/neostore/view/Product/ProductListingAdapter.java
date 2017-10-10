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
import neosoft.training.neostore.view.Product.activity.ProductDetailedActivity;

/**
 * Created by webwerks1 on 6/10/17.
 */

public class ProductListingAdapter extends RecyclerView.Adapter<ProductListingAdapter.NumberViewHolder> {

   private Context context;

    public ProductListingAdapter(Context context) {
this.context=context;
    }


    @Override
    public ProductListingAdapter.NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.product_list,parent,false);
        NumberViewHolder numberViewHolder=new NumberViewHolder(view);
        return numberViewHolder;

    }

    @Override
    public void onBindViewHolder(ProductListingAdapter.NumberViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }



    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         TextView productName;
         ImageView iconImageView;
         public NumberViewHolder(View itemView){
            super(itemView);
            iconImageView = (ImageView) itemView.findViewById(R.id.imgProductList);
            productName=itemView.findViewById(R.id.txtProducListName);
            // to make toast in recycler view when click on row
            itemView.setOnClickListener(this);
            //new activity when click on particular item



        }
        void bind(int position){
            productName.setText(String.valueOf("Product : "+position));

        }
        @Override
        public void onClick(View v) {
            // to make toast in recycler view when click on row
             Toast.makeText(v.getContext(), (getAdapterPosition()+1+" OF "+10), Toast.LENGTH_SHORT).show();
             Intent intent=new Intent(v.getContext(),ProductDetailedActivity.class);
             intent.putExtra("ItemTitle",productName.getText().toString());
             v.getContext().startActivity(intent);

        }
    }
}
