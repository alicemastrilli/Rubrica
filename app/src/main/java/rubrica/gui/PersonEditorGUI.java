package rubrica.gui;
import java.awt.*;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PersonEditorGUI {
    private static Integer ROW_NUMBER = 5;
    private static Integer COL_NUMBER = 2;
    private Vector<String> rowNames = new Vector<>(List.of("Nome", "Cognome", "Indirizzo", "Telefono", "Età" ));
    private Vector<String> person;
    private Vector<JTextField> textFields;
    private JOptionPane optionPane;
    public JButton getSaveBtn() {
        return saveBtn;
    }
    public JButton getCancelBtn() {
        return cancelBtn;
    }
    public JFrame getFrame() {
        return f;
    }
    private JButton saveBtn;
    private JButton cancelBtn;
    private JFrame f; 
    public PersonEditorGUI(Vector<String> person) {
        this.person = person;
        f = new JFrame("editor-persona");
        f.setSize(500,300);
        f.getContentPane().setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(ROW_NUMBER, COL_NUMBER));
        this.textFields = new Vector<>();
        for(int i = 0; i < ROW_NUMBER; i++ ){
            panel.add(new JLabel(rowNames.get(i)));
            JTextField textField = new JTextField(this.person.get(i));
            this.textFields.add(textField);
            panel.add(textField);
        }

        f.getContentPane().add(panel, BorderLayout.CENTER);
        JPanel btnPanel = new JPanel();
        //TODO: aggiungere controllo sui valori inseriri prima di salvare
        saveBtn = new JButton("Salva");
        cancelBtn = new JButton("Annulla");
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);
        f.getContentPane().add(btnPanel, BorderLayout.SOUTH);
        f.setVisible(true);
    }

    public List<String> getValues() {
        return this.textFields.stream().map(JTextField::getText).toList();
    }
}