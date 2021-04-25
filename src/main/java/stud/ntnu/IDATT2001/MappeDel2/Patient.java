package stud.ntnu.IDATT2001.MappeDel2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Objects;

public class Patient {
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String diagnosis;
    private String generalPractitioner;

    private HashMap<String,String> errorMessages;

    public Patient(String socialSecurityNumber, String firstName, String lastName, String diagnosis, String generalPractitioner) {
        errorMessages = new HashMap<>();
        setSocialSecurityNumber(socialSecurityNumber);
        setFirstName(firstName);
        setLastName(lastName);
        setDiagnosis(diagnosis);
        setGeneralPractitioner(generalPractitioner);
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getGeneralPractitioner() {
        return generalPractitioner;
    }

    public HashMap<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        if (socialSecurityNumber == null || socialSecurityNumber.trim().length() == 0) {
            errorMessages.put("socialSecurityNumber","Social security number cannot be null or empty!!");
        }

        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().length() == 0) {
            errorMessages.put("firstName","First name cannot be null or empty!!");
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().length() == 0) {
            errorMessages.put("lastName","Last name cannot be null or empty!!");
        }

        this.lastName = lastName;
    }

    public void setDiagnosis(String diagnosis) {
        if (diagnosis == null || diagnosis.trim().length() == 0) {
            errorMessages.put("diagnosis","Diagnosis cannot be null or empty!!");
        }

        this.diagnosis = diagnosis;
    }

    public void setGeneralPractitioner(String generalPractitioner) {
        if (generalPractitioner == null || generalPractitioner.trim().length() == 0) {
            errorMessages.put("generalPractitioner","General practitioner cannot be null or empty!!");
        }

        this.generalPractitioner = generalPractitioner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getSocialSecurityNumber(), patient.getSocialSecurityNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSocialSecurityNumber());
    }
}
