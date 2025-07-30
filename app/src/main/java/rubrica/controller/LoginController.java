package rubrica.controller;

import java.sql.Connection;

import rubrica.model.PersonManager;
import rubrica.view.LoginView;
import rubrica.view.PersonViewImpl;

public class LoginController {
    private static String USERNAME = "username";
    private static String PASSWORD = "password";
    LoginView view;
    private Connection connection;
    public LoginController(LoginView view, Connection connection) {
        this.view = view;
        view.setObserver(this);
        view.start();
        this.connection = connection;

    }

    public void login(String username, String password) {

        if (!username.equals(USERNAME) || !password.equals(PASSWORD)) {
            this.view.showErrorMessage();
        } else {
            PersonManager model = new PersonManager(connection);
            PersonViewImpl view = new PersonViewImpl(model);

            new PersonControllerImpl(model, view);
            this.view.closeWindow();
        }
    }
    
}
