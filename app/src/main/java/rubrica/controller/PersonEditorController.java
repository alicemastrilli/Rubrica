package rubrica.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import rubrica.gui.PersonEditorGUI;
import rubrica.gui.PersonView;
import rubrica.model.Person;
import rubrica.model.PersonManager;

public class PersonEditorController implements ActionListener{

    private Vector<String> person;
    private JButton saveBtn;
    private JButton cancelBtn;
    private PersonEditorGUI view;
    private JFrame frame;
    private PersonManager model;
    private int numRow;
    public PersonEditorController(PersonManager model, int numRow) throws InvocationTargetException, InterruptedException {
        if (numRow < 0) {
            this.person = new Vector<>(List.of("", "", "", "", ""));
        } else {
            Person person = model.getPersonList().get(numRow);
        this.person = new Vector<>(List.of(
            person.getName(),
            person.getSurname(),
            person.getAddress(),
            person.getPhoneNumber(),
            person.getAge().toString()
        ));
        }
        this.view = new PersonEditorGUI(person);    
        this.model = model;
        this.saveBtn = this.view.getSaveBtn();
        this.saveBtn.addActionListener(this);
        this.cancelBtn = this.view.getCancelBtn();
        this.cancelBtn.addActionListener(this);
        this.frame = this.view.getFrame();
        this.numRow = numRow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveBtn) {
            List<String> values = this.view.getValues();
            String name = values.get(0);
            String surname = values.get(1);
            String phoneNumber = values.get(3);
            /**
             *  Controlli input: 
             *  - obbligatorio inserire almeno nome o cognome e numero di telefono
             *  */

            if(name.isEmpty() && surname.isEmpty()) {
                JOptionPane.showMessageDialog(
                frame,
                "Il nome o il cognome sono obbligatori.",
                "Errore di validazione",
                JOptionPane.ERROR_MESSAGE
            );
                return;
            }
            if(phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(
                frame,
                "Il numero di telefono è obbligatorio.",
                "Errore di validazione",
                JOptionPane.ERROR_MESSAGE
                );
               return;
            }
            Person p = new Person(values.get(0), values.get(1), values.get(2),
            values.get(3), values.get(4).isEmpty() ? null: Integer.parseInt(values.get(4))); 
            if (this.numRow < 0) {
                // aggiungo
                this.model.addNewPerson(p);                
            } else {
                //modifico
                this.model.updatePerson(this.model.getPersonList().get(numRow), p);
            }
        } 
            
        this.frame.dispose();
        
    }
}
