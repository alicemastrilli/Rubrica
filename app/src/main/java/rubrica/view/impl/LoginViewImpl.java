package rubrica.view.impl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rubrica.controller.LoginController;
import rubrica.view.LoginView;

public class LoginViewImpl implements LoginView {    
    private static final String WINDOW_NAME = "Accedi";
    private static final String USERNAME_LABEL = "Username";
    private static final String PASSWORD_LABEL = "Password";
    private static final String LOGIN_BTN_NAME = "Login";
    private static final String WRONG_LOGIN_MESSAGE = "Login errato";
    private static final Integer FIELD_COLUMNS = 15;

    private static final Dimension WINDOW_DIMENSION = new Dimension(500, 150); 
    private JFrame frame;
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField userPasswordField;
    private JLabel messageLabel;

    public LoginViewImpl() {
       frame = new JFrame(WINDOW_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(WINDOW_DIMENSION);
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel(USERNAME_LABEL));
        usernameField = new JTextField(FIELD_COLUMNS);
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel(PASSWORD_LABEL));
        userPasswordField = new JPasswordField(FIELD_COLUMNS);
        inputPanel.add(userPasswordField);

        frame.add(inputPanel, BorderLayout.CENTER);

        
                

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        messageLabel = new JLabel(" "); 
        buttonPanel.add(messageLabel);
        loginButton = new JButton(LOGIN_BTN_NAME);
        buttonPanel.add(loginButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        //frame.pack();
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true);

    }

    @Override
    public void setObserver(LoginController controller) {
        this.loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.login(usernameField.getText(), String.valueOf(userPasswordField.getPassword()));
            }
            
        });
    }
    @Override
    public void start() {
        frame.setVisible(true);
    }

    @Override
    public void showErrorMessage() {
        this.messageLabel.setText(WRONG_LOGIN_MESSAGE);
    }
    
    @Override
    public void closeWindow() {
        frame.dispose();
    }
    
    
}
