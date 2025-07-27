package rubrica.gui;
import java.awt.*;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import rubrica.model.Person;
import rubrica.model.PersonManager;

public class PersonEditorGUI {
    private static Integer ROW_NUMBER = 5;
    private static Integer COL_NUMBER = 2;
    private Vector<String> rowNames = new Vector<>(List.of("Nome", "Cognome", "Indirizzo", "Telefono", "Età" ));
    private Vector<String> person;
    public PersonEditorGUI(PersonManager model, int numPerson) {
        Person person = model.getPersonList().get(numPerson);
        this.person = new Vector<>(List.of(
            person.getName(),
            person.getSurname(),
            person.getAddress(),
            person.getPhoneNumber(),
            person.getAge().toString()
        ));
        this.openEditor();
    }
    public PersonEditorGUI() {
        this.person = new Vector<>(List.of("", "", "", "", ""));
        this.openEditor();
    }
    private void openEditor(){
        JFrame f = new JFrame("editor-persona");
        

        f.setSize(500,300);
        f.getContentPane().setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(ROW_NUMBER, COL_NUMBER));
        for(int i = 0; i < ROW_NUMBER; i++ ){
            panel.add(new JLabel(rowNames.get(i)));
            panel.add(new JTextField(this.person.get(i)));
        }

        f.getContentPane().add(panel, BorderLayout.CENTER);
        JPanel btnPanel = new JPanel();
        JButton saveBtn = new JButton("Salva");
        //newBtn.addActionListener(this);
        JButton cancelBtn = new JButton("Annulla");
        //modifyBtn.addActionListener(this);
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);
        f.getContentPane().add(btnPanel, BorderLayout.SOUTH);
        f.setVisible(true);
    }
}