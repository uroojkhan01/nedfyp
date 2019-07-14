package com.practice.e_centrar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.bind.SqlDateTypeAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProductGridViewActivity extends AppCompatActivity {

    GridView gridview;
    DatabaseHelper mydb;
    SQLiteDatabase db;
    ArrayList<Product> arraylist;
    MyGridAdapter myGridAdapter;
    Toolbar toolbar;
    TextView txt;
    String senditem,enterprisename;
    Button button;
    Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    StringBuilder a;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String strDate = sdf.format(new Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_grid_view);
        mydb = new DatabaseHelper(this);
        txt = (TextView)findViewById(R.id.customername);
        senditem = getIntent().getExtras().get("id").toString();
        button = (Button)findViewById(R.id.button19);

        String query2 = " SELECT " + DataContract.Customer_Entry.COLUMN_EnterpriseName + " FROM " + DataContract.Customer_Entry.TABLE_NAME + " WHERE "
                + DataContract.Customer_Entry.COLUMN_CUSTOMERAPI_ID + " = '" + senditem + "'";

        db = mydb.getReadableDatabase();

        Cursor cursor = db.rawQuery(query2,null);

        int enterprisenameColumninex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_EnterpriseName);

        while(cursor.moveToNext()){
            enterprisename = cursor.getString(enterprisenameColumninex);
        }
        txt.setText(enterprisename);


        toolbar = (Toolbar)findViewById(R.id.toolbar);

        gridview = (GridView)findViewById(R.id.grid);

        arraylist = new ArrayList<>();
        arraylist = mydb.getProducts();
        myGridAdapter =new MyGridAdapter(this,arraylist);
        gridview.setAdapter(myGridAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ProductGridViewActivity.this,ProductDetails.class);
                intent.putExtra("pname", arraylist.get(position).getProductName());
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ProductGridViewActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        month++;
                        String monthS = month >= 10 ? Integer.toString(month) : "0" + Integer.toString(month);
                        String dayS = day >= 10 ? Integer.toString(day) : "0" + Integer.toString(day);

                        a = new StringBuilder()
                                // Month is 0 based, just add 1
                                .append(dayS).append("-").append(monthS).append("-")
                                .append(year);

                        booknow();
                    }
                }, mYear, mMonth, mDay);

                datePickerDialog.show();


            }
        });
    }

    public void booknow(){
        String b = a.toString();
        db = mydb.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataContract.Orders_entry.CREATED_BY,"Urooj");
        cv.put(DataContract.Orders_entry.CREATED_DATE,strDate);
        cv.put(DataContract.Orders_entry.CUSTOMER_ID_FK,senditem);
        cv.put(DataContract.Orders_entry.ISACTIVE,"true");
        cv.put(DataContract.Orders_entry.ORDER_DATE,b);
        cv.put(DataContract.Orders_entry.ISCONFIRMED,"true");

        db.insert(DataContract.Orders_entry.ORDER_TABLE_NAME, null, cv);

  //      getid();

        Log.v("insert",cv.toString());
    }

   /* public void getid(){
        String query = "Select * from " +DataContract.Orders_entry.ORDER_TABLE_NAME;
        db = mydb.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        Log.v("lll",c.toString());

        int column = c.getColumnIndex(DataContract.Orders_entry.ORDER_ID);
        c.moveToLast();
        String id = c.getString(column);

        Intent intent = new Intent(this,ProductDetails.class);
        intent.putExtra("id",id);
       // ProductDetails productDetails = new ProductDetails();
        //productDetails.getid(id);
*/

    }



