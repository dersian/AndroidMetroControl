package com.example.multimediaproject;

public class CSVDataSample {
    private double longitude;
    private double latitude;
    private String station;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "StationSample{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", station='" + station + '\'' +
                '}';
    }
}
