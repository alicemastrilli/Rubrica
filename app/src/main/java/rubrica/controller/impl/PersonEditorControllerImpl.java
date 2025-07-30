package rubrica.controller.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;

import rubrica.controller.PersonEditorController;
import rubrica.model.Person;
import rubrica.model.PersonManager;
import rubrica.view.PersonEditorGUI;
import rubrica.view.impl.PersonEditorGUIImpl;

public class PersonEditorControllerImpl implements PersonEditorController {

    private Vector<String> person;
    private PersonEditorGUI view;
    private PersonManager model;
    private int numRow;

    public PersonEditorControllerImpl(PersonManager model, int numRow)
            throws InvocationTargetException, InterruptedException {
        if (numRow < 0) {
            this.person = new Vector<>(List.of("", "", "", "", ""));
        } else {
            Person person = model.getPersonList().get(numRow);
            this.person = new Vector<>(List.of(
                    person.getName(),
                    person.getSurname(),
                    person.getAddress(),
                    person.getPhoneNumber(),
                    person.getAge() == null ? "" : person.getAge().toString()));
        }
        this.model = model;

        this.numRow = numRow;
        this.view = new PersonEditorGUIImpl(person);
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void onSaveButtonClicked(List<String> values) {
        String name = values.get(0);
        String surname = values.get(1);
        String phoneNumber = values.get(3);
        /**
         * Controlli input:
         * - obbligatorio inserire almeno nome o cognome e numero di telefono
         */
        if (name.isEmpty() && surname.isEmpty()) {
            view.showErrorDialog("Il nome o il cognome sono obbligatori.");
            return;
        }
        if (phoneNumber.isEmpty()) {
            view.showErrorDialog("Il numero di telefono è obbligatorio");
            return;
        }
        Person p = new Person(values.get(0), values.get(1), values.get(2),
                values.get(3), values.get(4).isEmpty() ? null : Integer.parseInt(values.get(4)));
        if (this.numRow < 0) {
            // aggiungo
            this.model.addNewPerson(p);
        } else {
            Person oldP = this.model.getPersonList().get(numRow);
            p.setId(oldP.getId());
            // modifico
            this.model.updatePerson(oldP, p);
        }
        view.dispose();
    }

}
