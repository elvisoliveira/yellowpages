package com.elvisoliveira.yellowpages.beans;

import java.util.ArrayList;

public class GeocodeBean {

    private String status;
    private ArrayList<ArrayList> results_address_components;
    private String results_formatted_address;
    private String results_geometry;
    private String results_geometry_location;
    private String results_geometry_location_lat;
    private String results_geometry_location_lng;
    private String results_geometry_location_type;
    private String results_geometry_viewport;
    private String results_geometry_viewport_northeast;
    private String results_geometry_viewport_northeast_lat;
    private String results_geometry_viewport_northeast_lng;
    private String results_geometry_viewport_southwest;
    private String results_geometry_viewport_southwest_lat;
    private String results_geometry_viewport_southwest_lng;
    private String results_types;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ArrayList> getResults_address_components() {
        return results_address_components;
    }

    public void setResults_address_components(ArrayList<ArrayList> results_address_components) {
        this.results_address_components = results_address_components;

    }

    public String getResults_formatted_address() {
        return results_formatted_address;
    }

    public void setResults_formatted_address(String results_formatted_address) {
        this.results_formatted_address = results_formatted_address;
    }

    public String getResults_geometry() {
        return results_geometry;
    }

    public void setResults_geometry(String results_geometry) {
        this.results_geometry = results_geometry;
    }

    public String getResults_geometry_location() {
        return results_geometry_location;
    }

    public void setResults_geometry_location(String results_geometry_location) {
        this.results_geometry_location = results_geometry_location;
    }

    public String getResults_geometry_location_lat() {
        return results_geometry_location_lat;
    }

    public void setResults_geometry_location_lat(String results_geometry_location_lat) {
        this.results_geometry_location_lat = results_geometry_location_lat;
    }

    public String getResults_geometry_location_lng() {
        return results_geometry_location_lng;
    }

    public void setResults_geometry_location_lng(String results_geometry_location_lng) {
        this.results_geometry_location_lng = results_geometry_location_lng;
    }

    public String getResults_geometry_location_type() {
        return results_geometry_location_type;
    }

    public void setResults_geometry_location_type(String results_geometry_location_type) {
        this.results_geometry_location_type = results_geometry_location_type;
    }

    public String getResults_geometry_viewport() {
        return results_geometry_viewport;
    }

    public void setResults_geometry_viewport(String results_geometry_viewport) {
        this.results_geometry_viewport = results_geometry_viewport;
    }

    public String getResults_geometry_viewport_northeast() {
        return results_geometry_viewport_northeast;
    }

    public void setResults_geometry_viewport_northeast(String results_geometry_viewport_northeast) {
        this.results_geometry_viewport_northeast = results_geometry_viewport_northeast;
    }

    public String getResults_geometry_viewport_northeast_lat() {
        return results_geometry_viewport_northeast_lat;
    }

    public void setResults_geometry_viewport_northeast_lat(String results_geometry_viewport_northeast_lat) {
        this.results_geometry_viewport_northeast_lat = results_geometry_viewport_northeast_lat;
    }

    public String getResults_geometry_viewport_northeast_lng() {
        return results_geometry_viewport_northeast_lng;
    }

    public void setResults_geometry_viewport_northeast_lng(String results_geometry_viewport_northeast_lng) {
        this.results_geometry_viewport_northeast_lng = results_geometry_viewport_northeast_lng;
    }

    public String getResults_geometry_viewport_southwest() {
        return results_geometry_viewport_southwest;
    }

    public void setResults_geometry_viewport_southwest(String results_geometry_viewport_southwest) {
        this.results_geometry_viewport_southwest = results_geometry_viewport_southwest;
    }

    public String getResults_geometry_viewport_southwest_lat() {
        return results_geometry_viewport_southwest_lat;
    }

    public void setResults_geometry_viewport_southwest_lat(String results_geometry_viewport_southwest_lat) {
        this.results_geometry_viewport_southwest_lat = results_geometry_viewport_southwest_lat;
    }

    public String getResults_geometry_viewport_southwest_lng() {
        return results_geometry_viewport_southwest_lng;
    }

    public void setResults_geometry_viewport_southwest_lng(String results_geometry_viewport_southwest_lng) {
        this.results_geometry_viewport_southwest_lng = results_geometry_viewport_southwest_lng;
    }

    public String getResults_types() {
        return results_types;
    }

    public void setResults_types(String results_types) {
        this.results_types = results_types;
    }

}
