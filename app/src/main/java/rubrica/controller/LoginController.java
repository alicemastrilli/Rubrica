package rubrica.controller;

import rubrica.model.PersonManager;
import rubrica.view.LoginView;
import rubrica.view.PersonViewImpl;

public class LoginController {
    private static String USERNAME = "username";
    private static String PASSWORD = "password";
    LoginView view;
    public LoginController(LoginView view) {
        this.view = view;
        view.setObserver(this);
        view.start();

    }

    public void login(String username, String password) {

        if (!username.equals(USERNAME) || !password.equals(PASSWORD)) {
            this.view.showErrorMessage();
        } else {
            PersonManager model = new PersonManager();
            PersonViewImpl view = new PersonViewImpl(model);

            new PersonControllerImpl(model, view);
            this.view.closeWindow();
        }
    }
    
}
