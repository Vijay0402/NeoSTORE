package neosoft.training.neostore.view.address.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import neosoft.training.neostore.R;

/**
 * Created by webwerks1 on 11/10/17.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.NumberViewHolder> {

    private Context context;

    public AddressListAdapter(Context context) {
        this.context=context;

    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      context=parent.getContext();
      LayoutInflater inflater=LayoutInflater.from(context);
      View view=inflater.inflate(R.layout.shipping_address_list,parent,false);
      NumberViewHolder viewHolder=new NumberViewHolder(view);
      return viewHolder;

    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {

      holder.bind(position);


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class NumberViewHolder extends ViewHolder implements View.OnClickListener {
       TextView txtName,txtAddress;
       ImageView clear;

        public NumberViewHolder(View view) {
            super(view);
            txtName=view.findViewById(R.id.txtAddressListShipping);
            txtAddress=view.findViewById(R.id.txtAddressListDescription);
            clear=view.findViewById(R.id.imgClear);
        }

        @Override
        public void onClick(View view) {

        }

        public void bind(int position) {
            txtName.setText(R.string.address);
        }
    }
}
