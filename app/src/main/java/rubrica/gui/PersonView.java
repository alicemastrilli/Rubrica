package rubrica.gui;

import java.util.List;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.spec.RC2ParameterSpec;
import javax.swing.*;


import rubrica.model.Person;

public class PersonView {

    Vector<Person> persons; 
    JTable table;
    JButton newBtn;
    JButton modifyBtn;
    JButton deleteBtn;
    JFrame frame;

    public PersonView() {

        frame = new JFrame("Rubrica");

        Person p1 = new Person("ali", "mas", "via g.", "3926", 25);
        Person p2 = new Person("fra", "diste", "via g.", "3926", 25);
        this.persons = new Vector<>(List.of(p1,p2));

        frame.setSize(500,300);
        frame.getContentPane().setLayout(new BorderLayout());

        Vector<Vector<String>> rows = new Vector<>();
        for (Person p : this.persons) {
            rows.add(new Vector<>(List.of(p.getName(), p.getSurname(), p.getPhoneNumber())));
        }
        Vector<String> columnNames = new Vector<>(List.of("Name", "Surname", "PhoneNumber" ));

        table = new JTable(rows, columnNames);
        JScrollPane sp = new JScrollPane(table);

        frame.getContentPane().add(sp, BorderLayout.CENTER);   // metto la tabella al centro

        JPanel panel = new JPanel();
       
        newBtn = new JButton("Nuovo");
        modifyBtn = new JButton("Modifica");
        deleteBtn = new JButton("Elimina");
        panel.add(newBtn);
        panel.add(modifyBtn);
        panel.add(deleteBtn);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        
    }
    public Vector<Person> getPersons() {
        return persons;
    }
    public JTable getTable() {
        return table;
    }
    public JButton getNewBtn() {
        return newBtn;
    }
    public JButton getModifyBtn() {
        return modifyBtn;
    }
    public JButton getDeleteBtn() {
        return deleteBtn;
    }
    public JFrame getFrame() {
        return frame;
    }
   
}
