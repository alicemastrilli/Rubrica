package rubrica.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import rubrica.controller.PersonController;
import rubrica.model.PersonManager;

public class PersonViewImpl implements PersonView {
    private static final String WINDOW_NAME = "Rubrica";
    private static final String ADD_BTN_NAME = "Nuovo";
    private static final String MODIFY_BTN_NAME = "Modifica";
    private static final String DELETE_BTN_NAME = "Elimina";
    private static final Dimension WINDOW_DIMENSION = new Dimension(500, 300);

    private JTable table;
    private JButton newBtn;
    private JButton modifyBtn;
    private JButton deleteBtn;
    private JFrame frame;
    private PersonController controller;

    public PersonViewImpl(PersonManager model) {

        frame = new JFrame(WINDOW_NAME);

        frame.setSize(WINDOW_DIMENSION);
        frame.getContentPane().setLayout(new BorderLayout());

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);

        frame.getContentPane().add(sp, BorderLayout.CENTER); // metto la tabella al centro

        JPanel panel = new JPanel();

        newBtn = new JButton(ADD_BTN_NAME);
        modifyBtn = new JButton(MODIFY_BTN_NAME);
        deleteBtn = new JButton(DELETE_BTN_NAME);
        panel.add(newBtn);
        panel.add(modifyBtn);
        panel.add(deleteBtn);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void setObserver(PersonController controller) {
        this.controller = controller;
        newBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addNewPerson();
            }

        });
        modifyBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.modifyPerson(table.getSelectedRow());
            }

        });
        deleteBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onDeleteButtonClicked(table.getSelectedRow());
            }

        });
    }

    @Override
    public void showConfirmOnDelete(String question) {
        int choise = JOptionPane.showConfirmDialog(frame, question, null, JOptionPane.YES_NO_OPTION);
        if (choise == JOptionPane.YES_OPTION) {
            controller.removePerson(table.getSelectedRow());

        }
    }

    @Override
    public void showErrorDialog(String error) {
        JOptionPane.showMessageDialog(frame, error, "Errore", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void start() {
        this.frame.setVisible(true);
    }

}
