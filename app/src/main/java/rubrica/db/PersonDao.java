package rubrica.db;

import java.util.Vector;

import rubrica.model.Person;

public interface PersonDao {

    void insert(Person person);

    void update(Person person);

    void deleteById(Integer id);

    Vector<Person> getAll();

}