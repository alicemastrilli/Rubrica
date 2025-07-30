package rubrica.db.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import rubrica.db.DbConnection;


public class DbConnectionImpl implements DbConnection {
    private final static String DRIVER = "jdbc:mysql://";
    Properties prop = null;
    Connection conn;
    public DbConnectionImpl() {
        prop = new Properties();
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("credenziali_database.properties");
        if (inputStream != null) {
            try {
                prop.load(inputStream);
                String user = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");
                String host = prop.getProperty("db.host");
                String port = prop.getProperty("db.port");
                String dbName = prop.getProperty("db.name");
                String url = DRIVER + host + ":"+ port + "/" + dbName;
                this.conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connesso a "+ conn.getMetaData().getURL());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
               
                e.printStackTrace();
            }
        }
    }
    @Override
    public Connection getConnection() {
        return this.conn;
    }

}
