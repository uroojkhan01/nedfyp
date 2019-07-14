package com.practice.e_centrar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetProductFromAPI extends AppCompatActivity {

    SQLiteDatabase db;
    DatabaseHelper mydb;
    TextView textview_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_product_from_api);

        mydb = new DatabaseHelper(this);
        // textview_result = (TextView) findViewById(R.id.ProducttextView);
    }

        public void gotogrid(View view){
        Intent intent = new Intent(GetProductFromAPI.this,ProductGridViewActivity.class);
        startActivity(intent);

        }

        public void images(View view){
            Intent intent = new Intent(GetProductFromAPI.this,imagesproductActivity.class);
            startActivity(intent);
        }

        public void deleteproduct(View view){
            db= mydb.getReadableDatabase();
            mydb = new DatabaseHelper(this);
            String deleter = " delete from " + DataContract.Product_Entry.PRODUCT_TABLE_NAME;
            db.execSQL(deleter);
        }

        public void getproduct(View view){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataContract.variablesentry.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonPlaceHolderApi = retrofit.create(JsonApi.class);
        Call<List<Product>> call = jsonPlaceHolderApi.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()) {
                    textview_result.setText("Code:" + response.code());
                    return;
                }

                List<Product> product = response.body();
                db = mydb.getWritableDatabase();
                ContentValues cv = new ContentValues();
                for (Product products : product) {

                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCTAPI_ID, products.getId());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_NAME, products.getProductName());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_IMAGE, products.getProductImage());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_CATEGORYID_FK_, products.getProductCategoryIdFk());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_PRODUCTTYPEID_FK, products.getProductTypeIdFk());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_INSTOCK, products.getInstock());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_FULFILLED, products.getFullfilled());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_ONHAND, products.getOnHand());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_SKU, products.getSku());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_VARIANTS, products.getVariants());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_CREATEDBY, products.getCreatedBy());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_CREATEDDATE, products.getCreatedDate());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_UPDATEDBY, products.getUpdatedBy());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_UPDATEDDATE, products.getUpdatedDate());
                    cv.put(DataContract.Product_Entry.COLUMN_PRODUCT_ISACTIVE, products.getIsActive());
                    cv.put(DataContract.Product_Entry.COLUMN_UNITPRICE, products.getUnitPrice());

                    db.insert(DataContract.Product_Entry.PRODUCT_TABLE_NAME, null, cv);

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                textview_result.setText(t.getMessage());
            }
        });

    }

    public void JFunction(View view) {

        String result = "";
        String query2 = "Select * FROM " + DataContract.Product_Entry.PRODUCT_TABLE_NAME;
        SQLiteDatabase db = mydb.getReadableDatabase();
        Cursor cursor2 = db.rawQuery(query2, null);

        int productnameColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_NAME);
        int productSKUColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_SKU);
        int productvariantColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_VARIANTS);
        int producttype_IDColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_PRODUCTTYPEID_FK);
        int productCategory_IDColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_CATEGORYID_FK_);
        int productOnhandColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_ONHAND);
        int productfulfilledColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_FULFILLED);
        int productInstockColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_INSTOCK);
        int productCreatedByColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_CREATEDBY);
        int productCreatedDateColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_CREATEDDATE);
        int productUpdatedByColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_UPDATEDBY);
        int productUpdatedDateColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_UPDATEDDATE);
        int productisactiveColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_ISACTIVE);
        int unitpriseColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_UNITPRICE);
        int ProdutIDColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCTAPI_ID);
        int productimageColumnIndex = cursor2.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_IMAGE);

        while (cursor2.moveToNext()) {
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
            String unitprice = cursor2.getString(unitpriseColumnIndex);
            String id = cursor2.getString(ProdutIDColumnIndex);
            String image = cursor2.getString(productimageColumnIndex);

            result += productname + " " + productsku + " " + productvariant + " " + producttype + " " + productcategory + " " +
                    productonhand + " " + productfulfilled + " " + productinstock + " " + productcreatedby + " " + productcreateddate +
                    " " + productupdatedby + " " + productupdateddate + " " + productisactive + " " + unitprice + " " + id + " " + image + "\n\n ";
            System.getProperty("line.seperator");

        }

        textview_result.setText(result);
    }

}