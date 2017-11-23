package neosoft.training.neostore.view.mycart.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import neosoft.training.neostore.R;
import neosoft.training.neostore.model.MyCartData;
import neosoft.training.neostore.model.MyCartProduct;
import neosoft.training.neostore.view.mycart.activity.MyCartActivity;


/**
 * Created by webwerks1 on 13/10/17.
 */

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.NumberViewHolder> {
    private final Context context;
    Spinner spnQuantity;
    List<MyCartData> listData;
    MyCartProduct myCartProduct;
    MyCartData myCartData;
    ArrayList<String> items = new ArrayList<String>();
    String userToken;

    public MyCartAdapter(Context context, List<MyCartData> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_cart_list, parent, false);
        NumberViewHolder numberViewHolder = new NumberViewHolder(view);
        return numberViewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }


    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtOrderIdName, txtOrderNameDescription, txtOrderPrice;
        ImageView imgOrderId;
        public LinearLayout viewForeground, viewBackground;
        int totalPrice;

        public NumberViewHolder(View view) {
            super(view);
            txtOrderIdName = view.findViewById(R.id.orderName);
            imgOrderId = view.findViewById(R.id.imgOrder);
            txtOrderNameDescription = view.findViewById(R.id.orderNameDescription);
            spnQuantity = view.findViewById(R.id.spnQuantity);
            txtOrderPrice = view.findViewById(R.id.txtOrderPrice);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);


            SharedPreferences sharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
            userToken = sharedPreferences.getString("AccessToken", "");
            view.setOnClickListener(this);

        }

        public void bind(int position) {

            myCartData = listData.get(position);
            items.add("" + myCartData.getQuantity());
            txtOrderIdName.setText(myCartData.getProduct().getName());
            txtOrderNameDescription.setText(myCartData.getProduct().getProductCategory());

            // for Cart total price
            totalPrice=myCartData.getProduct().getCost();
            if(totalPrice!=0)
            txtOrderPrice.setText("Rs. " + totalPrice );
            else
                txtOrderPrice.setText("Rs. " +0);

            Glide.with(context).load(myCartData.getProduct().getProductImages()).into(imgOrderId);

//            //Spinner array adapter
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
//                    myCartData.getQuantity(), android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout
//                    .simple_spinner_dropdown_item);

            spnQuantity.setAdapter(new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_dropdown_item, items));
//            spnQuantity.setSelection(myCartData.getQuantity());
        }

        @Override
        public void onClick(View view) {

        }
    }

    public void onItemRemove(int position) {

        if (!listData.isEmpty()) {
            if (listData.size() >= 0) {
                ((MyCartActivity) context).deleteItemFromCart(userToken, listData.get(position).getProductId().toString());
                 listData.remove(position);
            }
            else
                Toast.makeText(context, "There is No item to deleted", Toast.LENGTH_SHORT).show();
        }
        else
            return;
        notifyItemRemoved(position);
    }

}
