package rubrica.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import rubrica.db.UserDao;
import rubrica.model.User;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Vector<User> getAllUsers() {
        Vector<User> users = new Vector<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT utente.* FROM utente");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                User u = new User(
                        result.getString("Username"),
                        result.getString("Password"));
                        
                users.add(u);
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error getting persons", e);
        }
        return users;
    }

}
