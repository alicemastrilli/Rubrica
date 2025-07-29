package rubrica.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import rubrica.gui.PersonView;
import rubrica.model.Person;
import rubrica.model.PersonManager;

public class PersonController{

    PersonManager model;
    PersonView view;
    Vector<Person> persons; 

    public PersonController(PersonManager model, PersonView view) {
        this.model = model;
        this.view = view;
        this.view.setObserver(this);
        this.view.start();
    }

    public void addNewPerson() {
        try {
            new PersonEditorController(model, -1);
        } catch (InvocationTargetException | InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void modifyPerson(int row) {
        if (row < 0) {
            this.view.showErrorDialog("Per modificare devi prima selezionare una persona");
        } else {
            try {
                new PersonEditorController(model, row);
            } catch (InvocationTargetException | InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void onDeleteButtonClicked(int row) {
        if (row < 0) {
            this.view.showErrorDialog("Per eliminare devi prima selezionare una persona");
        } else {
            this.view.showConfirmOnDelete("Sei sicuro di voler eliminare " +
            this.persons.get(row).getName() + " " + this.persons.get(row).getSurname() + "?");
        }
    }
    public void removePerson(int row) {
        model.removePerson(this.persons.get(row));
    }
    
    }



