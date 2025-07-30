package rubrica.controller.impl;


import java.lang.reflect.InvocationTargetException;

import rubrica.controller.PersonController;
import rubrica.model.PersonManager;
import rubrica.view.PersonView;

public class PersonControllerImpl implements PersonController {

    PersonManager model;
    PersonView view;

    public PersonControllerImpl(PersonManager model, PersonView view) {
        this.model = model;
        this.view = view;
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void addNewPerson() {
        try {
            new PersonEditorControllerImpl(model, -1);
        } catch (InvocationTargetException | InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void modifyPerson(int row) {
        if (row < 0) {
            this.view.showErrorDialog("Per modificare devi prima selezionare una persona");
        } else {
            try {
                new PersonEditorControllerImpl(model, row);
            } catch (InvocationTargetException | InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onDeleteButtonClicked(int row) {
        if (row < 0) {
            this.view.showErrorDialog("Per eliminare devi prima selezionare una persona");
        } else {
            this.view.showConfirmOnDelete("Sei sicuro di voler eliminare " +
                    this.model.getPersonList().get(row).getName() + " "
                    + this.model.getPersonList().get(row).getSurname() + "?");
        }
    }

    @Override
    public void removePerson(int row) {
        model.removePerson(this.model.getPersonList().get(row));
    }

}
