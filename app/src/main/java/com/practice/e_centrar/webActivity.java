package com.practice.e_centrar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class webActivity extends AppCompatActivity {

    EditText Lat, Longi, Dati;
    TextView Getdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Longi = (EditText) findViewById(R.id.lat);
        Lat = (EditText) findViewById(R.id.longi);
        Dati = (EditText) findViewById(R.id.date);
        Getdata = (TextView) findViewById(R.id.getdata);
    }

        public void add (View view){

        String longitude = Longi.getText().toString();
        String latitude = Lat.getText().toString();
        String col_current_date= Dati.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.16/api/Data/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonApi jsonPlaceHolderApi = retrofit.create(JsonApi.class);
            Call<ResponseBody> call = jsonPlaceHolderApi.send(latitude,longitude,col_current_date);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    try {
                        String s = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(webActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
