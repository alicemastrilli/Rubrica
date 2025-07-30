package rubrica.view;

import rubrica.controller.LoginController;

public interface LoginView {

    void setObserver(LoginController controller);

    void start();

    void showErrorMessage();

    void closeWindow();

}