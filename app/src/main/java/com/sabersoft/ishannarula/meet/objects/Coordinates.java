package com.sabersoft.ishannarula.meet.objects;


public class Coordinates {

    private double latitude;
    private double longitude;
    private int counter;

    public Coordinates(double latitude, double longitude, int counter){
        this.latitude = latitude;
        this.longitude = longitude;
        this.counter = counter;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
