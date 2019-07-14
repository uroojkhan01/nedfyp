package com.practice.e_centrar;

import com.google.gson.JsonArray;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

import com.practice.e_centrar.MainActivity;

import org.json.JSONArray;

import java.util.List;

public interface JsonApi {

    @Headers({
            "Content-Type: application/json"})

    @PUT("Add")
    //public void createUser(@Body JsonArray array);
    Call<JSONArray> createUser(
        @Body JSONArray array);


    @FormUrlEncoded
    @POST("Data/Create")
    Call<ResponseBody> send(
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("col_current_date") String col_current_date
    );

    @GET("Data")
    Call<List<Data>> getdata();

    @GET("Customer")
    Call<List<Customer>> getcustomers();

    @GET("Product/GetAll")
    Call<List<Product>> getProducts();
}
