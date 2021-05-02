package stud.ntnu.IDATT2001.MappeDel2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

class PatientRegisterTest {

    /**
     * Made a new patient object and added it to the patient registry. This test checks if
     * that patient was successfully added with the add addPatient method in PatientRegistry.
     */
    @Test
    void addUniquePatient() {
        //Arrange
        Patient patient = new Patient("12345678910","Karen","Lyst","Syk", "Lege");
        PatientRegister patientRegister = new PatientRegister();

        //Act
        boolean addPatient = patientRegister.addPatient(patient);

        //Assert
        assertTrue(addPatient);
    }

    /**
     * Made two patient objects where one of the was a duplicate of the other one, which means
     * that the duplicate one should not be added into the patient registry.
     * This test shows that the duplicate one was not added by the addPatient method since
     * it returned false. But the first patient was possible to added.
     */
    @Test
    void addDuplicatePatient() {
        //Arrange
        Patient patient = new Patient("12345678910","Karen","Lyst","Syk", "Lege");
        Patient patientDuplicate = new Patient("12345678910","Marin","Lyst","Syk","Fru lege");
        PatientRegister patientRegister = new PatientRegister();

        //Act
        boolean addPatient = patientRegister.addPatient(patient);
        boolean addDuplicatePatient = patientRegister.addPatient(patientDuplicate);

        //Assert
        assertTrue(addPatient);
        assertFalse(addDuplicatePatient);
    }

    /**
     * Added a patient in patient registry and then removed it. Then iterated through
     * the registry to check in the assert if the removePatient method worked. If the
     * patient was removed then the result would give an empty string, if not,
     * the information about the added patient would be a string.
     */
    @Test
    void removePatient() {
        //Arrange
        Patient patient = new Patient("12345678910","Karen","Lyst","Syk", "Lege");
        PatientRegister patientRegister = new PatientRegister();

        //Act
        patientRegister.addPatient(patient);
        patientRegister.removePatient(patient);

        String result = "";
        for (Patient p : patientRegister.getAllPatients()){
            result += p;
        }

        //Assert
        assertEquals("",result);
    }

    /**
     * Adds a patient and checks if the getAllPatients methode can get all the patients from
     * the registry. If the method gets all the patient, then the patient information would
     * be listed on on the form of patient's toString method.
     */
    @Test
    void getAllPatients() {
        //Arrange
        Patient patient = new Patient("12345678910","Karen","Lyst","Syk", "Lege");
        PatientRegister patientRegister = new PatientRegister();

        //Act
        patientRegister.addPatient(patient);
        
        String result = "";
        for (Patient p : patientRegister.getAllPatients()){
            result += p;
        }

        //Assert
        assertEquals("Karen;Lyst;Syk;Lege;12345678910",result);
    }
}