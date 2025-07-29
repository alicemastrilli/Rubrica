package rubrica.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rubrica.controller.PersonEditorController;

public class PersonEditorGUIImpl implements PersonEditorGUI {
    private static final String WINDOW_NAME = "Editor persona";
    private static final String SAVE_BTN_NAME = "Salva";
    private static final String CANCEL_BTN_NAME = "Annulla";
    private static final Dimension WINDOW_DIMENSION = new Dimension(500, 300);
    private static final Integer ROW_NUMBER = 5;
    private static final Integer COL_NUMBER = 2;
    private Vector<String> rowNames = new Vector<>(List.of("Nome", "Cognome", "Indirizzo", "Telefono", "Età"));
    private Vector<String> person;
    private Vector<JTextField> textFields;
    private JButton saveBtn;
    private JButton cancelBtn;
    private JFrame f;

    public PersonEditorGUIImpl(Vector<String> person) {
        this.person = person;
        f = new JFrame(WINDOW_NAME);
        f.setSize(WINDOW_DIMENSION);
        f.getContentPane().setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(ROW_NUMBER, COL_NUMBER));
        this.textFields = new Vector<>();
        for (int i = 0; i < ROW_NUMBER; i++) {
            panel.add(new JLabel(rowNames.get(i)));
            JTextField textField = new JTextField(this.person.get(i));
            this.textFields.add(textField);
            panel.add(textField);
        }

        f.getContentPane().add(panel, BorderLayout.CENTER);
        JPanel btnPanel = new JPanel();
        saveBtn = new JButton(SAVE_BTN_NAME);
        cancelBtn = new JButton(CANCEL_BTN_NAME);
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);
        f.getContentPane().add(btnPanel, BorderLayout.SOUTH);

    }

    @Override
    public void setObserver(PersonEditorController controller) {
        saveBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> values = textFields.stream().map(JTextField::getText).toList();
                controller.onSaveButtonClicked(values);
            }

        });
    }

    @Override
    public void start() {
        f.setVisible(true);
    }

    @Override
    public void showErrorDialog(String error) {
        JOptionPane.showMessageDialog(f, error, "Errore", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void dispose() {
        this.f.dispose();
    }

}