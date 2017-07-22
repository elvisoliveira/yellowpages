package com.elvisoliveira.yellowpages.beans;

public class LocationBean {

    private String locationName;
    private Integer locationID;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    @Override
    public String toString() {
        return this.locationName;
    }
}
