package stud.ntnu.IDATT2001.MappeDel2;

import jakarta.persistence.Persistence;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import stud.ntnu.IDATT2001.MappeDel2.Task5.PatientDao;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Patient register controller
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class PatientRegisterController implements Initializable {
    @FXML private TableView<Patient> patientDetailsTableView;
    @FXML private TableColumn<Patient,String> firstName;
    @FXML private TableColumn<Patient,String> lastName;
    @FXML private TableColumn<Patient,String> generalPractitioner;
    @FXML private TableColumn<Patient,String> ssn;
    @FXML private TableColumn<Patient,String> diagnosis;
    @FXML private Label status;
    @FXML private MenuItem addNewPatient;
    @FXML private MenuItem editSelectedPatient;
    @FXML private MenuItem removeSelectedPatient;

    private FileHandler fileHandler;

    private PatientRegister patientRegister;

    private ObservableList<Patient> patientRegisterListWrapper;

    private PatientDao patientDao;

    /**
     * EnWraps the patient register into an observable list that is manageable for out gui application
     * @return a observable list of patients registered in patient register
     */
    public ObservableList<Patient> getPatientRegisterListWrapper() {
        if (this.patientRegister == null) {
            patientRegisterListWrapper =  null;
        } else {
            patientRegisterListWrapper = FXCollections.observableArrayList(this.patientRegister.getAllPatients());
        }

        return patientRegisterListWrapper;
    }

    /**
     * Updates the the patientRegisterListWrapper
     */
    public void updateObservableList() {
        this.patientRegisterListWrapper.setAll(this.patientRegister.getAllPatients());
    }

    /**
     * Functionality for add patient feature
     */
    @FXML
    public void addPatient() {

        PatientDialog patientDialog = new PatientDialog();

        Optional<Patient> result = patientDialog.showAndWait();

        if (result.isPresent()) {
            Patient newPatient = result.get();
            if (newPatient.getErrorCounter()==0) {
                if (patientRegister.addPatient(newPatient)) {
                    status.setText("Status: patient was successfully registered.");
                } else {
                    status.setText("Status: patient with this social security number is already registered.");
                }
                updateObservableList();
            }
            else {
                status.setText("Status: could not add patient. Required to fill all fields.");
            }
        }
    }

    /**
     * Alert box for delete confirmation
     * @return true if the patient was successfully deleted
     *         false if not
     */
    @FXML
    private boolean deleteConfirmation(){
        boolean delete = false;

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

    /**
     * Functionality for the delete feature
     */
    public void deletePatient() {
        Patient selectedPatient = patientDetailsTableView.getSelectionModel().getSelectedItem();
        if (selectedPatient == null) {
            showPleaseSelectItemDialog();
        } else {
            if (deleteConfirmation()) {
                patientRegister.removePatient(selectedPatient);
                status.setText("Status: patient deleted successfully.");
                updateObservableList();
            }
        }
    }

    /**
     * Alert bok for help feature
     */
    @FXML
    private void helpBox(){
        Alert alertHelp = new Alert(Alert.AlertType.INFORMATION);
        alertHelp.setTitle("Patient Register\nv0.1-SNAPSHOT");
        alertHelp.setHeaderText("Information Dialog - About");
        alertHelp.setContentText("An effective application created by\n(C)Thadshajini Paramsothy\n2021-04-30");
        alertHelp.showAndWait();
    }

    /**
     * Get information about a chosen patient
     * @param selectedPatient
     */
    @FXML
    public void showDetails(Patient selectedPatient) {
        if (selectedPatient == null) {
            showPleaseSelectItemDialog();
        } else {
            PatientDialog detailsDialog = new PatientDialog(selectedPatient,false);

            detailsDialog.showAndWait();
        }
    }

    /**
     * Alert box for if the user did not select a column
     */
    public void showPleaseSelectItemDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information");
        alert.setHeaderText("No items selected");
        alert.setContentText("No item is selected from the table.\n" + "Please select an item from the table.");

        alert.showAndWait();
    }

    /**
     * Functionality for the edit feature
     */
    @FXML
    public void editPatient() {
        Patient editRow = patientDetailsTableView.getSelectionModel().getSelectedItem();
        if (editRow == null) {
            showPleaseSelectItemDialog();
        } else {
            PatientDialog patientDialog = new PatientDialog(editRow, true);
            patientDialog.showAndWait();
            Patient result = patientDialog.getResult();
            if (result != null && result.getErrorCounter() == 0) {
                status.setText("Status: patient was successfully edited.");
                updateObservableList();
            } else {
                status.setText("Status: could not edit patient. Required to fill all fields.");
            }
        }
    }

    /**
     * Functionality for the exit feature
     */
    @FXML
    public void exitApplication() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Exit Application ?");
        alert.setContentText("Are you sure you want to exit this application?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            Platform.exit();
        }
    }

    /**
     * Functionality for the import csv feature
     */
    @FXML
    private void readFile(){
        fileHandler.importData(patientRegister);
        patientDetailsTableView.setItems(getPatientRegisterListWrapper());
    }

    /**
     * Functionality for the export csv feature
     */
    @FXML
    private void writeFile(){
        boolean exportStatus = fileHandler.exportData(patientRegister);
        if (exportStatus){
            status.setText("Status: successfully exported to .CSV file");
        } else {
            status.setText("Status: could not export to .CSV file");
        }
    }

    /**
     * Functionality for the load database feature
     */
    @FXML
    private void loadRegistryFromDatabase(){
        patientDao.loadDatabase(patientRegister);
        patientDetailsTableView.setItems(getPatientRegisterListWrapper());
    }

    /**
     * Functionality for the save database feature
     */
    @FXML
    private void saveRegistryInDatabase(){
        patientDao.saveDatabase(patientRegister);
        status.setText("Status: successfully saved the registry into database");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patientDao = new PatientDao(Persistence.createEntityManagerFactory("st‐olavs‐register"));

        fileHandler = new FileHandler();

        patientRegister = new PatientRegister();

        // set values for each column in the table view
        firstName.setCellValueFactory(new PropertyValueFactory<Patient,String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Patient,String>("lastName"));
        generalPractitioner.setCellValueFactory(new PropertyValueFactory<Patient,String>("generalPractitioner"));
        ssn.setCellValueFactory(new PropertyValueFactory<Patient,String>("socialSecurityNumber"));
        diagnosis.setCellValueFactory(new PropertyValueFactory<Patient,String>("diagnosis"));

        // sets in patient register data into the table
        patientDetailsTableView.setItems(getPatientRegisterListWrapper());

        // activated retrieving of information when you double click
        patientDetailsTableView.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && (mouseEvent.getClickCount() == 2)) {
                Patient selectedPatient = patientDetailsTableView.getSelectionModel().getSelectedItem();
                if (selectedPatient != null) {
                    showDetails(selectedPatient);
                }
            }
        });

        // adding short-keys
        addNewPatient.setAccelerator(new KeyCodeCombination(KeyCode.A));
        editSelectedPatient.setAccelerator(new KeyCodeCombination(KeyCode.E));
        removeSelectedPatient.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));

    }
}
