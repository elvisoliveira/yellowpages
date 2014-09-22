package com.elvisoliveira.yellowpages.DAO;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactModel
{

    private String message;

    public void getAll()
    {
        try
        {
            SQLite db = new SQLite();
            try (ResultSet rs = db.stm.executeQuery("SELECT * FROM contacts"))
            {
                while (rs.next())
                {
                    System.out.println(rs.getString("name"));
                }
            }
        }
        catch (SQLException | ClassNotFoundException e)
        {
        }
    }

    public Boolean setContact(ContactBean contact)
    {

        try
        {

            String sql = "INSERT INTO \"main\".\"contacts\" (\"id_telelistas\",   "
                         + "                                   \"name\",          "
                         + "                                   \"telephone\",     "
                         + "                                   \"address\",       "
                         + "                                   \"other\")         "
                         + "   VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\");       ";

            String query = String.format(sql,
                                         contact.getId(),
                                         contact.getName(),
                                         contact.getTelephone(),
                                         contact.getAddress(),
                                         contact.getLink());

            SQLite db = new SQLite();

            Integer rs = db.stm.executeUpdate(query);

            return true;

        }
        catch (SQLException | ClassNotFoundException ex)
        {

            this.setMessage(ex.getMessage());

            return false;
        }
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
