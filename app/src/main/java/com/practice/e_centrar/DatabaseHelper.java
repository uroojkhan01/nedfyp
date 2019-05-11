package com.practice.e_centrar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.practice.e_centrar.DataContract.Customer_Entry;
import com.practice.e_centrar.DataContract.Product_Entry;
import com.practice.e_centrar.DataContract.GoodNotes_Entry;
import com.practice.e_centrar.DataContract.PurchaseInvoice_Entry;
import com.practice.e_centrar.DataContract.Locations_entry;
import java.lang.ref.PhantomReference;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hello.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CUSTOMER_TABLE = "CREATE TABLE " + DataContract.Customer_Entry.TABLE_NAME + " ( "+
                DataContract.Customer_Entry.COLUMN_Customer_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DataContract.Customer_Entry.COLUMN_FirstName+" TEXT, " + DataContract.Customer_Entry.COLUMN_LastName+" TEXT, "+
                DataContract.Customer_Entry.COLUMN_Adress +" TEXT, "+ DataContract.Customer_Entry.COLUMN_EnterpriseName +" TEXT, "+ DataContract.Customer_Entry.COLUMN_JobPosition + " TEXT, " +
                DataContract.Customer_Entry.COLUMN_Email +" TEXT, "+ DataContract.Customer_Entry.COLUMN_MobileNo +" TEXT, " + DataContract.Customer_Entry.COLUMN_PhoneNo + " TEXT, "+
                DataContract.Customer_Entry.COLUMN_DateCreated +" TEXT, "+ DataContract.Customer_Entry.COLUMN_PaymentMethod +" TEXT, " + DataContract.Customer_Entry.COLUMN_IsActive + " TEXT, " +
                DataContract.Customer_Entry.COLUMN_CreatedBy +" TEXT, "+ DataContract.Customer_Entry.COLUMN_UpdatedBy +" TEXT, " + DataContract.Customer_Entry.COLUMN_UpdatedDate + " TEXT );";

        String PRODUCT_TABLE= "CREATE TABLE " + Product_Entry.PRODUCT_TABLE_NAME + " ( " + Product_Entry.COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Product_Entry.COLUMN_PRODUCT_NAME + " TEXT, " +
                Product_Entry.COLUMN_PRODUCT_CATEGORYID_FK_+ " INTEGER, " + Product_Entry.COLUMN_PRODUCT_PRODUCTTYPEID_FK + " INTEGER, " + Product_Entry.COLUMN_PRODUCT_ONHAND + " TEXT, " +
                Product_Entry.COLUMN_PRODUCT_INSTOCK + " TEXT, " + Product_Entry.COLUMN_PRODUCT_FULFILLED + " TEXT, " + Product_Entry.COLUMN_PRODUCT_SKU + " TEXT, " +
                Product_Entry.COLUMN_PRODUCT_IMAGE + " TEXT, " + Product_Entry.COLUMN_PRODUCT_CREATEDBY + " TEXT, " + Product_Entry.COLUMN_PRODUCT_CREATEDDATE + " TEXT, " +
                Product_Entry.COLUMN_PRODUCT_UPDATEDBY + " TEXT, " + Product_Entry.COLUMN_PRODUCT_UPDATEDDATE + " TEXT, " + Product_Entry.COLUMN_PRODUCT_ISACTIVE + " TEXT, " +
                Product_Entry.COLUMN_PRODUCT_VARIANTS + " TEXT ); ";

        String GOODNOTE_TABLE = " CREATE TABLE " + GoodNotes_Entry.GOODNOTES_TABLE_NAME + "( " + GoodNotes_Entry.GOODNOTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GoodNotes_Entry.GOODNOTES_ORDERID_FK + " INTEGER, " + GoodNotes_Entry.GOODNOTES_DELIVERTO + " TEXT, " + GoodNotes_Entry.GOODNOTES_ORDERSTATUS + " TEXT, " +
                GoodNotes_Entry.GOODNOTES_PACKED + " TEXT, " + GoodNotes_Entry.GOODNOTES_PICKED + " TEXT, " + GoodNotes_Entry.GOODNOTES_PRINTED + " TEXT, " +
                GoodNotes_Entry.GOODNOTES_SHIPPED + " TEXT, " + GoodNotes_Entry.GOODNOTES_WAREHOUSE + " TEXT, " + GoodNotes_Entry.GOODNOTES_CREATEDBY + " TEXT, " +
                GoodNotes_Entry.GOODNOTES_CREATEDDATE + " TEXT, " + GoodNotes_Entry.GOODNOTES_UPDATEDBY + " TEXT, " + GoodNotes_Entry.GOODNOTES_UPDATEDDATE + " TEXT, " +
                GoodNotes_Entry.GOODNOTES_ISACTIVE + " TEXT ); " ;

        String PURCHASE_INVOICE = "CREATE TABLE " + PurchaseInvoice_Entry.PURCHASE_ORDER_TABLE_NAME + " ( " + PurchaseInvoice_Entry.PURCHASE_INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PurchaseInvoice_Entry.PURCHASE_SUPPLIER + " TEXT, " + PurchaseInvoice_Entry.PURCHASE_BALANCE + " TEXT, " + PurchaseInvoice_Entry.PURCHASE_DUEDATE + " TEXT, " +
                PurchaseInvoice_Entry.PURCHASE_INVOICEDATE + " TEXT, " + PurchaseInvoice_Entry.PURCHASE_PAIDAMOUNT + " TEXT, " + PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE + " TEXT, " +
                PurchaseInvoice_Entry.PURCHASE_STATUS + " TEXT, " + PurchaseInvoice_Entry.PURCHASE_REVENUE + " TEXT, " + PurchaseInvoice_Entry.PURCHASE_TOTAL + " TEXT, " +
                PurchaseInvoice_Entry.PURCHASE_CREATEDBY + " TEXT, " + PurchaseInvoice_Entry.PURCHASE_CREATEDDATE + " TEXT, " + PurchaseInvoice_Entry.PURCHASE_UPDATEDBY + " TEXT, " +
                PurchaseInvoice_Entry.PURCHASE_UPDATEDDATE + " TEXT, " + PurchaseInvoice_Entry.PURCHASE_ISACTIVE + " TEXT );";
        Log.v("DatabaseHelper",PURCHASE_INVOICE);

        String LOCATION_TRACKER =  "create table " + Locations_entry.LOCATION_TABLE_NAME + " ( " +
                Locations_entry.LOCATION_ID + " integer primary key autoincrement , " +
                Locations_entry.LOCATION_LATITUDE + " REAL , " +
                Locations_entry.LOCATION_LONGITUDE + " REAL , " +
                Locations_entry.LOCATION_ZOOM + " TEXT , "  +
                Locations_entry.CURRENT_DATE + " TEXT " +
                " ); ";
        Log.v("DatabaseHelper",LOCATION_TRACKER);

        db.execSQL(CUSTOMER_TABLE);
        db.execSQL(PRODUCT_TABLE);
        db.execSQL(GOODNOTE_TABLE);
        db.execSQL(PURCHASE_INVOICE);
        db.execSQL(LOCATION_TRACKER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DataContract.Customer_Entry.TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + PurchaseInvoice_Entry.PURCHASE_ORDER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Product_Entry.PRODUCT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GoodNotes_Entry.GOODNOTES_TABLE_NAME);

        onCreate(db);
    }

    public String loadHandler() {

        String result = "";
        String query = "Select * FROM " + DataContract.Customer_Entry.TABLE_NAME ;
        String query1 = "Select * FROM " + PurchaseInvoice_Entry.PURCHASE_ORDER_TABLE_NAME;
        String query2 = "Select * FROM " + Product_Entry.PRODUCT_TABLE_NAME;
        String query3 = "Select * FROM " + GoodNotes_Entry.GOODNOTES_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Cursor cursor1 = db.rawQuery(query1,null);
        Cursor cursor2 = db.rawQuery(query2,null);
        Cursor cursor3 = db.rawQuery(query3,null);


        // Column Indices of Customer table
        int firstNameColumnIndex = cursor.getColumnIndex(Customer_Entry.COLUMN_FirstName);
        int lastNameColumnIndex = cursor.getColumnIndex(Customer_Entry.COLUMN_LastName);
        int addressColumnIndex = cursor.getColumnIndex(Customer_Entry.COLUMN_Adress);
        int enterpriseColumnIndex = cursor.getColumnIndex(Customer_Entry.COLUMN_EnterpriseName);
        int emailColumnIndex =  cursor.getColumnIndex(Customer_Entry.COLUMN_Email);
        int Mob_NoColumnIndex =  cursor.getColumnIndex(Customer_Entry.COLUMN_MobileNo);
        int Phone_noColumnIndex =  cursor.getColumnIndex(Customer_Entry.COLUMN_PhoneNo);
        int Job_positionColumnIndex =  cursor.getColumnIndex(Customer_Entry.COLUMN_JobPosition);
        int Updated_byColumnIndex =  cursor.getColumnIndex(Customer_Entry.COLUMN_UpdatedBy);
        int Updated_dateColumnIndex =  cursor.getColumnIndex(Customer_Entry.COLUMN_UpdatedDate);
        int Date_CreatedColumnIndex =  cursor.getColumnIndex(Customer_Entry.COLUMN_DateCreated);
        int IsActiveColumnIndex =  cursor.getColumnIndex(Customer_Entry.COLUMN_IsActive);
        int PaymentMethodColumnIndex =  cursor.getColumnIndex(Customer_Entry.COLUMN_PaymentMethod);
        int Created_ByColumnIndex =  cursor.getColumnIndex(Customer_Entry.COLUMN_CreatedBy);

        //Column indices of Purchase invoice table
        int supplierColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_SUPPLIER);
        int PaymentDateColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int DueDateColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_DUEDATE);
        int BalanceeColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int PaidAmountDateColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int TotaleColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int StatusColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int InvoiceDateColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int RevenueColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int CreatedByColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int CreatedtDateColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int UpdatedByColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int UpdatedDateColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);
        int Is_ACtiveDateColumnIndex = cursor1.getColumnIndex(PurchaseInvoice_Entry.PURCHASE_PAYMENT_DATE);

        //Column indices of Prodect table
        int productnameColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_NAME);
        int productSKUColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_SKU);
        int productvariantColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_VARIANTS);
        int producttype_IDColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_PRODUCTTYPEID_FK);
        int productCategory_IDColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_CATEGORYID_FK_);
        int productOnhandColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_ONHAND);
        int productfulfilledColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_FULFILLED);
        int productInstockColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_INSTOCK);
        int productCreatedByColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_CREATEDBY);
        int productCreatedDateColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_CREATEDDATE);
        int productUpdatedByColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_UPDATEDBY);
        int productUpdatedDateColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_UPDATEDDATE);
        int productisactiveColumnIndex = cursor2.getColumnIndex(Product_Entry.COLUMN_PRODUCT_ISACTIVE);

        //Column indices of goodnotes table
        int good_orderID_FKColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_ORDERID_FK);
        int good_orderstatusColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_ORDERSTATUS);
        int good_delivertoColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_DELIVERTO);
        int good_warehouseColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_WAREHOUSE);
        int good_printedColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_PRINTED);
        int good_pickedColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_PICKED);
        int good_packedColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_PACKED);
        int good_shippedColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_SHIPPED);
        int good_createdbyColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_CREATEDBY);
        int good_createddatedrderIDColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_CREATEDDATE);
        int good_updatedbyColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_UPDATEDBY);
        int good_updateddateColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_UPDATEDDATE);
        int good_isactiveColumnIndex = cursor3.getColumnIndex(GoodNotes_Entry.GOODNOTES_ISACTIVE);


        //To retireve the customer table
        while (cursor.moveToNext()) {

            String firstname = cursor.getString(firstNameColumnIndex);
            String lastname = cursor.getString(lastNameColumnIndex);
            String email = cursor.getString(emailColumnIndex);
            String address = cursor.getString(addressColumnIndex);
            String enterprise = cursor.getString(enterpriseColumnIndex);
            String mobile = cursor.getString(Mob_NoColumnIndex);
            String phone = cursor.getString(Phone_noColumnIndex);
            String jobpos = cursor.getString(Job_positionColumnIndex);
            String update_by = cursor.getString(Updated_byColumnIndex);
            String updated_date = cursor.getString(Updated_dateColumnIndex);
            String date_created = cursor.getString(Date_CreatedColumnIndex);
            String is_active = cursor.getString(IsActiveColumnIndex);
            String payment_method = cursor.getString(PaymentMethodColumnIndex);
            String created_by = cursor.getString(Created_ByColumnIndex);

            result += firstname + " " + lastname + " " + email + " " + address + " " + enterprise + " " + mobile + " " + phone +
                    " " + jobpos + " " + update_by + " " + updated_date + " " + date_created + " " + is_active + " " + payment_method + " " + created_by + "\n\n";
            System.getProperty("line.separator");
        }

        //To retrieve the purchase invoice table
        while(cursor1.moveToNext()){
            String supplier = cursor1.getString(supplierColumnIndex);
            String paymentdate = cursor1.getString(PaymentDateColumnIndex);
            String duedate = cursor1.getString(DueDateColumnIndex);
            String balance = cursor1.getString(BalanceeColumnIndex);
            String paidamount = cursor1.getString(PaidAmountDateColumnIndex);
            String total = cursor1.getString(TotaleColumnIndex);
            String status = cursor1.getString(StatusColumnIndex);
            String invoice = cursor1.getString(InvoiceDateColumnIndex);
            String revenue = cursor1.getString(RevenueColumnIndex);
            String createdby = cursor1.getString(CreatedByColumnIndex);
            String createddate = cursor1.getString(CreatedtDateColumnIndex);
            String updatedby = cursor1.getString(UpdatedByColumnIndex);
            String updateddate = cursor1.getString(UpdatedDateColumnIndex);
            String isactive = cursor1.getString(Is_ACtiveDateColumnIndex);

            result += supplier + " " + paymentdate + " " + duedate + " " + balance + " " + paidamount + " " + total + " " +
                    status + " " + invoice + " " + revenue + " " + createdby + " " + createddate + " " + updatedby + " " + updateddate + " " + isactive +" \n\n" ;
            System.getProperty("line.seperator");

        }

        //To retrieve the product table data
        while(cursor2.moveToNext()){
            String productname = cursor2.getString(productnameColumnIndex);
            String productsku = cursor2.getString(productSKUColumnIndex);
            String productvariant = cursor2.getString(productvariantColumnIndex);
            String producttype = cursor2.getString(producttype_IDColumnIndex);
            String productcategory = cursor2.getString(productCategory_IDColumnIndex);
            String productonhand = cursor2.getString(productOnhandColumnIndex);
            String productfulfilled = cursor2.getString(productfulfilledColumnIndex);
            String productinstock = cursor2.getString(productInstockColumnIndex);
            String productcreatedby = cursor2.getString(productCreatedByColumnIndex);
            String productcreateddate = cursor2.getString(productCreatedDateColumnIndex);
            String productupdatedby = cursor2.getString(productUpdatedByColumnIndex);
            String productupdateddate = cursor2.getString(productUpdatedDateColumnIndex);
            String productisactive = cursor2.getString(productisactiveColumnIndex);

            result += productname + " " + productsku + " " + productvariant + " " + producttype + " " + productcategory + " " +
                    productonhand + " " + productfulfilled + " " + productinstock + " " + productcreatedby + " " + productcreateddate +
                    " " + productupdatedby + " " + productupdateddate + " " + productisactive + "\n\n ";
            System.getProperty("line.seperator");

        }

        while(cursor3.moveToNext()){
            String goodorderidfk = cursor3.getString(good_orderID_FKColumnIndex);
            String goodstatus = cursor3.getString(good_orderstatusColumnIndex);
            String gooddeliverto = cursor3.getString(good_delivertoColumnIndex);
            String goodwarehouse = cursor3.getString(good_warehouseColumnIndex);
            String goodprinted = cursor3.getString(good_printedColumnIndex);
            String goodpacked = cursor3.getString(good_packedColumnIndex);
            String goodpicked = cursor3.getString(good_pickedColumnIndex);
            String goodshipped = cursor3.getString(good_shippedColumnIndex);
            String goodcreatedby = cursor3.getString(good_createdbyColumnIndex);
            String goodcreateddate = cursor3.getString(good_createddatedrderIDColumnIndex);
            String goodupdatedby = cursor3.getString(good_updatedbyColumnIndex);
            String goodupdateddate = cursor3.getString(good_updateddateColumnIndex);
            String goodisactive = cursor3.getString(good_isactiveColumnIndex);

            result += goodorderidfk + " " + goodstatus + " " + gooddeliverto + " " + goodwarehouse +  " " + goodprinted + " " +
                    goodpacked + " " + goodpicked + " " + goodshipped + " " + goodcreatedby + " " + goodcreateddate + " " +
                    goodupdatedby + " " + goodupdateddate + " " + goodisactive + " ";
            System.getProperty("line.seperator");

        }
        cursor.close();
        cursor1.close();
        cursor2.close();
        cursor3.close();
        db.close();
        return result;
    }

    public Location getLocation(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + Locations_entry.LOCATION_TABLE_NAME + " WHERE "
              + Locations_entry.CURRENT_DATE + " = '" + date + "'";

        Log.e("DatabaseHelper", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Log.v("tempMethod","No of rows: "+c.getCount());

        int locationdate=c.getColumnIndex(Locations_entry.CURRENT_DATE);


        if (c != null)
            c.moveToFirst();


        Location td = null;
        while(c.moveToNext())
        {
            td = new Location();
            td.setId((c.getInt(c.getColumnIndex(Locations_entry.LOCATION_ID))));
            td.setLatitude((c.getString(c.getColumnIndex(Locations_entry.LOCATION_LATITUDE))));
            td.setLongitude(c.getString(c.getColumnIndex(Locations_entry.LOCATION_LONGITUDE)));
            //String x= td.getLatitude();
            //String y=td.getLongitude();

            Log.v("DatabaseHelper",td.getId()+"-"+td.getLatitude()+"-"+td.getLongitude()+"-"+c.getString(locationdate));
           //LatLng latLng= new LatLng(x,y);

            //maps.addMarker(	new MarkerOptions().position(x,y).title("Hello world"));

        }
       return td;

    }

    public void showmap( String x, String y ){


    }
}