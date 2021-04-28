package stud.ntnu.IDATT2001.MappeDel2;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;

public class FileHandler extends Dialog {
    FileChooser fileChooser = new FileChooser();

    TextField fileName = new TextField();

    TextField directoryName = new TextField();

    public void importData(String title, PatientRegister patientRegister) {
        fileChooser.setTitle(title);
        //implemented filter instead of letting the user choose wrong filetype. So, it is not necessary to create, for instance
        //an alert dialog box to give the user a message about invalid filetype.
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV file (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            try(Scanner scanner = new Scanner(file)) {
                String metaData = scanner.nextLine(); // firstName;lastNam;generalPractitioner;socialSecurityNumber
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
                     String[] data = scanner.nextLine().split(";"); //{"1212", "Dr. Yang", "Panda", "Hansen"}

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
        //String fileName = "test.csv";
        // hent filnavn og plassering fra bruker ved hjelp av en FÆNSI drop box
             // later som om vi skal skrive til en gitt fil å nåværende mappe





        // gyldig filnavn: try catch til å åpne fil
        //BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))
           Dialog<String> fileDialog = new FileDialog();
           fileDialog.showAndWait();
           String result = fileDialog.getResult();
        System.out.println("handler export" + "" +result);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(result + ".csv"))) {
                    Collection<Patient> patients = patientRegister.getAllPatients();
                    writer.write("firstName;lastName;diagnosis;generalPractitioner;socialSecurityNumber\n");

                    for (Patient p : patients) {
                        writer.write(p.toString() + "\n");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

        // iterer gjennom patient register og lagre hver pasient som en linje
        
    }

}
