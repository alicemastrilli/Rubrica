package rubrica.controller;

public interface PersonController {

    void addNewPerson();

    void modifyPerson(int row);

    void onDeleteButtonClicked(int row);

    void removePerson(int row);

}