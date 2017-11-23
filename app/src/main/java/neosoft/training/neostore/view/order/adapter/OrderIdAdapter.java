package neosoft.training.neostore.view.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import neosoft.training.neostore.R;
import neosoft.training.neostore.model.OrderDetail;

/**
 * Created by webwerks1 on 12/10/17.
 */

public class OrderIdAdapter extends RecyclerView.Adapter<OrderIdAdapter.NumberViewHolder> {
    private Context context;
    List<OrderDetail> listOrderDetail;

    public OrderIdAdapter(Context context, List<OrderDetail> listOrderDetail)
    {
        this.context = context;
        this.listOrderDetail=listOrderDetail;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.order_id_list,null,false);
        NumberViewHolder viewHolder=new NumberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return listOrderDetail == null ? 0 : listOrderDetail.size();
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtOrderIdName,txtOrderNameDescription,txtQuantity,txtOrderPrice;
        ImageView imgOrderId;

        public NumberViewHolder(View view) {
            super(view);
         txtOrderIdName=view.findViewById(R.id.orderName);
         imgOrderId=view.findViewById(R.id.imgOrder);
         txtOrderNameDescription=view.findViewById(R.id.orderNameDescription);
         txtQuantity=view.findViewById(R.id.txtQuantity);
         txtOrderPrice=view.findViewById(R.id.itemOrderPrice);
         view.setOnClickListener(this);
        }

        @Override
         public void onClick(View view) {

        }

        public void bind(int position) {


            txtOrderIdName.setText(listOrderDetail.get(position).getProdName());
            txtOrderNameDescription.setText(listOrderDetail.get(position).getProdCatName());
            txtQuantity.setText(""+listOrderDetail.get(position).getQuantity());
            txtOrderPrice.setText("Rs. "+listOrderDetail.get(position).getTotal());

            Glide.with(context).load(listOrderDetail.get(position).getProdImage()).into(imgOrderId);

        }
    }


}
