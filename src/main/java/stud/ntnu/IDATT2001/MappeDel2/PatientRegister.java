package stud.ntnu.IDATT2001.MappeDel2;

import java.util.ArrayList;
import java.util.Collection;

public class PatientRegister {
    private ArrayList<Patient> patients;

    public PatientRegister() {
        patients = new ArrayList<>();
    }

    public boolean addPatient(Patient newPatient) {

        if (newPatient != null && !patients.stream().anyMatch(p -> p.equals(newPatient))) {
           return patients.add(newPatient);
        }
        return false;
    }

    public void removePatient(Patient patient){this.patients.remove(patient);}

    public Collection<Patient> getAllPatients(){return this.patients;}
}
