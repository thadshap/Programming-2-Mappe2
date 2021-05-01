package stud.ntnu.IDATT2001.MappeDel2;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

/**
 * This class represents a file handler class which contains
 * the methods for reading and writing a .csv file.
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class FileHandler {

    /**
     * Imports data from a csv file and populates the table view
     * @param patientRegister is a register of all the patient
     */
    public void importData(PatientRegister patientRegister) {
        FileChooser fileChooser = new FileChooser();

        /* Implemented filter instead of letting the user choose wrong filetype. So, it is not necessary to create, for instance
           an alert dialog box to give the user a message about invalid filetype as the task description in 4.1 says.
         */
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV file (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        //Opens up your finder/file explorer
        File file = fileChooser.showOpenDialog(null);

        if(file != null){
            fileReader(patientRegister,file);
        }
    }

    /**
     * Helder method for reading file
     * @param patientRegister
     * @param file
     */
    public boolean fileReader(PatientRegister patientRegister, File file){
        boolean status = false;
        try(Scanner scanner = new Scanner(file)) {
            String metaData = scanner.nextLine();
            String[] arr = metaData.split(";");

            int socialSecurityNumber = 0, lastName = 0, firstName = 0, generalPractitioner = 0;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals("firstName")) {
                    firstName = i;
                } else if (arr[i].equals("lastName")) {
                    lastName = i;
                } else if (arr[i].equals("generalPractitioner")) {
                    generalPractitioner = i;
                } else if (arr[i].equals("socialSecurityNumber")) {
                    socialSecurityNumber = i;
                }
            }

            while (scanner.hasNextLine()){
                String[] data = scanner.nextLine().split(";");

                Patient p = new Patient(
                        data[firstName],
                        data[lastName],
                        data[generalPractitioner],
                        data[socialSecurityNumber]
                );
                patientRegister.addPatient(p);
            }
            status = true;
        }
        catch (IOException e){
            System.err.println("ERROR: IO exception while reading from file " +file.getPath());
            status = false;
        }
        return status;
    }

    /**
     * Exports data from the table view to a csv file
     * @param patientRegister
     * @return
     */
    public boolean exportData(PatientRegister patientRegister){
        // opens a dialog box to allow the user select a file directory and specify a file name
        Dialog<PathContent> fileDialog = new FilePathDialog();
        fileDialog.showAndWait();
        PathContent result = fileDialog.getResult();
        String filePath = result.getDirName()+"/"+result.getFileName()+ ".csv";
        File file = new File(filePath);

        /* if the file already exists, the user will be prompted with one of two choices:
            - Overwrite the file (YES)
            - Specify a new file name (NO)
         */
        while (file.exists()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ButtonType.YES,ButtonType.CANCEL,ButtonType.NO);
            alert.setTitle("File selection");
            alert.setHeaderText("File already exists");
            alert.setContentText("A file with same name exists. Do you want to overwrite it?");

            Optional<ButtonType> r = alert.showAndWait();

            if (r.isPresent() && (r.get() == ButtonType.YES)) {
                break;
            } else if(r.isPresent() && (r.get() == ButtonType.CANCEL)){
                return false;
            } else {
                Dialog<String> fileNameDialog = new FileNameDialog();
                fileNameDialog.showAndWait();
                String fileDialogResult = fileNameDialog.getResult();
                if (fileDialogResult == null){
                    return false;
                }
                filePath = result.getDirName()+"/"+fileDialogResult+ ".csv";
                file = new File(filePath);
            }
        }

        return writeToFile(filePath,patientRegister);
    }

    /**
     * Helper method to write to a created file
     * @param filePath chosen file path to save the exported file
     * @param patientRegister is a register of all the patient
     * @return true, if the file successfully written to a csv file
     *         false, if it was not
     */
    public boolean writeToFile(String filePath, PatientRegister patientRegister){
        boolean status = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Collection<Patient> patients = patientRegister.getAllPatients();
            writer.write("firstName;lastName;diagnosis;generalPractitioner;socialSecurityNumber\n");

            for (Patient p : patients) {
                writer.write(p.toString() + "\n");
            }
            status = true;
        } catch (IOException e) {
            System.err.println("ERROR: IO exception while writing to a file " + filePath);
            status = false;
        }
        return status;
    }
}
