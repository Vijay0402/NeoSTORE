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

import java.util.ArrayList;

import neosoft.training.neostore.R;
import neosoft.training.neostore.view.address.activity.AddAddressData;

/**
 * Created by webwerks1 on 11/10/17.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.NumberViewHolder> {

    private Context context;
    ArrayList<AddAddressData> arrayListAddress;

    public AddressListAdapter(Context context, ArrayList<AddAddressData> arrayListAddress) {
        this.arrayListAddress=arrayListAddress;
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
        return arrayListAddress==null ? 0 : arrayListAddress.size();
    }

    public void notifyData(ArrayList<AddAddressData> arrayListAddress) {
        this.arrayListAddress=arrayListAddress;
        notifyDataSetChanged();
    }

    public class NumberViewHolder extends ViewHolder implements View.OnClickListener {
       TextView txtName,txtAddress;
       ImageView clear;

        public NumberViewHolder(View view) {
            super(view);
            txtName=view.findViewById(R.id.txt_address_name);
            txtAddress=view.findViewById(R.id.txtAddressListDescription);
            clear=view.findViewById(R.id.imgClear);
        }

        @Override
        public void onClick(View view) {

        }

        public void bind(int position) {
            txtName.setText(arrayListAddress.get(position).getUserAddress());

        }
    }
}
