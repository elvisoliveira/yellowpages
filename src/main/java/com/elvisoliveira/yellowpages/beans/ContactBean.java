package com.elvisoliveira.yellowpages.beans;

public class ContactBean {

    private String Name;
    private String Address;
    private String Link;

    public String getName() {
        return Name.replace(String.valueOf((char) 160), " ").trim();
    }

    public void setName(String Name) {
        this.Name = Name.trim();
    }

    public String getAddress() {
        return Address.replace(String.valueOf((char) 160), " ").trim();
    }

    public void setAddress(String Address) {
        this.Address = Address.trim();
    }

    public String getLink() {
        return Link.replace(String.valueOf((char) 160), " ").trim();
    }

    public void setLink(String Link) {
        this.Link = Link.trim();
    }

}
