package stud.ntnu.IDATT2001.MappeDel2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import stud.ntnu.IDATT2001.MappeDel2.model.Patient;
import stud.ntnu.IDATT2001.MappeDel2.controller.PatientRegister;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PatientRegisterTest class for PatientRegister. This class is important to test the methods of
 * since these methods are business-critical functionality considering that they are used in several
 * places in the application/program.
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

class PatientRegisterTest {
    PatientRegister patientRegister;
    Patient patient;
    Patient duplicatePatient;

    @BeforeEach
    void initAll() {
        //Arrange
        patientRegister = new PatientRegister();
        patient = new Patient("12345678910", "Karen", "Lyst", "Syk", "Lege");
        duplicatePatient = new Patient("12345678910", "Marin", "Lyst", "Syk", "Fru lege");
    }

    @org.junit.jupiter.api.Nested
    class AddPatientTest {
        @Test
        @DisplayName("Add a non-existing patient")
        void addUniquePatient() {
            //Act
            //adding a patient that has not been added in the register before
            boolean addPatient = patientRegister.addPatient(patient);

            //Assert
            //checking if the addPatient method in PatientRegister adds a patient, which should work (return true)
            assertTrue(addPatient);
            //checking if the patient was added inti the registry, which should work (return true)
            assertTrue(patientRegister.getAllPatients().contains(patient));
        }

        @Test
        @DisplayName("Add an existing patient")
        void addDuplicatePatient() {
            //Act
            //adding a patient that has not been added in the register before
            patientRegister.addPatient(patient);
            //adding a patient with the same social security number as a another added patient
            boolean addDuplicatePatient = patientRegister.addPatient(duplicatePatient);

            //Assert
            //trying to add a patient that has already been added before, which should nor work (return false)
            assertFalse(patientRegister.addPatient(patient));
            /* trying to add a patient that has the same social security number as another
               previously added patient, which should not work (return false)*/
            assertFalse(addDuplicatePatient);
        }
    }
    @org.junit.jupiter.api.Nested
    class RemovePatientTest {
        @Test
        @DisplayName("Remove an existing patient from register")
        void removeExistingPatient() {
            //Act
            //adding a patient that has not been added in the register before
            patientRegister.addPatient(patient);

            //Assert
            //trying to remove the patient that was added above, which should return true
            assertTrue(patientRegister.removePatient(patient));
            //checking if the patient register still contains that patient, which should be false
            assertFalse(patientRegister.getAllPatients().contains(patient));
        }

        @Test
        @DisplayName("Remove a non-existing patient from register")
        void removeNotExistingPatient() {
            //Act and Assert
            //trying to remove a non-added patient, which should return false
            assertFalse(patientRegister.removePatient(patient));
        }
    }

    @Nested
    class GetAllPatient {
        @Test
        @DisplayName("Check if getAllPatient method gets all patient")
        void getAllPatients() {
            //Act
            //adding a patient
            patientRegister.addPatient(patient);

            //converts from Collection to string
            String result = "";
            for (Patient p : patientRegister.getAllPatients()) {
                result += p;
            }

            //Assert
            //checking if the getAllPatient gets all the patient in the register
            assertEquals("Karen;Lyst;Syk;Lege;12345678910", result);
        }
    }
}
