package com.elvisoliveira.yellowpages.beans;

public class ContactBean
{

    private String Name;
    private String Address;
    private String Link;
    private String FinalString;
    private String Telephone;
    private String Id;

    public String getName()
    {
        return Name.replace(String.valueOf((char) 160), " ").trim();
    }

    public void setName(String Name)
    {
        this.Name = Name.trim();
    }

    public String getAddress()
    {
        return Address.replace(String.valueOf((char) 160), " ").trim();
    }

    public void setAddress(String Address)
    {
        this.Address = Address.trim();
    }

    public void setFinal(String FinalString)
    {
        this.FinalString = FinalString.trim();
    }
    public String getFinal()
    {
        return FinalString;
    }
    public String getLink()
    {
        return Link.replace(String.valueOf((char) 160), " ").trim();
    }

    public void setLink(String Link)
    {
        this.Link = Link.trim();
    }

    public String getTelephone()
    {
        return Telephone.trim();
    }

    public void setTelephone(String Telephone)
    {
        this.Telephone = Telephone;
    }

    public String getId()
    {
        return Id;
    }

    public void setId(String Id)
    {
        this.Id = Id;
    }

}
