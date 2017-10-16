package neosoft.training.neostore.view.Product.fragment;

import android.app.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import neosoft.training.neostore.R;
import neosoft.training.neostore.common.base.BaseFragment;
import neosoft.training.neostore.view.home.activity.HomeActivity;

/**
 * Created by webwerks1 on 11/10/17.
 */

public class EnterQuantityFragment extends DialogFragment implements View.OnClickListener {
    Button btnSubmit;



    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.enter_quantity, null);
        builder.setView(view);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        return builder.create();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
               Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_SHORT).show();
               break;

        }
    }
}