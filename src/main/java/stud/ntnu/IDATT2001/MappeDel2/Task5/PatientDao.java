package stud.ntnu.IDATT2001.MappeDel2.Task5;

import stud.ntnu.IDATT2001.MappeDel2.PatientRegister;
import stud.ntnu.IDATT2001.MappeDel2.Task5.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
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
            String sql = "SELECT c FROM Patient c";
            Query q = em.createQuery(sql);
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

    private Patient reverseConvertPatient(stud.ntnu.IDATT2001.MappeDel2.Patient patient){
        Patient p = new Patient(patient.getFirstName(),patient.getLastName(),patient.getGeneralPractitioner(), patient.getSocialSecurityNumber(), patient.getDiagnosis());
        return p;
    }

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
}
