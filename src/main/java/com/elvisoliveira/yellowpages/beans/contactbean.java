package com.elvisoliveira.yellowpages.beans;

public class contactbean {

    private String Name;
    private String Address;
    private String Link;

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name.trim();
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address.trim();
    }

    /**
     * @return the Link
     */
    public String getLink() {
        return Link;
    }

    /**
     * @param Link the Link to set
     */
    public void setLink(String Link) {
        this.Link = Link.trim();
    }

}
