package neosoft.training.neostore.view.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import neosoft.training.neostore.R;
import neosoft.training.neostore.view.Product.activity.ProductDetailedActivity;
import neosoft.training.neostore.view.order.activity.MyOrderActivity;
import neosoft.training.neostore.view.order.activity.OrderIdActivity;

/**
 * Created by webwerks1 on 11/10/17.
 */

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.NumberViewHolder> {

    private Context context;

    public MyOrderAdapter(Context context) {
        this.context=context;

    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.order_list,null,false);
        NumberViewHolder numberViewHolder=new NumberViewHolder(view);
        return numberViewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(position);

    }


    @Override
    public int getItemCount() {
        return 4;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtOrderValue, txtOrderDateValue,txtOrderPrice;
        public NumberViewHolder(View itemView) {
            super(itemView);
        txtOrderValue = itemView.findViewById(R.id.orderIDValue);
        txtOrderDateValue =itemView.findViewById(R.id.orderDateValue);
        txtOrderPrice =itemView.findViewById(R.id.orderPrice);

        itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            Intent intent=new Intent(view.getContext(),OrderIdActivity.class);
            intent.putExtra("ItemTitle",txtOrderValue.getText().toString());
            view.getContext().startActivity(intent);

        }

        public void bind(int position) {
         txtOrderValue.setText(""+position);
         txtOrderDateValue.setText(""+position);
         txtOrderPrice.setText("Rs. "+position);

        }
    }
}
