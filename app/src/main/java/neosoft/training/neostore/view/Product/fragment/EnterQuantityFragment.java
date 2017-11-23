package neosoft.training.neostore.view.Product.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import neosoft.training.neostore.R;
import neosoft.training.neostore.data.remote.APIService;
import neosoft.training.neostore.data.remote.ApiUtils;
import neosoft.training.neostore.model.EnterQuantityModel;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by webwerks1 on 11/10/17.
 */

public class EnterQuantityFragment extends DialogFragment implements View.OnClickListener {

    Button btnSubmit;
    TextView txtItemname;
    EditText edtQuantity;
    ImageView imgQuantity;
    APIService apiService;
    String prodId;
    String access;
    String quantity;
    int totalCartProduct;
    private static final String TAG = EnterQuantityFragment.class.getSimpleName();


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.enter_quantity, null);
        builder.setView(view);
        btnSubmit = view.findViewById(R.id.btnQtySubmit);
        imgQuantity=view.findViewById(R.id.imgItem);
        txtItemname = view.findViewById(R.id.txtItemname);
        edtQuantity = view.findViewById(R.id.edtQuantity);

        apiService= ApiUtils.getAPIService();

        prodId = getArguments().getString("productId");
        String txtName = getArguments().getString("ItemName");
        String imgProd = getArguments().getString("Image");
        String setFirst = getArguments().getString("SetFirstImage");

        txtItemname.setText(txtName);

        Glide.with(getActivity()).load(setFirst).into(imgQuantity);

        btnSubmit.setOnClickListener(this);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        access=sharedPreferences.getString("AccessToken","");
        return builder.create();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnQtySubmit:
                quantity=edtQuantity.getText().toString();
                addToCart(access, prodId,quantity);
                Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                break;

        }
    }

    private void addToCart(String access, String prodId, String qty) {

        apiService.cartPost(access,prodId,qty).enqueue(new Callback<EnterQuantityModel>() {

            @Override
            public void onResponse(retrofit2.Call<EnterQuantityModel> call, Response<EnterQuantityModel> response) {
                if(response.isSuccessful()) {
                    EnterQuantityModel data=response.body();//body() convert from Json to pojo
                    Log.i(TAG, "Post submitted to API." + response.body().toString());
                     totalCartProduct=data.getTotalCarts();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<EnterQuantityModel> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");

            }
        });
    }


}