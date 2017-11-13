package neosoft.training.neostore.view.Product.fragment;


import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseAsyncTask;
import neosoft.training.neostore.common.base.ratingmodel.BaseRatingModel;
import neosoft.training.neostore.common.base.ratingmodel.RatingData;

/**
 * Created by webwerks1 on 11/10/17.
 */

public class RatingPopupFragment extends DialogFragment implements View.OnClickListener{
    Button btnSubmit;
    TextView txtItemName;
    ImageView imgProduct;
    RatingBar ratingBar;

    String url = "http://staging.php-dev.in:8844/trainingapp/api/products/setRating";
    private static final String TAG = RatingPopupFragment.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.rating_popup, null);
        txtItemName = view.findViewById(R.id.txtItemName);
        imgProduct = view.findViewById(R.id.imgItem);
        ratingBar = view.findViewById(R.id.ratingBarPopup);


        String prodId = getArguments().getString("productId");
        String txtName = getArguments().getString("ItemName");
        String imgProd = getArguments().getString("Image");

        txtItemName.setText(txtName);

        Glide.with(getActivity()).load(imgProd).into(imgProduct);

        builder.setView(view);
        btnSubmit = view.findViewById(R.id.btnSubmitRate);
        btnSubmit.setOnClickListener(this);

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("product_id", prodId);

        BaseAsyncTask baseAsyncTask = new BaseAsyncTask(getActivity(), "POST", mapData);
        baseAsyncTask.execute(url);

        Log.e(TAG, "onCreateDialog: " + "asfsadf");

        return builder.create();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmitRate:
                Toast.makeText(getActivity(), "Thank you for your Response", Toast.LENGTH_SHORT).show();


                break;
        }
    }


}
