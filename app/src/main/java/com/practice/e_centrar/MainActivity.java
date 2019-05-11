package com.practice.e_centrar;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import com.practice.e_centrar.DataContract.Customer_Entry;
import com.practice.e_centrar.DataContract.PurchaseInvoice_Entry;
import com.practice.e_centrar.DataContract.Locations_entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    SQLiteDatabase db;
    TextView lst;
    TextView textView;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mydb = new DatabaseHelper(this);
        //  insertDataInDb();

        lst = (TextView) findViewById(R.id.textView);
        textView = (TextView) findViewById(R.id.textView2);

    }

    public void loadStudents(View view) {
        lst.setText(mydb.loadHandler());
    }

    public void Callfunction(View view) {
        insertDataInDb();
    }

    public void maps_function(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void insertDataInDb() {
        db = mydb.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cv1 = new ContentValues();
        ContentValues cv2 = new ContentValues();
        ContentValues cv3 = new ContentValues();

        //inserting in customer table
        cv.put(Customer_Entry.COLUMN_FirstName, "Urooj");
        cv.put(Customer_Entry.COLUMN_LastName, "Khan");
        cv.put(Customer_Entry.COLUMN_Adress, "A315Block-C");
        cv.put(Customer_Entry.COLUMN_EnterpriseName, "NED");
        cv.put(Customer_Entry.COLUMN_JobPosition, "Manager");
        cv.put(Customer_Entry.COLUMN_Email, "urjkhan");
        cv.put(Customer_Entry.COLUMN_MobileNo, "043432");
        cv.put(Customer_Entry.COLUMN_PhoneNo, "434523");
        cv.put(Customer_Entry.COLUMN_CreatedBy, "tim");
        cv.put(Customer_Entry.COLUMN_DateCreated, "3-4-18");
        cv.put(Customer_Entry.COLUMN_UpdatedBy, "gim");
        cv.put(Customer_Entry.COLUMN_UpdatedDate, "4-3-18");
        cv.put(Customer_Entry.COLUMN_IsActive, "1");
        cv.put(Customer_Entry.COLUMN_PaymentMethod, "Credit");

        //inserting in purchase invoide table
        cv1.put(PurchaseInvoice_Entry.PURCHASE_SUPPLIER, "naveed");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE, "3-2-19");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_DUEDATE, "13-12-19");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_BALANCE, "3 lacs");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_PAIDAMOUNT, "2.5 lacs");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_TOTAL, "3 lacs");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_STATUS, "pending");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_INVOICEDATE, "30-6-19");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_REVENUE, "1.5 lacs");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_CREATEDBY, "jamil");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_CREATEDDATE, "5-1-19");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_UPDATEDBY, "shaz");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_UPDATEDDATE, "30-2-19");
        cv1.put(PurchaseInvoice_Entry.PURCHASE_ISACTIVE, "1");

        //inserting in product table
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_NAME, "Shampoo");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_CATEGORYID_FK_, "2");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_SKU, "432");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_VARIANTS, "5 pieces");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_INSTOCK, "300 pieces left");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_FULFILLED, "Yes");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_ONHAND, "Delivered");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_PRODUCTTYPEID_FK, "5");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_CREATEDBY, "zehra");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_CREATEDDATE, "21-8-18");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_UPDATEDBY, "Kashan");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_UPDATEDDATE, "29-8-18");
        cv2.put(DataContract.Product_Entry.COLUMN_PRODUCT_ISACTIVE, "1");

        //inserting data in goodnote table
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_ORDERID_FK, "3");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_ORDERSTATUS, "Onroute");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_DELIVERTO, "MIF Enterprise");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_PACKED, "Yes");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_PICKED, "No");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_PRINTED, "Ink");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_WAREHOUSE, "ABC");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_CREATEDBY, "Tahir");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_CREATEDDATE, "12-4-18");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_UPDATEDBY, "Khan");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_UPDATEDDATE, "30-5-18");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_ISACTIVE, "0");
        cv3.put(DataContract.GoodNotes_Entry.GOODNOTES_SHIPPED, "No");


        db.insert(Customer_Entry.TABLE_NAME, null, cv);
        db.insert(PurchaseInvoice_Entry.PURCHASE_ORDER_TABLE_NAME, null, cv1);
        db.insert(DataContract.Product_Entry.PRODUCT_TABLE_NAME, null, cv2);
        db.insert(DataContract.GoodNotes_Entry.GOODNOTES_TABLE_NAME, null, cv3);

    }

    public void view_locations(View view) {
        seedate();
    }

    Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    StringBuilder a;

    public void seedate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month++;
                String monthS = month>=10 ? Integer.toString(month) : "0"+Integer.toString(month);
                String dayS = day>=10 ? Integer.toString(day) : "0"+Integer.toString(day);

                 a = new StringBuilder()
                        // Month is 0 based, just add 1
                        .append(dayS).append("-").append(monthS).append("-")
                        .append(year);

                textView.setText(a + "");
                getmarkers();
            }
        }, mYear, mMonth, mDay);


        datePickerDialog.show();
        }

    public void getmarkers(){
        String search_by_date = a.toString();
     mydb = new DatabaseHelper(getApplicationContext());
       db = mydb.getReadableDatabase();
      Location location = mydb.getLocation(search_by_date);
    }
}






