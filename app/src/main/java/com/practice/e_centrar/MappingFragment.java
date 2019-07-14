package com.practice.e_centrar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MappingFragment extends Fragment {


    public MappingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mapping, container, false);
        Intent intent = new Intent(getActivity(),MapsActivity.class);
        startActivity(intent);
        return  root;

    }

}
