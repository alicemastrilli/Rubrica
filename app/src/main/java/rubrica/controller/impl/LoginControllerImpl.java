package rubrica.controller.impl;

import java.sql.Connection;

import rubrica.controller.LoginController;
import rubrica.model.PersonManager;
import rubrica.view.LoginView;
import rubrica.view.impl.PersonViewImpl;

public class LoginControllerImpl implements LoginController {
    private static String USERNAME = "username";
    private static String PASSWORD = "password";
    LoginView view;
    private Connection connection;
    public LoginControllerImpl(LoginView view, Connection connection) {
        this.view = view;
        view.setObserver(this);
        view.start();
        this.connection = connection;

    }

    @Override
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
