package rubrica.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;

import rubrica.db.PersonDao;
import rubrica.model.Person;

public class PersonDaoImpl implements PersonDao {
    private Connection connection;

    public PersonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO person "
                            + "(Name, Surname, Address, PhoneNumber, Age) "
                            + "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, person.getName());
            statement.setString(2, person.getSurname());
            statement.setString(3, person.getAddress());
            statement.setString(4, person.getPhoneNumber());
            if (person.getAge() == null) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, person.getAge());
            }

            int rowsAffedcted = statement.executeUpdate();

            if (rowsAffedcted > 0) {
                ResultSet result = statement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    person.setId(id);
                }
                result.close();
            }
            statement.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("Error inserting person", e);
        }
    }

    @Override
    public void update(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE person "
                            + "SET Name = ?, Surname = ?, Address = ?, PhoneNumber = ?, Age = ? "
                            + "WHERE Id = ?");
            statement.setString(1, person.getName());
            statement.setString(2, person.getSurname());
            statement.setString(3, person.getAddress());
            statement.setString(4, person.getPhoneNumber());
            if (person.getAge() == null) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, person.getAge());
            }
            statement.setInt(6, person.getId());
            statement.executeUpdate();

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating person", e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM person WHERE Id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting person", e);
        }
    }

    @Override
    public Vector<Person> getAll() {
        Vector<Person> persons = new Vector<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT person.* FROM person");
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                int ageValue = result.getInt("Age");
                Integer age = result.wasNull() ? null : ageValue;
                Person p = new Person(
                        result.getInt("Id"),
                        result.getString("Name"),
                        result.getString("Surname"),
                        result.getString("Address"),
                        result.getString("PhoneNumber"),
                        age);
                persons.add(p);
            }
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error getting persons", e);
        }
        return persons;
    }

}
