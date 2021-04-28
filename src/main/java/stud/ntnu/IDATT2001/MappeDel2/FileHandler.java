package stud.ntnu.IDATT2001.MappeDel2;


import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;


public class FileHandler {
    FileChooser fileChooser = new FileChooser();

    public void importData(String title, PatientRegister patientRegister) {
        fileChooser.setTitle(title);
        //implemented filter instead of letting the user choose wrong filetype. So, it is not necessary to create, for instance
        //an alert dialog box to give the user a message about invalid filetype.
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV file (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
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
            }
            catch (IOException e){
               e.printStackTrace();
            }
        }
    }

    public void exportData(PatientRegister patientRegister){
        Dialog<String> fileDialog = new FileDialog();
        fileDialog.showAndWait();
        String result = fileDialog.getResult();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(result + ".csv"))) {
            Collection<Patient> patients = patientRegister.getAllPatients();
            writer.write("firstName;lastName;diagnosis;generalPractitioner;socialSecurityNumber\n");

            for (Patient p : patients) {
                writer.write(p.toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
