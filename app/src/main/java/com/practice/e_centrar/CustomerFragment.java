package com.practice.e_centrar;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends Fragment {
ProgressDialog dialog;

    public CustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_customer, container, false);
        dialog = new ProgressDialog(getActivity());
       dialog.setTitle("Good Day");
       dialog.setMessage("showing customers");
       dialog.show();

        Intent intent = new Intent(getActivity(),CustomerListAvtivity.class);
        startActivity(intent);
    return  root;
    }

}
