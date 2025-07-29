package rubrica.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class PersonManager extends AbstractTableModel {
    Vector<String> columnNames = new Vector<>(List.of("Name", "Surname", "PhoneNumber"));
    FileManage fileManager;
    private Vector<Person> personList;

    public PersonManager() {
        this.fileManager = new FileManage();
        this.personList = fileManager.readFromDirectory();
        System.out.println("LEtto " + this.personList);

    }

    public void addNewPerson(Person person) {
        this.personList.add(person);
        fireTableRowsInserted(personList.size(), personList.size());
        this.fileManager.saveToDirectory(personList);
    }

    public void removePerson(Person person) {
        int index = this.personList.indexOf(person);
        this.personList.remove(person);
        fireTableRowsDeleted(index, index);
        this.fileManager.saveToDirectory(personList);
    }

    public void updatePerson(Person p1, Person p2) {
        int index = this.personList.indexOf(p1);
        this.personList.set(index, p2);
        fireTableCellUpdated(index, index);
        this.fileManager.saveToDirectory(personList);
    }

    public Vector<Person> getPersonList() {
        return this.personList;
    }

    @Override
    public int getRowCount() {
        return this.personList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
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