package com.elvisoliveira.yellowpages.beans;

public class ContactBean {

    private String Name;
    private String Address;
    private String Link;
    private String Telephone;

    /**
     * @return the Name
     */
    public String getName() {
        return Name.replace(String.valueOf((char) 160), " ").trim();
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
        return Address.replace(String.valueOf((char) 160), " ").trim();
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
        return Link.replace(String.valueOf((char) 160), " ").trim();
    }

    /**
     * @param Link the Link to set
     */
    public void setLink(String Link) {
        this.Link = Link.trim();
    }

    /**
     * @return the Telephone
     */
    public String getTelephone() {
        return Telephone;
    }

    /**
     * @param Telephone the Telephone to set
     */
    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

}
