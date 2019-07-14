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
public class SeeLocationFragment extends Fragment {


    public SeeLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View root =  inflater.inflate(R.layout.fragment_see_location, container, false);
      // Intent intent = new Intent(getActivity(),MainActivity.class);
       //startActivity(intent);
         MainActivity mainActivity = new MainActivity();
        mainActivity.seedate();

    return root;
    }

}
