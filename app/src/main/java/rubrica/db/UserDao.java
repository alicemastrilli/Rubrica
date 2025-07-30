package rubrica.db;

import java.util.Vector;

import rubrica.model.User;

public interface UserDao {
    public Vector<User> getAllUsers();
}
