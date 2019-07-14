package com.practice.e_centrar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDetails extends AppCompatActivity {

    SQLiteDatabase db;
    DatabaseHelper mydb;
    String senditem, getid;
    TextView textView;
    String initial = "http://192.168.1.5";
    String gif;
    ImageView img;
    String key,productid,productunitprice,spinnertext;
    ArrayAdapter<String> adapter;
    String record="";
    Spinner spinner;
    TextView diplay;
    Float mul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        diplay = (TextView)findViewById(R.id.textView8);
        spinner  = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (ProductDetails.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.quantity));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                      record = (String)spinner.getSelectedItem();
                        mul = Float.parseFloat(record);
                        diplay.setText(record);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button btn = (Button)findViewById(R.id.button11);

        textView = (TextView)findViewById(R.id.textView6);
        img = (ImageView)findViewById(R.id.imageView3);
        mydb = new DatabaseHelper(this);

//        key = getIntent().getExtras().getString("id").toString();
        senditem = getIntent().getExtras().get("pname").toString();
        Toast.makeText(ProductDetails.this,senditem, Toast.LENGTH_SHORT).show();

        String result = "";
        String query2 = "SELECT  * FROM " + DataContract.Product_Entry.PRODUCT_TABLE_NAME + " WHERE "
                + DataContract.Product_Entry.COLUMN_PRODUCT_NAME + " = '" + senditem + "'";


        db = mydb.getReadableDatabase();
        Cursor cursor2 = db.rawQuery(query2,null);

        int productidColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCTAPI_ID);
        int productnameColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_NAME);
        int productSKUColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_SKU);
        int productvariantColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_VARIANTS);
        int producttype_IDColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_PRODUCTTYPEID_FK);
        int productCategory_IDColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_CATEGORYID_FK_);
        final int productunitpriceColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_UNITPRICE);
        int productimageColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_IMAGE);

        while (cursor2.moveToNext()) {
            String productname = cursor2.getString(productnameColumnIndex);
            String productsku = cursor2.getString(productSKUColumnIndex);
            String productvariant = cursor2.getString(productvariantColumnIndex);
            String producttype = cursor2.getString(producttype_IDColumnIndex);
            String productcategory = cursor2.getString(productCategory_IDColumnIndex);
            productunitprice = cursor2.getString(productunitpriceColumnIndex);
            String productimage = cursor2.getString(productimageColumnIndex);
             productid = cursor2.getString(productidColumnIndex);

            gif = initial.concat(productimage);
            Log.v("imagelog",gif);

            Picasso.with(this).load(gif).placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(img, new com.squareup.picasso.Callback() {

                        @Override
                        public void onSuccess() {

                            Bitmap bitmap = ((BitmapDrawable) img.getDrawable()).getBitmap();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] imageInByte = baos.toByteArray();
                            Log.d("final", imageInByte.toString());
                            // hello(imageInByte);
                        }

                        @Override
                        public void onError() {

                        }
                    });

            result += "Id: " + productid + "NAME : " + productname + "\n\n" + "SKU : " + productsku + "\n\n" + "VARIANT : " + productvariant + " \n\n" + "PRODUCT TYPE : " + producttype + "\n\n" + "PRODUCT CATEGORY : " + productcategory + "\n\n" +
                  "PRODUCT UNITPRICE : " +  productunitprice + "Rs" +  "\n\n";
            System.getProperty("line.seperator");
        }
        textView.setText(result);
        Float price = Float.parseFloat(productunitprice);
        Float calc = mul*price;

        Log.v("spinner", calc.toString());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query = "Select * from " +DataContract.Orders_entry.ORDER_TABLE_NAME;
                db = mydb.getReadableDatabase();
                Cursor c = db.rawQuery(query,null);
                Log.v("lll",c.toString());

                int column = c.getColumnIndex(DataContract.Orders_entry.ORDER_ID);
                c.moveToLast();
                String id = c.getString(column);



                db = mydb.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = sdf.format(new Date());

                contentValues.put(DataContract.OrderLine_Entry.CREATED_DATE,strDate);
                contentValues.put(DataContract.OrderLine_Entry.CREATED_BY,"Sameera");
                contentValues.put(DataContract.OrderLine_Entry.ISACTIVE,"true");
                contentValues.put(DataContract.OrderLine_Entry.ORDER_ID_FK,id);
                contentValues.put(DataContract.OrderLine_Entry.PRODUCT_ID_FK,productid);
                contentValues.put(DataContract.OrderLine_Entry.QUANTITY,record);
                contentValues.put(DataContract.OrderLine_Entry.TOTALPRICE,"43434");

                db.insert(DataContract.OrderLine_Entry.ORDERLINES_TABLE_NAME,null,contentValues);
                Log.d("aaaa",contentValues.toString());

            }
        });

    }

/*    public void getid (String id) {

        String query2 = "SELECT  * FROM " + DataContract.Product_Entry.PRODUCT_TABLE_NAME + " WHERE "
                + DataContract.Product_Entry.COLUMN_PRODUCT_NAME + " = '" + senditem + "'";

        db = mydb.getReadableDatabase();
        Cursor cursor2 = db.rawQuery(query2, null);

        int productIdColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCTAPI_ID);
        while (cursor2.moveToNext()) {
            String productid = cursor2.getString(productIdColumnIndex);
            insertorder(id,productid);
        }

    }

public void insertorder(String id, String productid){

            db = mydb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = sdf.format(new Date());

        contentValues.put(DataContract.OrderLine_Entry.CREATED_DATE,strDate);
        contentValues.put(DataContract.OrderLine_Entry.CREATED_BY,"Sameera");
        contentValues.put(DataContract.OrderLine_Entry.ISACTIVE,"true");
        contentValues.put(DataContract.OrderLine_Entry.ORDER_ID_FK,id);
        contentValues.put(DataContract.OrderLine_Entry.PRODUCT_ID_FK,productid);
        contentValues.put(DataContract.OrderLine_Entry.QUANTITY,"2323");
        contentValues.put(DataContract.OrderLine_Entry.TOTALPRICE,"43434");

        db.insert(DataContract.OrderLine_Entry.ORDERLINES_TABLE_NAME,null,contentValues);
        Log.d("aaaa",contentValues.toString());
    }*/
}
