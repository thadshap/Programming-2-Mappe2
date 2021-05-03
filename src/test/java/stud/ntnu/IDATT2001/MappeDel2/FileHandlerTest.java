package stud.ntnu.IDATT2001.MappeDel2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import stud.ntnu.IDATT2001.MappeDel2.controller.FileHandler;
import stud.ntnu.IDATT2001.MappeDel2.controller.PatientRegister;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

/**
 * This class is a test class for the FileHandler class
 * and tests the methods to import and export data.
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

class FileHandlerTest {
    PatientRegister patientRegister;
    FileHandler fileHandler;

    @BeforeEach
    void initAll() {
        //Arrange
        patientRegister = new PatientRegister();
        fileHandler = new FileHandler();
    }

    @Nested
    class ReadFile {
        @Test
        @DisplayName("Reading a csv file existing directory")
        void readFromFileSuccessful() {
            //Act
            //Assume that PatientRegistry file already exists. Reads PatientRegstry.csv file and adds every patient into PatientRegister
            boolean status = fileHandler.fileReader(patientRegister, new File("src/main/resources/testFiles/PatientRegstry.csv"));

            //Asserts
            /*  checking if the fileReader could read the csv file and add the patient data from every column from csv file to PatientRegister,
                which should return true*/
            assertTrue(status);
            /*  checking if all the data from csv file got added inti PatientRegister by checking the size of
                the elements added in the register, which should be 104 */
            assertEquals(104, patientRegister.getAllPatients().size());
        }


        @Test
        @DisplayName("Reading a csv file from a non-existing directory")
        void readFromFileFailure() {
            //Act
            //trying to read a csv file from and add patients from csv file into PatientRegister
            boolean status = fileHandler.fileReader(patientRegister, new File("src/main/resources/hackerman/PatientRegstry.csv"));

            //Asserts
            //trying to read a existing file from a wrong path, which should return false
            assertFalse(status);
            //checking if it was added patients from PatientRegstry.csv to patientRegister, which should be 0
            assertEquals(0, patientRegister.getAllPatients().size());
        }
    }
    @Nested
    class WritingFile {
        @Test
        @DisplayName("Writing a csv file to a existing directory")
        void writeToFileSuccessful() {
            //Act
            //trying to write a csv file by setting in every patient from PatientRegister in each column in the csv file
            boolean writeToFile = fileHandler.writeToFile("src/main/resources/testFiles/UsedByFileHandler.csv", patientRegister);

            //Asserts
            //trying to write a csv file into a existing path, which should return true
            assertTrue(writeToFile);
        }

        @Test
        @DisplayName("Writing a csv file to a non-existing directory")
        void writeToFileFailure() {
            //Act
            //trying to write a csv file by setting in every patient from PatientRegister in each column in the csv file
            boolean writeToFile = fileHandler.writeToFile("src/main/resources/hackerman/UsedByFileHandler.csv", patientRegister);

            //Asserts
            //trying to write a csv file into a non-existing path, which should return false
            assertFalse(writeToFile);
        }
    }
}