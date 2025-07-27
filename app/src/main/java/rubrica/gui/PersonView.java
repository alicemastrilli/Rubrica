package rubrica.gui;

import java.util.List;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.spec.RC2ParameterSpec;
import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

import rubrica.model.Person;
import rubrica.model.PersonManager;

public class PersonView {

    JTable table;
    JButton newBtn;
    JButton modifyBtn;
    JButton deleteBtn;
    JFrame frame;
    public PersonView(PersonManager model) {
        
        frame = new JFrame("Rubrica");

       
        frame.setSize(500,300);
        frame.getContentPane().setLayout(new BorderLayout());

        Vector<Vector<String>> rows = new Vector<>();

        table = new JTable(model);
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
