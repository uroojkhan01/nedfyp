package com.practice.e_centrar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderLineActivity extends AppCompatActivity {

    TextView textView;
    SQLiteDatabase db;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_line);

        mydb = new DatabaseHelper(this);
        textView = (TextView)findViewById(R.id.txt);

        String result = "";
        db =mydb.getReadableDatabase();
        String query = "Select * FROM " + DataContract.OrderLine_Entry.ORDERLINES_TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        int IsActiveColumnIndex = cursor.getColumnIndex(DataContract.OrderLine_Entry.ISACTIVE);
        int Created_ByColumnIndex = cursor.getColumnIndex(DataContract.OrderLine_Entry.CREATED_BY);
        int Date_CreatedColumnIndex = cursor.getColumnIndex(DataContract.OrderLine_Entry.CREATED_DATE);
        int Updated_byColumnIndex = cursor.getColumnIndex(DataContract.OrderLine_Entry.UPDATED_BY);
        int Updated_dateColumnIndex = cursor.getColumnIndex(DataContract.OrderLine_Entry.UPDATED_DATE);
        int OrderIDColumnIndex = cursor.getColumnIndex(DataContract.OrderLine_Entry.ORDER_ID_FK);
        int ProductIdColumnIndex = cursor.getColumnIndex(DataContract.OrderLine_Entry.PRODUCT_ID_FK);
        int QuantityColumnIndex = cursor.getColumnIndex(DataContract.OrderLine_Entry.QUANTITY);
        int TotalPriceColumnIndex = cursor.getColumnIndex(DataContract.OrderLine_Entry.TOTALPRICE);

        while (cursor.moveToNext()) {

            String update_by = cursor.getString(Updated_byColumnIndex);
            String updated_date = cursor.getString(Updated_dateColumnIndex);
            String date_created = cursor.getString(Date_CreatedColumnIndex);
            String is_active = cursor.getString(IsActiveColumnIndex);
            String created_by = cursor.getString(Created_ByColumnIndex);
            String orderid = cursor.getString(OrderIDColumnIndex);
            String productid = cursor.getString(ProductIdColumnIndex);
            String quantity = cursor.getString(QuantityColumnIndex);
            String totalprice = cursor.getString(TotalPriceColumnIndex);

            result += "ProductID : " + productid + "\n\n " + "Quantity : " + quantity + "\n\n " + "TotalPrice : " +  totalprice + "\n\n " + "IsActive : " + is_active +  "\n\n " + "OrderID : " + orderid  +
                    "\n\n " + "CreatedDate: " + date_created + "\n\n" + "CreatedBy : " +created_by+ "\n\n " + "UpdatedBy: " + update_by + " \n\n" + "UpdatedDate : " + updated_date + "\n\n\n\n";
            System.getProperty("line.separator");

        }

        textView.setText(result);
        cursor.close();

    }
}
