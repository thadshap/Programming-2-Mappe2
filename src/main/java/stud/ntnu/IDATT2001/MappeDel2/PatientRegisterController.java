package stud.ntnu.IDATT2001.MappeDel2;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class PatientRegisterController implements Initializable {
    @FXML private TableView<Patient> patientDetailsTableView;
    @FXML private TableColumn<Patient,String> firstName;
    @FXML private TableColumn<Patient,String> lastName;
    @FXML private TableColumn<Patient,String> generalPractitioner;
    @FXML private TableColumn<Patient,String> ssn;
    @FXML private TableColumn<Patient,String> diagnosis;
    @FXML private Label status;


    private PatientRegister patientRegister;

    private ObservableList<Patient> patientRegisterListWrapper;

    public ObservableList<Patient> getPatientRegisterListWrapper() {
        if (this.patientRegister == null) {
            patientRegisterListWrapper =  null;
        } else {
            patientRegisterListWrapper = FXCollections.observableArrayList(this.patientRegister.getAllPatients());
        }

        return patientRegisterListWrapper;
    }

    public void updateObservableList() {
        this.patientRegisterListWrapper.setAll(this.patientRegister.getAllPatients());
    }

    @FXML
    public void addPatient() {

        PatientDialog patientDialog = new PatientDialog();

        Optional<Patient> result = patientDialog.showAndWait();

        if (result.isPresent()) {
            Patient newPatient = result.get();
            if (newPatient.getErrorMessages().isEmpty()) {
                if (patientRegister.addPatient(newPatient)) {
                    status.setText("Patient was successfully registered.");
                } else {
                    status.setText("Patient with this social security number is already registered.");
                }
                updateObservableList();
            }
            else {
                status.setText("Could not add patient. Required to fill all fields.");
            }
        }
    }

    @FXML
    private boolean deleteConfirmation(){
        Boolean delete = false;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("Delete confirmation");
        alert.setContentText("Are you sure you want to delete this item?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            delete = true;
            updateObservableList();
        }
        return delete;
    }

    public void deletePatient() {
        Patient selectedPatient = patientDetailsTableView.getSelectionModel().getSelectedItem();
        if (selectedPatient == null) {
            showPleaseSelectItemDialog();
        } else {
            if (deleteConfirmation()) {
                patientRegister.removePatient(selectedPatient);
                status.setText("Patient deleted successfully.");
                updateObservableList();
            }
        }
    }

    private void handleDelete(){
        if (deleteConfirmation()){

        }
    }

    @FXML
    private void helpBox(){
        Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
        alertHelp.setTitle("Patient Register\nv0.1-SNAPSHOT");
        alertHelp.setHeaderText("Information Dialog - About");
        alertHelp.setContentText("An effective application created by\n(C)Thadshajini Paramsothy\n2021-04-30");
        alertHelp.showAndWait();
    }

    @FXML
    public void showDetails(Patient selectedPatient) {
        if (selectedPatient == null) {
            showPleaseSelectItemDialog();
        } else {

            PatientDialog detailsDialog = new PatientDialog(selectedPatient,false);

            detailsDialog.showAndWait();
        }
    }

    public void showPleaseSelectItemDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information");
        alert.setHeaderText("No items selected");
        alert.setContentText("No item is selected from the table.\n" + "Please select an item from the table.");

        alert.showAndWait();
    }

    @FXML
    public void editPatient() {
        Patient editRow = patientDetailsTableView.getSelectionModel().getSelectedItem();
        if (editRow == null) {
            showPleaseSelectItemDialog();
        } else {
            PatientDialog patientDialog = new PatientDialog(editRow, true);
            patientDialog.showAndWait();
            if (patientDialog.getResult().getErrorMessages().isEmpty()) {
                status.setText("Patient was successfully edited.");
                updateObservableList();
            }
            else {
                status.setText("Could not edit patient. Required to fill all fields.");
            }
        }
    }

    @FXML
    public void exitApplication() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Exit Application ?");
        alert.setContentText("Are you sure you want to exit this application?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            // ... user choose OK
            Platform.exit();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patientRegister = new PatientRegister();

        patientRegister.addPatient(new Patient("12345678910","t","t","r","r"));
        patientRegister.addPatient(new Patient("12345678810","t","p","r","r"));

        firstName.setCellValueFactory(new PropertyValueFactory<Patient,String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Patient,String>("lastName"));
        generalPractitioner.setCellValueFactory(new PropertyValueFactory<Patient,String>("generalPractitioner"));
        ssn.setCellValueFactory(new PropertyValueFactory<Patient,String>("socialSecurityNumber"));
        diagnosis.setCellValueFactory(new PropertyValueFactory<Patient,String>("diagnosis"));
        patientDetailsTableView.getColumns().addAll(firstName,lastName,generalPractitioner,ssn,diagnosis);

        patientDetailsTableView.setItems(getPatientRegisterListWrapper());

        patientDetailsTableView.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && (mouseEvent.getClickCount() == 2)) {
                Patient selectedPatient = patientDetailsTableView.getSelectionModel().getSelectedItem();
                if (selectedPatient != null) {
                    showDetails(selectedPatient);
                }
            }
        });

        //patientDetailsTableView = createCenterContent();

    }

}
