package com.elvisoliveira.yellowpages.DAO;

import java.sql.*;

public class SQLite
{

    private final Connection conn;
    public final Statement stm;

    public SQLite() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        this.conn = DriverManager.getConnection("jdbc:sqlite:yellowpages.sqlite");
        this.stm = this.conn.createStatement();
    }

}
