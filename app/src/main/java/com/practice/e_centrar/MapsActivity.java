package com.practice.e_centrar;
import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.practice.e_centrar.DataContract.Locations_entry;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    DatabaseHelper mydb;
    SQLiteDatabase db;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){

            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED);{
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10,5,locationListener);
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    int count=0;
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        DatabaseHelper helper = new DatabaseHelper(this);
        db = helper.getWritableDatabase();
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng userlocation = new LatLng(-31,151);
                mMap.addMarker(new MarkerOptions().position(userlocation).title("Current Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userlocation));

                if(count!=0)
                {
                    insert_into_locations(location,googleMap);
                }

                Toast.makeText(MapsActivity.this,"Location changed", Toast.LENGTH_LONG).show();
                count=1;
                LatLng userSlocation = new LatLng(location.getLatitude(),location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(userSlocation).title("Current Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(userSlocation));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if(Build.VERSION.SDK_INT<23){
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,0,locationListener);
        }
        else {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 0, locationListener);

                Location Lastknownlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (Lastknownlocation != null) {
                    LatLng userlocation = new LatLng(Lastknownlocation.getLatitude(), Lastknownlocation.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(userlocation).title("Marker in Sydney"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(userlocation));
                }
            }

        }

        // Add a marker in Sydney and move the camera

    }

    public void insert_into_locations(Location location, GoogleMap googleMap){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = sdf.format(new Date());

        ContentValues contentValues = new ContentValues();
        contentValues.put(Locations_entry.LOCATION_LONGITUDE,location.getLongitude());
        Log.v("MapsActivivity",location.getLongitude()+"");
        contentValues.put(Locations_entry.LOCATION_LATITUDE,location.getLatitude());
        Log.v("MapsActivivity",location.getLatitude()+"");
        contentValues.put(Locations_entry.LOCATION_ZOOM,googleMap.getCameraPosition().zoom+"");
        Log.v("MapsActivivity",googleMap.getCameraPosition().zoom+"");
        contentValues.put(Locations_entry.CURRENT_DATE, strDate );
        Log.v("MapsActivivity",strDate+"");
        db.insert(Locations_entry.LOCATION_TABLE_NAME,null,contentValues);
    }

}
