package rubrica.db.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import rubrica.db.DbConnection;

public class DbConnectionImpl implements DbConnection {
    private final static String DRIVER = "jdbc:mysql://";
    private final static String PROPERTIES_FILE = "credenziali_database.properties";
    private Properties prop = null;
    private Connection conn;

    public DbConnectionImpl() {
        prop = new Properties();
        InputStream inputStream;
        try {
            File file = new File(PROPERTIES_FILE);
            inputStream = file.exists() ? new FileInputStream(file) : getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE) ;
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
        }  catch (IOException e) {
            throw new RuntimeException("Errore caricando le credenziali del database", e);
        }
    }

    

    @Override
    public Connection getConnection() {
        return this.conn;
    }

}
