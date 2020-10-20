/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Junior
 */
public class ConnectionFactory {
    public static Connection getConnection() {

        try {
//            try {
//                
//                Class.forName("com.mysql.cj.jdbc.Driver");
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
//            }
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            return DriverManager.getConnection("jdbc:mysql://localhost/javaweb?serverTimezone=UTC&useSSL=false", "root", "banco123");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
