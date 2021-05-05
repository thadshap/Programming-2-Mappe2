package stud.ntnu.IDATT2001.MappeDel2.task5;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.*;
import stud.ntnu.IDATT2001.MappeDel2.controller.PatientRegister;
import stud.ntnu.IDATT2001.MappeDel2.model.Patient;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is a test class for the PatientDatabase class
 * and tests the methods to load and save data into database.
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

class PatientDaoTest {
    private static EntityManagerFactory emf;
    private static EntityManager entityManager;
    private static PatientDao patientDao;
    private static PatientDb patientDb;
    private static PatientRegister patientRegister;
    private static Patient p;

    @BeforeAll
    static void initResources() {
        //Arrange
        patientRegister = new PatientRegister();
        emf = Persistence.createEntityManagerFactory("st‐olavs‐register-test");
        patientDao = new PatientDao(emf);
        entityManager = patientDao.getEM();
        patientDb = new PatientDb("Ida", "Hansen", "Dr.Klarksen", "12345678910", "Syk");
        p = patientDao.convertPatient(patientDb);
        //adding patientDb into PatientRegister
        patientRegister.addPatient(p);
    }

    @AfterAll
    static void closerESOURCES() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Nested
    class SaveDatabaseTest {
        @Test
        @DisplayName("Save patients from PatientRegister to database")
        void savePatientToDatabase() {
            try {
                //Act
                //trying to save every patients from PatientRegister to database
                patientDao.saveDatabase(patientRegister);
                //Assert
                //checking is patientDb's ssn exists in the database, which should return true
                assertTrue(patientDao.exists(patientDb.getSocialSecurityNumber()));
                //checking if patientDb got added into PatientRegister, which should be true
                assertTrue(patientRegister.getAllPatients().contains(p));
            } catch (Exception e){
                fail();
            }
        }

        @Test
        @DisplayName("Save a non-patient to database")
        void saveNonPatientToDatabase(){
            try{
                //Act
                entityManager.getTransaction().begin();
                //Assert
                //trying to add another object then patientRegistry into database, which should throw IllegalArgumentException.
                assertThrows(IllegalArgumentException.class, ()->entityManager.merge("Patient"));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Nested
    class LoadDatabaseTest {
        @Test
        @DisplayName("load existing patient from database")
        void loadExistingPatientFromDatabase() {
            try {
                //Act
                //save every patients from PatientRegister to database
                patientDao.saveDatabase(patientRegister);
                //Assert
                //checking if the method loadDatabase could load the data from the database, which should return true
                assertTrue(patientDao.loadDatabase(patientRegister));
                //checking if patientDb got added into PatientRegister, which should be true
                assertTrue(patientRegister.getAllPatients().contains(p));
            } catch (Exception e){
                fail();
            }
        }

        @Test
        @DisplayName("load non-existing patient from database")
        void loadNonExistingPatientFromDatabase(){
            try{
                //Act
                //trying to find a patient that does not exist
                PatientDb patientDb1 = entityManager.find(PatientDb.class,"13123986458");
                //Assert
                //checking if a patient exists with a non-existing social security number, which should return null
                assertNull(patientDb1);
            } catch (Exception e){
                fail();
            }
        }
    }
}