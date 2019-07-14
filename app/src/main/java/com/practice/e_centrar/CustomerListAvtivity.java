package com.practice.e_centrar;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class CustomerListAvtivity extends AppCompatActivity {

    DatabaseHelper myDB;
    MyListAdapter myListAdapter;
    ArrayList<Customer> arrayList;
    ListView listView;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_avtivity);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);
        arrayList = new ArrayList<>();
        arrayList = myDB.getList();
        myListAdapter = new MyListAdapter(this, arrayList);
        listView.setAdapter(myListAdapter);
       // myListAdapter.notifyDataSetChanged();

    }
}



