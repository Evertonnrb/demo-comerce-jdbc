package br.com.tests;

import br.com.model.dao.DB;
import br.com.model.dao.DaoFactory;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    @Test
    public void testDeveConectarAoDB() throws SQLException {
        Connection con = DB.getConnection();
        System.out.println(con);
        con.close();

    }
}
