package com.chana.lazynight.data;

public class PlaceItem {
    String placeName;
    String placeFeel;
    Double latitude;
    Double longitude;
    String visitTime;

    public PlaceItem(){
        placeName = "";
        placeFeel = "";
        latitude = 0.0;
        longitude = 0.0;
        visitTime = "";
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceFeel() {
        return placeFeel;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setPlaceFeel(String placeFeel) {
        this.placeFeel = placeFeel;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
