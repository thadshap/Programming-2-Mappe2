package stud.ntnu.IDATT2001.MappeDel2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class Patient {
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String diagnosis;
    private String generalPractitioner;

    public Patient(String socialSecurityNumber, String firstName, String lastName, String diagnosis, String generalPractitioner) {
        if (socialSecurityNumber == null && firstName == null && lastName == null && diagnosis == null && generalPractitioner == null){
            socialSecurityNumber = "";
            firstName = "";
            lastName = "";
            diagnosis = "";
            generalPractitioner = "";
        }
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

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        if (socialSecurityNumber == null) {
            throw new IllegalArgumentException("Social security number cannot be null!!");
        }

        int length = socialSecurityNumber.trim().length();
        if (length == 0) {
            throw new IllegalArgumentException("Social security number cannot be empty.");
        }

        if (length<11 || length>11){
            throw new IllegalArgumentException("Social security number cannot be a negative integer or have over or under 11 digits.");
        }
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("First name should not be null!!");
        }

        if (firstName.trim().length() == 0) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Last name should not be null!!");
        }

        if (lastName.trim().length() == 0) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
        this.lastName = lastName;
    }

    public void setDiagnosis(String diagnosis) {
        if (diagnosis == null) {
            throw new IllegalArgumentException("Diagnosis should not be null!!");
        }

        if (diagnosis.trim().length() == 0) {
            throw new IllegalArgumentException("Diagnosis cannot be empty.");
        }
        this.diagnosis = diagnosis;
    }

    public void setGeneralPractitioner(String generalPractitioner) {
        if (generalPractitioner == null) {
            throw new IllegalArgumentException("General practitioner should not be null!!");
        }

        if (generalPractitioner.trim().length() == 0) {
            throw new IllegalArgumentException("General practitioner cannot be empty.");
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
