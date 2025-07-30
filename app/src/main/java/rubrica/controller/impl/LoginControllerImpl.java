package rubrica.controller.impl;

import java.sql.Connection;

import rubrica.controller.LoginController;
import rubrica.db.UserDao;
import rubrica.db.impl.UserDaoImpl;
import rubrica.model.PersonManager;
import rubrica.view.LoginView;
import rubrica.view.impl.PersonViewImpl;

public class LoginControllerImpl implements LoginController {

    LoginView view;
    private Connection connection;
    private UserDao userDao;
    public LoginControllerImpl(LoginView view, Connection connection) {
        this.view = view;
        view.setObserver(this);
        view.start();
        this.userDao = new UserDaoImpl(connection);
        this.connection = connection;

    }

    private boolean userIsCorrect(String username, String password) {
        return this.userDao.getAllUsers().stream().anyMatch(u ->
        u.getUsername().equals(username) &&
        u.getPassword().equals(password)
    );

    }

    @Override
    public void login(String username, String password) {

        if (!userIsCorrect(username, password)) {
            this.view.showErrorMessage();
        } else {
            PersonManager model = new PersonManager(connection);
            PersonViewImpl view = new PersonViewImpl(model);

            new PersonControllerImpl(model, view);
            this.view.closeWindow();
        }
    }
    
}
