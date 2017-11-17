package neosoft.training.neostore.view.mycart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import neosoft.training.neostore.R;
import neosoft.training.neostore.model.MyCartData;
import neosoft.training.neostore.model.MyCartProduct;


/**
 * Created by webwerks1 on 13/10/17.
 */

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.NumberViewHolder> {
    private final Context context;
    Spinner spnQuantity;
    List<MyCartData> listData;
    MyCartProduct myCartProduct;
    MyCartData myCartData;
    ArrayList<String > items=new ArrayList<String>();

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
        return listData.size();
    }


    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtOrderIdName, txtOrderNameDescription, txtOrderPrice;
        ImageView imgOrderId;

        public NumberViewHolder(View view) {
            super(view);
            txtOrderIdName = view.findViewById(R.id.orderName);
            imgOrderId = view.findViewById(R.id.imgOrder);
            txtOrderNameDescription = view.findViewById(R.id.orderNameDescription);
            spnQuantity = view.findViewById(R.id.spnQuantity);
            txtOrderPrice = view.findViewById(R.id.txtOrderPrice);
            view.setOnClickListener(this);

        }

        public void bind(int position) {

            myCartData = listData.get(position);
            items.add(""+myCartData.getQuantity());
            txtOrderIdName.setText(myCartData.getProduct().getName());
            txtOrderNameDescription.setText(myCartData.getProduct().getProductCategory());
            txtOrderPrice.setText("Rs." + myCartData.getProduct().getCost());

            Glide.with(context).load(myCartData.getProduct().getProductImages()).into(imgOrderId);


            spnQuantity .setAdapter(new ArrayAdapter<String>(context,
                            android.R.layout.simple_spinner_dropdown_item, items));

//            //Spinner array adapter
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
//                    myCartData.getQuantity(), android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout
//                    .simple_spinner_dropdown_item);





        }


        @Override
        public void onClick(View view) {

        }


    }
}
