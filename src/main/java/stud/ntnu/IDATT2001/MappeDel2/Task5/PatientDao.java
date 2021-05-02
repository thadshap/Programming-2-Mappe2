package stud.ntnu.IDATT2001.MappeDel2.Task5;

import stud.ntnu.IDATT2001.MappeDel2.PatientRegister;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PatientDao {
    private final EntityManagerFactory emFactory;
    public PatientDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    private EntityManager getEM(){
        return emFactory.createEntityManager();
    }

    private void closeEM(EntityManager em){
        if (em != null && em.isOpen()) em.close();
    }

    //get all the patients
    public void loadDatabase(PatientRegister patientRegister){
        EntityManager em = getEM();
        try{
            Query q = em.createQuery("SELECT c FROM Patient c");
            List<Patient> allPatients =  q.getResultList();

            allPatients.forEach(patient -> patientRegister.addPatient(convertPatient(patient)));
        }finally{
            closeEM(em);
        }
    }

    private stud.ntnu.IDATT2001.MappeDel2.Patient convertPatient(Patient patient){
        stud.ntnu.IDATT2001.MappeDel2.Patient p = new stud.ntnu.IDATT2001.MappeDel2.Patient(patient.getSocialSecurityNumber(),patient.getFirstName(),patient.getLastName(),patient.getDiagnosis(),patient.getGeneralPractitioner());
        return p;
    }

    public void saveDatabase(){

    }

}
