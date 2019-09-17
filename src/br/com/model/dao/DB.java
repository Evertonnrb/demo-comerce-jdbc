package br.com.model.dao;

import br.com.controller.excpetions.BDException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection con = null;

    public static Connection getConnection() {
        if (con == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("url");
                con = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new BDException(e.getMessage());
            }
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new BDException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream inputStream = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(inputStream);
            return props;
        } catch (IOException e) {
            throw new BDException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new BDException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new BDException(e.getMessage());
            }
        }
    }

}
