package neosoft.training.neostore.view.Product.fragment;

import android.app.AlertDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import neosoft.training.neostore.R;

/**
 * Created by webwerks1 on 11/10/17.
 */

public class RatingPopupFragment extends DialogFragment implements View.OnClickListener{
   Button btnRate;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rating_popup,null,false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.rating_popup,null,false);
        btnRate=view.findViewById(R.id.btnRate);
        btnRate.setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRate:
            Toast.makeText(getActivity(), "Thank you for your Response", Toast.LENGTH_SHORT).show();
        }
    }
}
