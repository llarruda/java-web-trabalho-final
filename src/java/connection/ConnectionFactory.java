/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

/**
 *
 * @author Jordi.Santos
 */
public class ConnectionFactory {
    
    private static Connection conn = null;
    
    public static Connection getConnection(){
        if (conn == null){
            try{
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
    
    public static void closeConnection() {
    	if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {throw new DbException(e.getMessage());}
    	}
    }
    
    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }  catch (IOException e){
            throw new DbException(e.getMessage());
        }
    }
    
    public static void closeStatement(Statement stmt) {
    	if(stmt != null) {
            try {
                stmt.close();
                } catch (SQLException e) {
                    throw new DbException(e.getMessage());
                }
    	}
    }
    
    public static void closeResultSet(ResultSet rs) {
    	if(rs != null) {
            try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DbException(e.getMessage());
                }
    	}
    }    
    
} // fim da classe