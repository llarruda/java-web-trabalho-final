/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author llarruda
 */
public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            Properties prop = new Properties();
            Thread currentThread = Thread.currentThread();
            ClassLoader contextClassLoader = currentThread.getContextClassLoader();
            InputStream propertiesStream = contextClassLoader.getResourceAsStream("db/db.properties");
            if (propertiesStream != null) {           
                prop.load(propertiesStream);
                propertiesStream.close();
                String dbDriver = prop.getProperty("db.driver");
                String dbUrl = prop.getProperty("db.url");
                String dbUser = prop.getProperty("db.user");
                String dbPwd = prop.getProperty("db.pwd");
                Class.forName(dbDriver);
                // @DEBUG
                // System.out.printf("URL = %s\nUser = %s\nPassword = %s\n",
                //    dbUrl, dbUser, dbPwd);
                Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
                con.setAutoCommit(false);
                return con;
            } else {
                System.out.println("propertiesStream está retornando null");
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
