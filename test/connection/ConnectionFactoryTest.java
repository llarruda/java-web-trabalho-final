/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author llarruda
 */
public class ConnectionFactoryTest {
    
    @Test
    public void ConnectionFactoryTest() {
        Connection con = new ConnectionFactory().getConnection();
        try {
            System.out.println(con.getAutoCommit());
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
