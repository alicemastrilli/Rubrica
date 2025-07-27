package rubrica.model;

import java.util.List;
import java.util.Vector;
import java.util.function.Predicate;

import javax.swing.table.AbstractTableModel;

public class PersonManager extends AbstractTableModel{
    Vector<String> columnNames = new Vector<>(List.of("Name", "Surname", "PhoneNumber" ));

    private Vector<Person> personList;
    public PersonManager() {
        
        Person p1 = new Person("ali", "mas", "via g.", "3926", 25);
        Person p2 = new Person("fra", "diste", "via g.", "3926", 25);
        this.personList = new Vector<>(List.of(p1,p2));

    }
    public void addNewPerson(Person person) {
        this.personList.add(person);
        fireTableRowsInserted(personList.size(), personList.size());
    }
    public void removePerson(Person person) {
        int index = this.personList.indexOf(person);
        this.personList.remove(person);
        fireTableRowsDeleted(index, index);
    }
    public void updatePerson(Person p1, Person p2) {
        int index = this.personList.indexOf(p1);
        this.personList.set(index, p2);
        fireTableCellUpdated(index, index);        
    }

    public Vector<Person> getPersonList() {
        return this.personList;
    }
    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.personList.size();
    }
    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.size();
    }
    @Override
    public String getColumnName(int column) {
        return this.columnNames.get(column);
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        switch (columnIndex) {
            case 0:
                return this.personList.get(rowIndex).getName();
            case 1: 
                return this.personList.get(rowIndex).getSurname();
            case 2: 
                return this.personList.get(rowIndex).getPhoneNumber();
            default:
                return new Exception();
        }
    }
}