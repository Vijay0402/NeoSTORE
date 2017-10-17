package neosoft.training.neostore.view.Product.fragment;


import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import neosoft.training.neostore.R;

/**
 * Created by webwerks1 on 11/10/17.
 */

public class RatingPopupFragment extends DialogFragment implements View.OnClickListener{
   Button btnSubmit;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.rating_popup,null);
        builder.setView(view);
        btnSubmit=view.findViewById(R.id.btnSubmitRate);
        btnSubmit.setOnClickListener(this);
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
