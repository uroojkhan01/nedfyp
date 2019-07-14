package com.practice.e_centrar;
import com.practice.e_centrar.DataContract.Locations_entry;
public class Location{

    int id;
    String longitude;
    String latitude;
    // constructors
    public Location() {

    }

    public Location(String Long, String lat) {
        this.latitude = lat;
        this.longitude = Long;
    }

    public Location(int id, String Long, String lat) {
        this.id = id;
        this.longitude = Long;
        this.latitude = lat;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setLongitude(String Long) {
        this.longitude = Long;
    }

    public void setLatitude(String lat) {
        this.latitude = lat;
    }


    // getters
    public long getId() {
        return this.id;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    }

