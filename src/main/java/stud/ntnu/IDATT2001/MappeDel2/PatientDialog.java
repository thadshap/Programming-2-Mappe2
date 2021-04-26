package stud.ntnu.IDATT2001.MappeDel2;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PatientDialog extends Dialog<Patient> {
    final String textRegex = "^[a-zA-Z ,.'-]*$";

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

    private Label ssnError = new Label();




    private void validateTextField(TextField textField, String regex){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches(regex)) ? change : null));
        textField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (textField.getText().matches(regex) && !textField.getText().isEmpty()){
                    textField.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                }
                else {
                    textField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                }
            }
        });
    }

    private void createPatient() {
        // Set title depending upon mode...
        switch (this.mode) {
            case EDIT:
                setTitle("Patient Details - Edit");
                getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                break;

            case NEW:
                setTitle("Patient Details - Add");
                getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
                break;

            case INFO:
                setTitle("Patient Details");
                getDialogPane().getButtonTypes().addAll(ButtonType.OK);
                break;

            default:
                setTitle("Patient Details - UNKNOWN MODE...");
                break;

        }

        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField firstName = new TextField();
        firstName.setPromptText("Ex. Isaac");
        validateTextField(firstName,textRegex);

        TextField lastName = new TextField();
        lastName.setPromptText("Ex. Newton");
        validateTextField(lastName, textRegex);

        TextField socialSecurityNumber = new TextField();
        socialSecurityNumber.setPromptText("Ex. 12345678912");
        socialSecurityNumber.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches("[0-9]{0,11}")) ? change : null));
        socialSecurityNumber.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (socialSecurityNumber.getText().matches("[0-9]{11}")){
                    socialSecurityNumber.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                    ssnError.setText("");
                }
                else {
                    socialSecurityNumber.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                    ssnError.setTextFill(Color.RED);
                    ssnError.setText("*please provide 11 digits.");
                }
            }
        });

        TextField generalPractitioner = new TextField();
        generalPractitioner.setPromptText("Ex. Dr. Christina Yang");
        validateTextField(generalPractitioner,textRegex);

        TextField diagnosis = new TextField();
        diagnosis.setPromptText("Ex. Artery disease");
        validateTextField(diagnosis,textRegex);


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

        grid.add(new Label("First name"), 0, 0);
        grid.add(firstName, 1, 0);


        grid.add(new Label("Last name"), 0, 2);
        grid.add(lastName, 1, 2);

        grid.add(new Label("Social security number"), 0, 3);
        grid.add(socialSecurityNumber, 1, 3);

        grid.add(ssnError,1,4);

        grid.add(new Label("General Practitioner"), 0, 5);
        grid.add(generalPractitioner, 1, 5);

        grid.add(new Label("Diagnosis"), 0, 7);
        grid.add(diagnosis, 1, 7);

        Label required = new Label("*Required to fill all fields");
        required.setTextFill(Color.RED);
        grid.add(required,0,9);


        getDialogPane().setContent(grid);

        setResultConverter((ButtonType button) -> {
            Patient result = null;
            if (button == ButtonType.OK) {

                if (mode == Mode.NEW) {
                    result = new Patient(socialSecurityNumber.getText(),firstName.getText(),lastName.getText(),diagnosis.getText(),generalPractitioner.getText());
                } else if (mode == Mode.EDIT) {
                    existingPatient.setSocialSecurityNumber(socialSecurityNumber.getText());
                    existingPatient.setFirstName(firstName.getText());
                    existingPatient.setLastName(lastName.getText());
                    existingPatient.setGeneralPractitioner(generalPractitioner.getText());
                    existingPatient.setDiagnosis(diagnosis.getText());
                    result = existingPatient;
                }
            }
            return result;
        });
    }

}


