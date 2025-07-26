package rubrica.gui;

import java.util.List;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.spec.RC2ParameterSpec;
import javax.swing.*;

import rubrica.model.Person;

public class TableGUI implements ActionListener{

    Vector<Person> persons; 
    public TableGUI() {

        JFrame frame = new JFrame("Rubrica");

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

        JTable table = new JTable(rows, columnNames);
        JScrollPane sp = new JScrollPane(table);
         
        frame.getContentPane().add(sp, BorderLayout.CENTER);   // metto la tabella al centro

        JPanel panel = new JPanel();
       
        JButton newBtn = new JButton("Nuovo");
        newBtn.addActionListener(this);
        JButton modifyBtn = new JButton("Modifica");
        modifyBtn.addActionListener(this);
        JButton deleteBtn = new JButton("Elimina");
        panel.add(newBtn);
        panel.add(modifyBtn);
        panel.add(deleteBtn);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        new PersonEditorGUI();
    }
    
}
