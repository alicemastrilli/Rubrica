package rubrica.gui;

import rubrica.controller.PersonController;

public interface PersonView {

    void setObserver(PersonController controller);

    void showConfirmOnDelete(String question);

    void showErrorDialog(String error);

    void start();

}