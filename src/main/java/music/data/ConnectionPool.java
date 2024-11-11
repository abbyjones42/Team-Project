/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music.data;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author jared
 */
public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/music");
            if (dataSource == null) {
                System.err.println("DataSource lookup failed: dataSource is null.");
            }
        } catch (NamingException e) {
            System.err.println("NamingException during DataSource lookup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            if (dataSource == null) {
                System.err.println("DataSource is null. Cannot obtain a connection.");
                return null;
            }
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("SQLException during getConnection: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            System.err.println("SQLException during freeConnection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}