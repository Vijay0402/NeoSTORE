package neosoft.training.neostore.view.mycart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import neosoft.training.neostore.R;


/**
 * Created by webwerks1 on 13/10/17.
 */

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.NumberViewHolder>{
    private final Context context;
    Spinner spnQuantity;
    public MyCartAdapter(Context context) {
        this.context=context;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_cart_list,parent,false);
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


   class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtOrderIdName,txtOrderNameDescription,txtOrderPrice;
        ImageView imgOrderId;
        Spinner spnQuantity;

        public NumberViewHolder(View view) {
            super(view);
            txtOrderIdName=view.findViewById(R.id.orderName);
            imgOrderId=view.findViewById(R.id.imgOrder);
            txtOrderNameDescription=view.findViewById(R.id.orderNameDescription);
            spnQuantity=view.findViewById(R.id.spnQuantity);
            txtOrderPrice=view.findViewById(R.id.txtOrderPrice);
            view.setOnClickListener(this);
            //Spinner array adapter
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                    R.array.quantity_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout
                    .simple_spinner_dropdown_item);
            spnQuantity.setAdapter(adapter);
        }
        public void bind(int position) {
            txtOrderIdName.setText(""+position);
            txtOrderNameDescription.setText(""+position);
            txtOrderPrice.setText(""+position);

        }

        @Override
        public void onClick(View view) {

        }


    }
}
