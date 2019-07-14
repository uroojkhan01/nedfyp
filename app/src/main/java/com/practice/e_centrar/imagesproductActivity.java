package com.practice.e_centrar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class imagesproductActivity extends AppCompatActivity {

    String initial = "http://192.168.1.17";
    String date = "Nesfruta" ;
    SQLiteDatabase db;
    DatabaseHelper mydb;
    ImageView getImageView;
    String gif,newgif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagesproduct);
        mydb = new DatabaseHelper(this);
        getImageView = (ImageView)findViewById(R.id.imageView2);
    }

    public void image(View view){
        SQLiteDatabase db = mydb.getWritableDatabase();
        String selectQuery = "SELECT " + DataContract.Product_Entry.COLUMN_PRODUCT_IMAGE + " FROM " + DataContract.Product_Entry.PRODUCT_TABLE_NAME + " WHERE "
                + DataContract.Product_Entry.COLUMN_PRODUCT_NAME + " = '" + date + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.v("gif",cursor.toString());

        int column = cursor.getColumnIndex(DataContract.Product_Entry.COLUMN_PRODUCT_IMAGE);
         cursor.moveToFirst();

         while (cursor.moveToNext()){
             gif = cursor.getString(column);
        }

        newgif = initial.concat(gif);
        Log.v("imagelog",newgif);

        Picasso.with(this).load(newgif).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(500, 200)
                .into(getImageView, new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {

                        Bitmap bitmap = ((BitmapDrawable) getImageView.getDrawable()).getBitmap();
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

    }
}
