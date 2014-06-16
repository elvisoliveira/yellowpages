package com.elvisoliveira.yellowpages.beans.geocode;

import java.util.ArrayList;

public class AddressComponents {

    private String longname;
    private String shortname;
    private ArrayList<String> types;

    public String getLongname() {
        return longname;
    }

    public void setLongname(String longname) {
        this.longname = longname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

}
