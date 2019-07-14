package com.practice.e_centrar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.practice.e_centrar.DataContract;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DatabaseHelper mydeb;
    TextView textview_result,ter;
    Button btnView, btnproduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        btnView = (Button) findViewById(R.id.button8);
        mydeb = new DatabaseHelper(this);
       // btnproduct = (Button) findViewById(R.id.getproduct);
        textview_result = (TextView) findViewById(R.id.textview);
        //ter = (TextView)findViewById(R.id.tree);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this, CustomerListAvtivity.class);
                startActivity(intent);
            }
        });

       /* btnproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CustomerActivity.this, GetProductFromAPI.class);
                startActivity(intent1);
            }
        });*/
    }

    public void delete(View view) {

        Intent intent = new Intent(CustomerActivity.this, CustomerListAvtivity.class);
        startActivity(intent);
    }

    public void mydel(View view) {

        db = mydeb.getReadableDatabase();
        mydeb = new DatabaseHelper(this);
        String deleter = " delete from " + DataContract.Customer_Entry.TABLE_NAME;
        db.execSQL(deleter);
    }

    public void myfunction(View View) {

        GetFromApi();
    }

    public void GetFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataContract.variablesentry.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonPlaceHolderApi = retrofit.create(JsonApi.class);
        Call<List<Customer>> call = jsonPlaceHolderApi.getcustomers();

        call.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if (!response.isSuccessful()) {
                    textview_result.setText("Code:" + response.code());
                    return;
                }

                List<Customer> customer = response.body();
                db = mydeb.getWritableDatabase();
                ContentValues cv = new ContentValues();
                for (Customer customers : customer) {

                    cv.put(DataContract.Customer_Entry.COLUMN_CUSTOMERAPI_ID, customers.getId());
                    cv.put(DataContract.Customer_Entry.COLUMN_FirstName, customers.getFirstName());
                    cv.put(DataContract.Customer_Entry.COLUMN_LastName, customers.getLastName());
                    cv.put(DataContract.Customer_Entry.COLUMN_SalesManager_ID, customers.getFK_SalesManager());
                    cv.put(DataContract.Customer_Entry.COLUMN_Adress, customers.getAddress());
                    cv.put(DataContract.Customer_Entry.COLUMN_Email, customers.getEmail());
                    cv.put(DataContract.Customer_Entry.COLUMN_EnterpriseName, customers.getEnterpriseName());
                    cv.put(DataContract.Customer_Entry.COLUMN_JobPosition, customers.getJobPosition());
                    cv.put(DataContract.Customer_Entry.COLUMN_PhoneNo, customers.getPhoneNo());
                    cv.put(DataContract.Customer_Entry.COLUMN_MobileNo, customers.getMobileNo());
                    cv.put(DataContract.Customer_Entry.COLUMN_PaymentMethod, customers.getPaymentMethod());
                    cv.put(DataContract.Customer_Entry.COLUMN_CreatedBy, customers.getCreatedBy());
                    cv.put(DataContract.Customer_Entry.COLUMN_DateCreated, customers.getCreatedDate());
                    cv.put(DataContract.Customer_Entry.COLUMN_UpdatedBy, customers.getUpdatedBy());
                    cv.put(DataContract.Customer_Entry.COLUMN_UpdatedDate, customers.getUpdatedDate());
                    cv.put(DataContract.Customer_Entry.COLUMN_IsActive, customers.getIsActive());

                    db.insert(DataContract.Customer_Entry.TABLE_NAME, null, cv);
                    Log.v("insert",cv.toString());

                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                     //  textview_result.setText(t.getMessage());
            }
        });

    }

    public void seeorder(View view){

       Intent intent = new Intent(this,OrderActivity.class);
       startActivity(intent);
    }

    public void seeorderline(View view){

        Intent intent = new Intent(this,OrderLineActivity.class);
        startActivity(intent);
    }
    public void inserssstorder(View view){
        db = mydeb.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DataContract.Orders_entry.CREATED_BY, "tim");
        cv.put(DataContract.Orders_entry.CREATED_DATE, "3-4-18");
        cv.put(DataContract.Orders_entry.UPDATED_BY, "gim");
        cv.put(DataContract.Orders_entry.UPDATED_DATE, "4-3-18");
        cv.put(DataContract.Orders_entry.ISCONFIRMED, "1");
        cv.put(DataContract.Orders_entry.ORDER_DATE, "3-4-18");
        cv.put(DataContract.Orders_entry.CUSTOMER_ID_FK, "3");
        cv.put(DataContract.Orders_entry.COUNT, "5");
        cv.put(DataContract.Orders_entry.ISACTIVE,"1");

        db.insert(DataContract.Orders_entry.ORDER_TABLE_NAME, null, cv);

        Log.d("insert",cv.toString());
    }

    public void insertorder(View view){

        String result = "";
        String query = "Select * from " +DataContract.Orders_entry.ORDER_TABLE_NAME;
        db = mydeb.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        Log.v("lll",c.toString());

        int column = c.getColumnIndex(DataContract.Orders_entry.ORDER_ID);
        c.moveToLast();
            String id = c.getString(column);
            result+= id + " ";

        ter.setText(result);

    }

}
  /*  public void DFunction(View view){
        retrievedata();
    }

    public void retrievedata() {
        String result = "";
        String query = "Select * FROM " + DataContract.Customer_Entry.TABLE_NAME;
        db =mydeb.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int FirstNameColumn = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_FirstName);
        int LastNametColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_LastName);
        int IsActiveColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_IsActive);
        int Created_ByColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_CreatedBy);
        int Date_CreatedColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_DateCreated);
        int Updated_byColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_UpdatedBy);
        int Updated_dateColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_UpdatedDate);
        int EmailColumn = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_Email);
        int PhoneNoColumn = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_PhoneNo);
        int MobileNoColumn = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_MobileNo);
        int EnterPriseNameColumn = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_EnterpriseName);
        int AddressColumn = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_Adress);
        int JobPositionColumn = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_JobPosition);
        int PaymentMethodColumn = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_PaymentMethod);


        while (cursor.moveToNext()) {

            String FirstName = cursor.getString(FirstNameColumn);
            String LastName = cursor.getString(LastNametColumnIndex);
            String IsActive = cursor.getString(IsActiveColumnIndex);
            String Created_By = cursor.getString(Created_ByColumnIndex);
            String Date_Created = cursor.getString(Date_CreatedColumnIndex);
            String Updated_by = cursor.getString(Updated_byColumnIndex);
            String Updated_date = cursor.getString(Updated_dateColumnIndex);
            String Email = cursor.getString(EmailColumn);
            String PhoneNo = cursor.getString(PhoneNoColumn);
            String MobileNo = cursor.getString(MobileNoColumn);
            String EnterPriseName = cursor.getString(EnterPriseNameColumn);
            String Address = cursor.getString(AddressColumn);
            String Payment = cursor.getString(PaymentMethodColumn);
            String JobPosition = cursor.getString(JobPositionColumn);

            result += FirstName + " " + LastName + " " + IsActive + " " + Created_By + " " + Date_Created + " " + Updated_by + " " + Updated_date +
                   " " + Email + " " + PhoneNo + " " + MobileNo + " " + EnterPriseName + " " + Address + " " + Payment + " " + JobPosition + "\n\n";
            System.getProperty("line.separator");
        }

        cursor.close();
        db.close();

        textview_result.setText(result);

    }
*/



