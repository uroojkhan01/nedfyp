package com.practice.e_centrar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Customer> arrayList;
    Button btn,btn1;
    SQLiteDatabase db;
    DatabaseHelper mydb;
    String id;

    MyListAdapter(Context context, ArrayList<Customer> arrayList){

        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.customlistlayout,null);
        TextView t1 = (TextView)convertView.findViewById(R.id.listtext);
        btn = (Button)convertView.findViewById(R.id.button17);
        btn1 = (Button)convertView.findViewById(R.id.button18);
        final Customer customer = arrayList.get(position);

        t1.setText(customer.getEnterpriseName());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailsCustomerActivity.class);
                intent.putExtra("name", arrayList.get(position).getEnterpriseName());
                context.startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydb = new DatabaseHelper(context);
                String myname = customer.getEnterpriseName();

                String query2 = " SELECT " + DataContract.Customer_Entry.COLUMN_CUSTOMERAPI_ID + " FROM " + DataContract.Customer_Entry.TABLE_NAME + " WHERE "
                        + DataContract.Customer_Entry.COLUMN_EnterpriseName + " = '" + myname + "'";

                db = mydb.getReadableDatabase();

                Cursor cursor = db.rawQuery(query2,null);

                int idColumninex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_CUSTOMERAPI_ID);

                while(cursor.moveToNext()){
                     id = cursor.getString(idColumninex);
                }

                Intent intent = new Intent (context,ProductGridViewActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });
        return convertView;
    }


}
