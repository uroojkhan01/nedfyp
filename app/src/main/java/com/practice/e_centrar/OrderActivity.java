package com.practice.e_centrar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    TextView textView;
SQLiteDatabase db;
DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mydb = new DatabaseHelper(this);
        textView = (TextView)findViewById(R.id.txt);

        String result = "";
        db =mydb.getReadableDatabase();
        String query = "Select * FROM " + DataContract.Orders_entry.ORDER_TABLE_NAME;
         Cursor cursor = db.rawQuery(query,null);

        int IsActiveColumnIndex = cursor.getColumnIndex(DataContract.Orders_entry.ISACTIVE);
        int Created_ByColumnIndex = cursor.getColumnIndex(DataContract.Orders_entry.CREATED_BY);
        int Date_CreatedColumnIndex = cursor.getColumnIndex(DataContract.Orders_entry.CREATED_DATE);
        int Updated_byColumnIndex = cursor.getColumnIndex(DataContract.Orders_entry.UPDATED_BY);
        int Updated_dateColumnIndex = cursor.getColumnIndex(DataContract.Orders_entry.UPDATED_DATE);
        int CustomerIDColumnIndex = cursor.getColumnIndex(DataContract.Orders_entry.CUSTOMER_ID_FK);
        int OrderDateColumnIndex = cursor.getColumnIndex(DataContract.Orders_entry.ORDER_DATE);
        int ISconfirmedColumnIndex = cursor.getColumnIndex(DataContract.Orders_entry.ISCONFIRMED);


        while (cursor.moveToNext()) {

            String update_by = cursor.getString(Updated_byColumnIndex);
            String updated_date = cursor.getString(Updated_dateColumnIndex);
            String date_created = cursor.getString(Date_CreatedColumnIndex);
            String is_active = cursor.getString(IsActiveColumnIndex);
            String created_by = cursor.getString(Created_ByColumnIndex);
            String customerid = cursor.getString(CustomerIDColumnIndex);
            String order = cursor.getString(OrderDateColumnIndex);
            String isconfirmed = cursor.getString(ISconfirmedColumnIndex);

            result += "OrderDate : " + order + "\n\n " + "CreatedBy : " + created_by + "\n\n " + "DateCreated : " +  date_created + "\n\n " + "IsActive : " + is_active +  "\n\n " + "CustomerIdFk : " + customerid  +
                    "\n\n " + "IsConfirmed: " + isconfirmed + "\n\n " + "UpdatedBy: " + update_by + " \n\n" + "UpdatedDate : " + updated_date + "\n\n\n\n";
            System.getProperty("line.separator");

        }

        textView.setText(result);
        cursor.close();

    }
}
