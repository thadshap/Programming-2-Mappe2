package stud.ntnu.IDATT2001.MappeDel2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
        /**
         * Made a new patient object and added it to the patient registry. This test checks if
         * that patient was successfully added with the add addPatient method in PatientRegistry.
         */
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

        /**
         * Made two patient objects where one of the was a duplicate of the other one, which means
         * that the duplicate one should not be added into the patient registry.
         * This test shows that the duplicate one was not added by the addPatient method since
         * it returned false. But the first patient was possible to added.
         */
        @Test
        @DisplayName("Add an existing patient")
        void addDuplicatePatient() {
            //Act
            patientRegister.addPatient(patient);
            boolean addDuplicatePatient = patientRegister.addPatient(duplicatePatient);

            //Assert
            assertFalse(patientRegister.addPatient(patient));
            assertFalse(addDuplicatePatient);
        }
    }
    @org.junit.jupiter.api.Nested
    class RemovePatientTest {
        /**
         * Added a patient in patient registry and then removed it. Then iterated through
         * the registry to check in the assert if the removePatient method worked. If the
         * patient was removed then the result would give an empty string, if not,
         * the information about the added patient would be a string.
         */
        @Test
        @DisplayName("Remove an existing patient from register")
        void removeExistingPatient() {
            //Act
            patientRegister.addPatient(patient);
            boolean removePatient = patientRegister.removePatient(patient);

            //Assert
            assertTrue(removePatient);
            assertFalse(patientRegister.getAllPatients().contains(patient));
        }

        @Test
        @DisplayName("Remove a non-existing patient from register")
        void removeNotExistingPatient() {
            //Act and Assert
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
