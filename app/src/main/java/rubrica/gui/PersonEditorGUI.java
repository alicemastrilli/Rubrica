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

public class PersonEditorGUI {
    private Vector<String> rowNames = new Vector<>(List.of("Nome", "Cognome", "Indirizzo", "Telefono", "Età" ));

    public PersonEditorGUI() {
        JFrame f = new JFrame("editor-persona");
        

        f.setSize(500,300);
        f.getContentPane().setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(5, 2));
        for (String row : rowNames) {
            panel.add(new JLabel(row));
            panel.add(new JTextField());
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