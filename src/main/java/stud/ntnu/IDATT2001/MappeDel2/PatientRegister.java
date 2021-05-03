package stud.ntnu.IDATT2001.MappeDel2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A registry contains information about patients
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class PatientRegister {
    private ArrayList<Patient> patients;

    /**
     * Constructor
     */
    public PatientRegister() {
        patients = new ArrayList<>();
    }

    /**
     * Add a new patient
     * @param newPatient
     * @return true if the patient was successfully registered
     */
    public boolean addPatient(Patient newPatient) {

        if (newPatient != null && !patients.stream().anyMatch(p -> p.equals(newPatient))) {
           return patients.add(newPatient);
        }
        return false;
    }

    /**
     * Remove a patient
     * @param patient
     */
    public boolean removePatient(Patient patient){return this.patients.remove(patient);}

    /**
     * Accessor for getting all patients
     * @return a collection og patients
     */
    public Collection<Patient> getAllPatients(){return this.patients;}
}
