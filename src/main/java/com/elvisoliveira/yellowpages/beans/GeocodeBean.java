/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvisoliveira.yellowpages.beans;

import java.util.ArrayList;

/**
 *
 * @author root
 */
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

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the results_address_components
     */
    public ArrayList<ArrayList> getResults_address_components() {
        return results_address_components;
    }

    /**
     * @param results_address_components the results_address_components to set
     */
    public void setResults_address_components(ArrayList<ArrayList> results_address_components) {
        this.results_address_components = results_address_components;

    }
    /**
     * @return the results_formatted_address
     */
    public String getResults_formatted_address() {
        return results_formatted_address;
    }

    /**
     * @param results_formatted_address the results_formatted_address to set
     */
    public void setResults_formatted_address(String results_formatted_address) {
        this.results_formatted_address = results_formatted_address;
    }

    /**
     * @return the results_geometry
     */
    public String getResults_geometry() {
        return results_geometry;
    }

    /**
     * @param results_geometry the results_geometry to set
     */
    public void setResults_geometry(String results_geometry) {
        this.results_geometry = results_geometry;
    }

    /**
     * @return the results_geometry_location
     */
    public String getResults_geometry_location() {
        return results_geometry_location;
    }

    /**
     * @param results_geometry_location the results_geometry_location to set
     */
    public void setResults_geometry_location(String results_geometry_location) {
        this.results_geometry_location = results_geometry_location;
    }

    /**
     * @return the results_geometry_location_lat
     */
    public String getResults_geometry_location_lat() {
        return results_geometry_location_lat;
    }

    /**
     * @param results_geometry_location_lat the results_geometry_location_lat to
     * set
     */
    public void setResults_geometry_location_lat(String results_geometry_location_lat) {
        this.results_geometry_location_lat = results_geometry_location_lat;
    }

    /**
     * @return the results_geometry_location_lng
     */
    public String getResults_geometry_location_lng() {
        return results_geometry_location_lng;
    }

    /**
     * @param results_geometry_location_lng the results_geometry_location_lng to
     * set
     */
    public void setResults_geometry_location_lng(String results_geometry_location_lng) {
        this.results_geometry_location_lng = results_geometry_location_lng;
    }

    /**
     * @return the results_geometry_location_type
     */
    public String getResults_geometry_location_type() {
        return results_geometry_location_type;
    }

    /**
     * @param results_geometry_location_type the results_geometry_location_type
     * to set
     */
    public void setResults_geometry_location_type(String results_geometry_location_type) {
        this.results_geometry_location_type = results_geometry_location_type;
    }

    /**
     * @return the results_geometry_viewport
     */
    public String getResults_geometry_viewport() {
        return results_geometry_viewport;
    }

    /**
     * @param results_geometry_viewport the results_geometry_viewport to set
     */
    public void setResults_geometry_viewport(String results_geometry_viewport) {
        this.results_geometry_viewport = results_geometry_viewport;
    }

    /**
     * @return the results_geometry_viewport_northeast
     */
    public String getResults_geometry_viewport_northeast() {
        return results_geometry_viewport_northeast;
    }

    /**
     * @param results_geometry_viewport_northeast the
     * results_geometry_viewport_northeast to set
     */
    public void setResults_geometry_viewport_northeast(String results_geometry_viewport_northeast) {
        this.results_geometry_viewport_northeast = results_geometry_viewport_northeast;
    }

    /**
     * @return the results_geometry_viewport_northeast_lat
     */
    public String getResults_geometry_viewport_northeast_lat() {
        return results_geometry_viewport_northeast_lat;
    }

    /**
     * @param results_geometry_viewport_northeast_lat the
     * results_geometry_viewport_northeast_lat to set
     */
    public void setResults_geometry_viewport_northeast_lat(String results_geometry_viewport_northeast_lat) {
        this.results_geometry_viewport_northeast_lat = results_geometry_viewport_northeast_lat;
    }

    /**
     * @return the results_geometry_viewport_northeast_lng
     */
    public String getResults_geometry_viewport_northeast_lng() {
        return results_geometry_viewport_northeast_lng;
    }

    /**
     * @param results_geometry_viewport_northeast_lng the
     * results_geometry_viewport_northeast_lng to set
     */
    public void setResults_geometry_viewport_northeast_lng(String results_geometry_viewport_northeast_lng) {
        this.results_geometry_viewport_northeast_lng = results_geometry_viewport_northeast_lng;
    }

    /**
     * @return the results_geometry_viewport_southwest
     */
    public String getResults_geometry_viewport_southwest() {
        return results_geometry_viewport_southwest;
    }

    /**
     * @param results_geometry_viewport_southwest the
     * results_geometry_viewport_southwest to set
     */
    public void setResults_geometry_viewport_southwest(String results_geometry_viewport_southwest) {
        this.results_geometry_viewport_southwest = results_geometry_viewport_southwest;
    }

    /**
     * @return the results_geometry_viewport_southwest_lat
     */
    public String getResults_geometry_viewport_southwest_lat() {
        return results_geometry_viewport_southwest_lat;
    }

    /**
     * @param results_geometry_viewport_southwest_lat the
     * results_geometry_viewport_southwest_lat to set
     */
    public void setResults_geometry_viewport_southwest_lat(String results_geometry_viewport_southwest_lat) {
        this.results_geometry_viewport_southwest_lat = results_geometry_viewport_southwest_lat;
    }

    /**
     * @return the results_geometry_viewport_southwest_lng
     */
    public String getResults_geometry_viewport_southwest_lng() {
        return results_geometry_viewport_southwest_lng;
    }

    /**
     * @param results_geometry_viewport_southwest_lng the
     * results_geometry_viewport_southwest_lng to set
     */
    public void setResults_geometry_viewport_southwest_lng(String results_geometry_viewport_southwest_lng) {
        this.results_geometry_viewport_southwest_lng = results_geometry_viewport_southwest_lng;
    }

    /**
     * @return the results_types
     */
    public String getResults_types() {
        return results_types;
    }

    /**
     * @param results_types the results_types to set
     */
    public void setResults_types(String results_types) {
        this.results_types = results_types;
    }

}
