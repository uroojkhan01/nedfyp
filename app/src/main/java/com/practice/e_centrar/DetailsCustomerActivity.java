package com.practice.e_centrar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsCustomerActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DatabaseHelper mydb;
    String senditem;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_customer);

        textView = (TextView)findViewById(R.id.text);
        mydb = new DatabaseHelper(this);

       senditem = getIntent().getExtras().get("name").toString();
        Toast.makeText(DetailsCustomerActivity.this,senditem, Toast.LENGTH_SHORT).show();

        String result = "";
        String query2 = "SELECT  * FROM " + DataContract.Customer_Entry.TABLE_NAME + " WHERE "
                + DataContract.Customer_Entry.COLUMN_EnterpriseName + " = '" + senditem + "'";

        db = mydb.getReadableDatabase();
        Cursor cursor = db.rawQuery(query2,null);

        int identity = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_CUSTOMERAPI_ID);
        int firstNameColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_FirstName);
        int lastNameColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_LastName);
        int addressColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_Adress);
        int enterpriseColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_EnterpriseName);
        int emailColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_Email);
        int Mob_NoColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_MobileNo);
        int Phone_noColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_PhoneNo);
        int Job_positionColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_JobPosition);
        int PaymentMethodColumnIndex = cursor.getColumnIndex(DataContract.Customer_Entry.COLUMN_PaymentMethod);

        while (cursor.moveToNext()) {

            String firstname = cursor.getString(firstNameColumnIndex);
            String id = cursor.getString(identity);
            String lastname = cursor.getString(lastNameColumnIndex);
            String email = cursor.getString(emailColumnIndex);
            String address = cursor.getString(addressColumnIndex);
            String enterprise = cursor.getString(enterpriseColumnIndex);
            String mobile = cursor.getString(Mob_NoColumnIndex);
            String phone = cursor.getString(Phone_noColumnIndex);
            String jobpos = cursor.getString(Job_positionColumnIndex);
            String payment_method = cursor.getString(PaymentMethodColumnIndex);

            result +="FirstName : " + firstname + "\n\n" + "LastName : " + lastname + "\n\n" + "Email : " + email + "\n\n" + "Address : " + address + "\n\n" + "EnterpriseName : " + enterprise + "\n\n" + "MobileNo : " + mobile + "\n\n" + "PhoneNo : " + phone +
                    " " + "JobPosition : " + jobpos + "\n\n"  + "PaymentMethod : " +payment_method + "\n\n" + "Id : " + id + "\n\n";
            System.getProperty("line.separator");
        }

        textView.setText(result);

    }


}
