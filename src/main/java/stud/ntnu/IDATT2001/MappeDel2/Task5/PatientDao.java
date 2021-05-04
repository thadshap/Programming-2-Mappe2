package stud.ntnu.IDATT2001.MappeDel2.task5;

import stud.ntnu.IDATT2001.MappeDel2.model.Patient;
import stud.ntnu.IDATT2001.MappeDel2.controller.PatientRegister;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java.util.List;

/**
 * PatientDao is a class that contains all the methods used to perform operations in relation to the database
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class PatientDao {
    private final EntityManagerFactory emFactory;

    //Constructor
    public PatientDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public EntityManager getEM(){
        return emFactory.createEntityManager();
    }

    public void closeEM(EntityManager em){
        if (em != null && em.isOpen()) em.close();
    }

    /**
     * Get all the patients from database and adds them into PatientRegister
     * @param patientRegister
     * @return true if the data was successfully loaded from database
     *         false if not
     */
    public boolean loadDatabase(PatientRegister patientRegister){
        EntityManager em = getEM();
        boolean status = false;
        try {
            String sql = "SELECT c FROM Patient c";
            Query q = em.createQuery(sql);
            List<PatientDb> allPatientDbs = q.getResultList();
            allPatientDbs.forEach(patientDb -> patientRegister.addPatient(convertPatient(patientDb)));
            status =true;
        }catch (Exception e){
            e.printStackTrace();
            status = false;
        } finally{
            closeEM(em);
        }
        return status;
    }

    /**
     * Merging patient data from PatientRegister into database
     * @param patientRegister
     */
    public void saveDatabase(PatientRegister patientRegister){
        EntityManager em = getEM();
        try{
            em.getTransaction().begin();
            patientRegister.getAllPatients().forEach(patient -> em.merge(reverseConvertPatient(patient)));

            em.getTransaction().commit();//store in database


        }finally{
            closeEM(em);
        }
    }

    /**
     * Checks if a patient with given ssn exists in database
     * @param ssn
     * @return true if the patient exists anf false if not
     * @throws Exception
     */
    public boolean exists(String ssn) throws Exception{
        try {
            return (getEM().find(PatientDb.class,ssn) != null);
        } catch (Exception e){
            throw new Exception("Unable to check if patient with social security number '" + ssn + "' exists: " + e.getMessage());
        }

    }

    /**
     * Helper method to convert from patinet (stud.ntnu.IDATT2001.MappeDel2.Task5.Patient)
     * to stud.ntnu.IDATT2001.MappeDel2.model.Patient
     * @param patientDb
     * @return the patient object from stud.ntnu.IDATT2001.MappeDel2.model.Patient
     */
    public static Patient convertPatient(PatientDb patientDb){
        Patient p = new Patient(patientDb.getSocialSecurityNumber(), patientDb.getFirstName(), patientDb.getLastName(), patientDb.getDiagnosis(), patientDb.getGeneralPractitioner());
        return p;
    }

    /**
     * Helper method to convert from patinet (stud.ntnu.IDATT2001.MappeDel2.model.Patient)
     * to stud.ntnu.IDATT2001.MappeDel2.Task5.Patient
     * @param patient
     * @return the patient object from stud.ntnu.IDATT2001.MappeDel2.Task.Patient
     */
    public static PatientDb reverseConvertPatient(Patient patient){
        PatientDb p = new PatientDb(patient.getFirstName(),patient.getLastName(),patient.getGeneralPractitioner(), patient.getSocialSecurityNumber(), patient.getDiagnosis());
        return p;
    }


}
