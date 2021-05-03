package stud.ntnu.IDATT2001.MappeDel2.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import stud.ntnu.IDATT2001.MappeDel2.model.Patient;

/**
 * Creates dialog boxes for operation regarding a patient ie add, edit and info
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class PatientDialog extends Dialog<Patient> {
    final String textRegex = "^[a-zA-Z ,.'-]*$"; //uppercase and lowercase letters, fullstops, hyphen, apostrophe, comma and space.

    private Label ssnError = new Label();

    private final Mode mode;

    private Patient existingPatient = null;

    public enum Mode {
        NEW, EDIT, INFO
    }

    /**
     * Constructor (in use to add a new patient in the table view)
     */
    public PatientDialog() {
        super();
        this.mode = Mode.NEW;
        patientHandler();
    }

    /**
     * Constructor (in use to edit a patient in the table view or look up information about a patient)
     * @param patient
     * @param editable
     */
    public PatientDialog (Patient patient, boolean editable){
        super();
        if (editable) {
            this.mode = Mode.EDIT;
        } else {
            this.mode = Mode.INFO;
        }
        this.existingPatient = patient;
        patientHandler();
    }

    /**
     * Handles a patient according to a given enum
     */
    private void patientHandler() {
        GridPane grid = setupDialogBox();

        TextField firstName = new TextField();
        firstName.setPromptText("Ex. Isaac");
        validateTextField(firstName,textRegex);

        TextField lastName = new TextField();
        lastName.setPromptText("Ex. Newton");
        validateTextField(lastName, textRegex);

        TextField socialSecurityNumber = new TextField();
        socialSecurityNumber.setPromptText("Ex. 12345678912");
        validateNumber(socialSecurityNumber,"[0-9]{0,11}","[0-9]{11}");

        TextField generalPractitioner = new TextField();
        generalPractitioner.setPromptText("Ex. Dr. Christina Yang");
        validateTextField(generalPractitioner,textRegex);

        TextField diagnosis = new TextField();
        diagnosis.setPromptText("Ex. Artery disease");
        validateTextField(diagnosis,textRegex);

        infoAndEditSeparator(socialSecurityNumber,firstName,lastName,generalPractitioner,diagnosis);

        setupGrid(grid,socialSecurityNumber,firstName,lastName,generalPractitioner,diagnosis);

        getDialogPane().setContent(grid);

        addAndEditSeparator(socialSecurityNumber,firstName,lastName,generalPractitioner,diagnosis);
    }

    /**
     * Helper method to changes the color of the frame of the text field corresponding to the input
     * (used for first name, last name, general practitioner and diagnosis text field)
     * @param textField
     * @param regex
     */
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

    /**
     * Helper method to changes the color of the frame of the text field corresponding to the input
     * (used for social security number text field)
     * @param textField
     * @param regex1
     * @param regex2
     */
    private void validateNumber(TextField textField, String regex1, String regex2){
        textField.setTextFormatter(new TextFormatter<>(change ->
                (change.getControlNewText().matches(regex1)) ? change : null));
        textField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (textField.getText().matches(regex2)){
                    textField.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                    ssnError.setText("");
                }
                else {
                    textField.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                    ssnError.setTextFill(Color.RED);
                    ssnError.setText("*please provide 11 digits.");
                }
            }
        });
    }

    /**
     * Help method to switch in between the different objects that provide dialog boxes with corresponding functionality
     * @return the object's associated dialog box
     */
    private GridPane setupDialogBox(){
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
        return  grid;
    }

    /**
     * Helper method of saving patient objects depending on edit and new enums
     * @param socialSecurityNumber
     * @param firstName
     * @param lastName
     * @param generalPractitioner
     * @param diagnosis
     */
    private void addAndEditSeparator(TextField socialSecurityNumber,
                                     TextField firstName,
                                     TextField lastName,
                                     TextField generalPractitioner,
                                     TextField diagnosis){
        setResultConverter((ButtonType button) -> {
            Patient result = null;
            if (button == ButtonType.OK) {

                if (mode == Mode.NEW) {
                    result = new Patient(socialSecurityNumber.getText(),firstName.getText(),lastName.getText(),diagnosis.getText(),generalPractitioner.getText());
                } else if (mode == Mode.EDIT) {
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

    /**
     * Helper method to set if data from patient is editable depending on info and dit enums
     * @param socialSecurityNumber
     * @param firstName
     * @param lastName
     * @param generalPractitioner
     * @param diagnosis
     */
    private void infoAndEditSeparator(TextField socialSecurityNumber,
                                      TextField firstName,
                                      TextField lastName,
                                      TextField generalPractitioner,
                                      TextField diagnosis){

        if ((mode == Mode.EDIT) || (mode == Mode.INFO)) {
            firstName.setText(existingPatient.getFirstName());
            lastName.setText(existingPatient.getLastName());
            socialSecurityNumber.setEditable(false);
            generalPractitioner.setText(existingPatient.getGeneralPractitioner());
            diagnosis.setText(existingPatient.getDiagnosis());
            socialSecurityNumber.setText(existingPatient.getSocialSecurityNumber());

            if (mode == Mode.INFO) {
                firstName.setEditable(false);
                lastName.setEditable(false);
                socialSecurityNumber.setEditable(false);
                generalPractitioner.setEditable(false);
                diagnosis.setEditable(false);
            }
        }
    }

    /**
     * Helper methode for setting up the position for each GUI elements on the dialog box
     * @param grid
     * @param socialSecurityNumber
     * @param firstName
     * @param lastName
     * @param generalPractitioner
     * @param diagnosis
     */
    private void setupGrid(GridPane grid,
                           TextField socialSecurityNumber,
                           TextField firstName,
                           TextField lastName,
                           TextField generalPractitioner,
                           TextField diagnosis){
        Label required = new Label("Required to fill all fields");
        grid.add(required,1,0);

        grid.add(new Label("First name"), 0, 1);
        grid.add(firstName, 1, 1);

        grid.add(new Label("Last name"), 0, 2);
        grid.add(lastName, 1, 2);

        grid.add(new Label("Social security number"), 0, 3);
        grid.add(socialSecurityNumber, 1, 3);

        grid.add(ssnError,1,4);

        grid.add(new Label("General Practitioner"), 0, 5);
        grid.add(generalPractitioner, 1, 5);

        grid.add(new Label("Diagnosis"), 0, 6);
        grid.add(diagnosis, 1, 6);

    }
}


