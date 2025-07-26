package rubrica.model;

import java.util.Vector;
import java.util.function.Predicate;

public class PersonManager {
    private Vector<Person> personList;
    public PersonManager() {
        this.personList = new Vector<>();
    }
    public boolean addNewPerson(Person person) {
        return this.personList.add(person);
    }
    public boolean removePerson(Person person) {
        return this.personList.remove(person);
    }
    public void updatePerson(Person p1, Person p2) {
        this.personList.set(this.personList.indexOf(p1), p2);
    }
}