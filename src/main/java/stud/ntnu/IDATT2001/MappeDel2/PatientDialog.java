package stud.ntnu.IDATT2001.MappeDel2;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PatientDialog extends Dialog<Patient> {

    public enum Mode {
        NEW, EDIT, INFO
    }

    private final Mode mode;

    private Patient existingPatient = null;

    public PatientDialog() {
        super();
        this.mode = Mode.NEW;
        createPatient();
    }

    public PatientDialog (Patient patient, boolean editable){
        super();
        if (editable) {
            this.mode = Mode.EDIT;
        } else {
            this.mode = Mode.INFO;
        }
        this.existingPatient = patient;
        createPatient();
    }

    private void createPatient() {
        // Set title depending upon mode...
        switch (this.mode) {
            case EDIT:
                setTitle("Patient Details - Edit");
                break;

            case NEW:
                setTitle("Patient Details - Add");
                break;

            case INFO:
                setTitle("Patient Details");
                break;

            default:
                setTitle("Patient Details - UNKNOWN MODE...");
                break;

        }

        // Set the button types.
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField firstName = new TextField();
        firstName.setPromptText("First name:");

        TextField lastName = new TextField();
        lastName.setPromptText("Last name:");

        TextField socialSecurityNumber = new TextField();
        socialSecurityNumber.setPromptText("Social security number:");

        TextField generalPractitioner = new TextField();
        generalPractitioner.setPromptText("General Practitioner:");

        TextField diagnosis = new TextField();
        diagnosis.setPromptText("Diagnosis:");


        // Fill inn data from the provided Newspaper, if not null.
        if ((mode == Mode.EDIT) || (mode == Mode.INFO)) {
            firstName.setText(existingPatient.getFirstName());
            lastName.setText(existingPatient.getLastName());
            socialSecurityNumber.setText(existingPatient.getSocialSecurityNumber());
            generalPractitioner.setText(existingPatient.getGeneralPractitioner());
            diagnosis.setText(existingPatient.getDiagnosis());
            socialSecurityNumber.setText(existingPatient.getSocialSecurityNumber());
            // Set to non-editable if Mode.INFO
            if (mode == Mode.INFO) {
                firstName.setEditable(false);
                lastName.setEditable(false);
                socialSecurityNumber.setEditable(false);
                generalPractitioner.setEditable(false);
                diagnosis.setEditable(false);
            }
        }

        grid.add(new Label("First name:"), 0, 0);
        grid.add(firstName, 1, 0);

        grid.add(new Label("Last name:"), 0, 1);
        grid.add(lastName, 1, 1);

        grid.add(new Label("Social security number:"), 0, 2);
        grid.add(socialSecurityNumber, 1, 2);

        grid.add(new Label("General Practitioner:"), 0, 3);
        grid.add(generalPractitioner, 1, 3);

        grid.add(new Label("Diagnosis:"), 0, 4);
        grid.add(diagnosis, 1, 4);


        getDialogPane().setContent(grid);

        setResultConverter((ButtonType button) -> {
            Patient result = null;
            if (button == ButtonType.OK) {

                if (mode == Mode.NEW) {
                    result = new Patient(socialSecurityNumber.getText(),firstName.getText(),lastName.getText(),generalPractitioner.getText(),diagnosis.getText());
                } else if (mode == Mode.EDIT) {
                    existingPatient.setSocialSecurityNumber(socialSecurityNumber.getText());
                    existingPatient.setFirstName(firstName.getText());
                    existingPatient.setLastName(lastName.getText());
                    existingPatient.setDiagnosis(diagnosis.getText());
                    existingPatient.setGeneralPractitioner(generalPractitioner.getText());
                    result = existingPatient;
                }
            }
            return result;
        });
    }

}


