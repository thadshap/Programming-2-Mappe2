package stud.ntnu.IDATT2001.MappeDel2;

import org.junit.jupiter.api.Test;
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

    /**
     * Assume that PatientRegistry file already exists. This tests checks
     * if all the data is retrieved correctly from PatientRegistry file.
     */
    @Test
    void readFromFileSuccessful() {
        //Arrange
        FileHandler fileHandler = new FileHandler();
        PatientRegister patientRegister = new PatientRegister();

        //Act
        boolean status = fileHandler.fileReader(patientRegister, new File("src/main/resources/testFiles/PatientRegstry.csv"));

        //Asserts
        assertTrue(status);
        assertEquals(104,patientRegister.getAllPatients().size());
    }

    /**
     *
     */
    @Test
    void readFromFileFailure() {
        //Arrange
        FileHandler fileHandler = new FileHandler();
        PatientRegister patientRegister = new PatientRegister();

        //Act
        boolean status = fileHandler.fileReader(patientRegister, new File("src/main/resources/hackerman/PatientRegstry.csv"));

        //Asserts
        assertFalse(status);
        assertEquals(0,patientRegister.getAllPatients().size());
    }

    /**
     *
     */
    @Test
    void writeToFileSuccessful() {
        //Arrange
        FileHandler fileHandler = new FileHandler();
        PatientRegister patientRegister = new PatientRegister();

        //Act
        boolean writeToFile = fileHandler.writeToFile("src/main/resources/testFiles/UsedByFileHandler.csv",patientRegister);

        //Asserts
        assertTrue(writeToFile);
    }

    /**
     *
     */
    @Test
    void writeToFileFailure() {
        //Arrange
        FileHandler fileHandler = new FileHandler();
        PatientRegister patientRegister = new PatientRegister();

        //Act
        boolean writeToFile = fileHandler.writeToFile("src/main/resources/hackerman/UsedByFileHandler.csv",patientRegister);

        //Asserts
        assertFalse(writeToFile);
    }
}