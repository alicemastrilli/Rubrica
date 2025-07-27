package rubrica.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.checkerframework.checker.units.qual.m;

import rubrica.gui.PersonEditorGUI;
import rubrica.gui.PersonView;
import rubrica.model.Person;
import rubrica.model.PersonManager;

public class PersonController implements ActionListener{

    PersonManager model;
    PersonView view;
    Vector<Person> persons; 
    JTable table;
    JButton newBtn;
    JButton modifyBtn;
    JButton deleteBtn;
    JFrame frame;
    public PersonController(PersonManager model, PersonView view) {
        this.model = model;
        this.view = view;
        this.table = view.getTable();
        this.newBtn = view.getNewBtn();
        this.modifyBtn = view.getModifyBtn();
        this.deleteBtn = view.getDeleteBtn();
        this.persons = model.getPersonList();
        this.newBtn.addActionListener(this);
        this.modifyBtn.addActionListener(this);
        this.deleteBtn.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
         int row = table.getSelectedRow();
        if (e.getSource() == newBtn) {
            new PersonEditorGUI();
       } else if (e.getSource() == modifyBtn)
       {
        if (row < 0) {
            JOptionPane.showMessageDialog(frame, "Per modificare devi prima selezionare"+
            " una persona", "Errore", JOptionPane.ERROR_MESSAGE);
        } else {
            new PersonEditorGUI(model, row);
        }
       } else if (e.getSource() == deleteBtn){
        if (row < 0) {
            JOptionPane.showMessageDialog(frame, "Per eliminare devi prima selezionare"+
            " una persona", "Errore", JOptionPane.ERROR_MESSAGE);
        } else {
            int choise = JOptionPane.showConfirmDialog(frame, "Sei sicuro di voler eliminare " +
            this.persons.get(row).getName() + " " + this.persons.get(row).getSurname() + "?",
             null, JOptionPane.YES_NO_OPTION);
            if (choise == JOptionPane.YES_OPTION) {
                model.removePerson(this.persons.get(row));

            }  
        }
       }

        
       
    }    
    }



