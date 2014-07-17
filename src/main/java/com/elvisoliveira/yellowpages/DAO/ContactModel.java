package com.elvisoliveira.yellowpages.DAO;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactModel {

    public void getAll() {
        try {
            SQLite db = new SQLite();
            try (ResultSet rs = db.stm.executeQuery("SELECT * FROM contacts")) {
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
    }
    
    public static void setContact(ContactBean contact) {
        String sql = "INSERT INTO \"main\".\"contacts\" (\"id_telelistas\", \"name\", \"telephone\", \"address\", \"other\") VALUES (\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")";
        String query = String.format(sql, contact.getId(), contact.getName(), contact.getTelephone(), contact.getAddress(), contact.getLink());

        try {
            SQLite db = new SQLite();
            int rs = db.stm.executeUpdate(query);
        } catch (SQLException | ClassNotFoundException ex) {
            
        }
    }
}
