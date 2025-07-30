package rubrica.db;

import java.sql.Connection;

public interface DbConnection {

    Connection getConnection();

}