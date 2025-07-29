package rubrica.view;

import rubrica.controller.PersonEditorController;

public interface PersonEditorGUI {

    void setObserver(PersonEditorController controller);

    void start();

    void showErrorDialog(String error);

    void dispose();

}