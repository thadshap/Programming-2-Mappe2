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

    private EntityManager getEM(){
        return emFactory.createEntityManager();
    }

    private void closeEM(EntityManager em){
        if (em != null && em.isOpen()) em.close();
    }

    /**
     * Get all the patients from database and adds them into PatientRegister
     * @param patientRegister
     */
    public void loadDatabase(PatientRegister patientRegister){
        EntityManager em = getEM();
        try{
            String sql = "SELECT c FROM Patient c";
            Query q = em.createQuery(sql);
            List<PatientDb> allPatientDbs =  q.getResultList();

            allPatientDbs.forEach(patientDb -> patientRegister.addPatient(convertPatient(patientDb)));
        }finally{
            closeEM(em);
        }
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
     * Helper method to convert from patinet (stud.ntnu.IDATT2001.MappeDel2.Task5.Patient)
     * to stud.ntnu.IDATT2001.MappeDel2.model.Patient
     * @param patientDb
     * @return the patient object from stud.ntnu.IDATT2001.MappeDel2.model.Patient
     */
    private Patient convertPatient(PatientDb patientDb){
        Patient p = new Patient(patientDb.getSocialSecurityNumber(), patientDb.getFirstName(), patientDb.getLastName(), patientDb.getDiagnosis(), patientDb.getGeneralPractitioner());
        return p;
    }

    /**
     * Helper method to convert from patinet (stud.ntnu.IDATT2001.MappeDel2.model.Patient)
     * to stud.ntnu.IDATT2001.MappeDel2.Task5.Patient
     * @param patient
     * @return the patient object from stud.ntnu.IDATT2001.MappeDel2.Task.Patient
     */
    private PatientDb reverseConvertPatient(Patient patient){
        PatientDb p = new PatientDb(patient.getFirstName(),patient.getLastName(),patient.getGeneralPractitioner(), patient.getSocialSecurityNumber(), patient.getDiagnosis());
        return p;
    }
}
