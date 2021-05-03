package stud.ntnu.IDATT2001.MappeDel2.model;

import java.util.Objects;

/**
 * Objectifies a single row from the table view in order to store them in the patient registry
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class Patient {
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String diagnosis;
    private String generalPractitioner;

    /*
        I choose to handle all the exception related to the patient with error counter instead of throwing exceptions.
        Error counter depicts any potential errors occurring during creation or mutation.
        The reason why I chose to handle these exceptions in this particular way is because in the
        controller the error counter will be used to send unique error messages in the status bar. In this particular case,
        I find this way to handle exception easier then throwing exceptions and then handling them in a try-catch, since
        we are only checking for null values and empty strings.
     */
    private int errorCounter;

    /**
     * Constructor
     * @param firstName
     * @param lastName
     * @param generalPractitioner
     * @param socialSecurityNumber
     */
    public Patient(String firstName, String lastName, String generalPractitioner, String socialSecurityNumber){
        errorCounter = 0;
        setFirstName(firstName);
        setLastName(lastName);
        setGeneralPractitioner(generalPractitioner);
        setSocialSecurityNumber(socialSecurityNumber);
    }

    /**
     * Constructor
     * @param socialSecurityNumber
     * @param firstName
     * @param lastName
     * @param diagnosis
     * @param generalPractitioner
     */
    public Patient(String socialSecurityNumber, String firstName, String lastName, String diagnosis, String generalPractitioner) {
        this(firstName,lastName,generalPractitioner,socialSecurityNumber);
        setDiagnosis(diagnosis);
    }

    /**
     * Accessor for socialSecurityNumber
     * @return
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Accessor for firstName
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Accessor for lastName
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Accessor for diagnosis
     * @return
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Accessor for generalPractitioner
     * @return
     */
    public String getGeneralPractitioner() {
        return generalPractitioner;
    }

    /**
     * Accessor for errorCounter
     * @return
     */
    public int getErrorCounter() {
        return errorCounter;
    }

    /**
     * Immutable initializer for socialSecurityNumber
     * @param socialSecurityNumber
     */
    private void setSocialSecurityNumber(String socialSecurityNumber) {
        if (socialSecurityNumber == null || socialSecurityNumber.trim().length() == 0) {
            errorCounter++;
        }

        this.socialSecurityNumber = socialSecurityNumber;
    }

    /**
     * Mutator for firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().length() == 0) {
            errorCounter++;
        }

        this.firstName = firstName;
    }

    /**
     * Mutator for lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().length() == 0) {
            errorCounter++;
        }

        this.lastName = lastName;
    }

    /**
     * Mutator for diagnosis
     * @param diagnosis
     */
    public void setDiagnosis(String diagnosis) {
        if (diagnosis == null || diagnosis.trim().length() == 0) {
            errorCounter++;
            this.diagnosis = "";
        } else {
            this.diagnosis = diagnosis;
        }
    }

    /**
     * Mutator for generalPractitioner
     * @param generalPractitioner
     */
    public void setGeneralPractitioner(String generalPractitioner) {
        if (generalPractitioner == null || generalPractitioner.trim().length() == 0) {
            errorCounter++;
            this.generalPractitioner = "";
        } else{
            this.generalPractitioner = generalPractitioner;
        }
    }

    /**
     * Equals method to check if two patient are equal by their social security number
     * @param o the patient object which will be checked if the same object exists
     * @return true if a registered patient with an existing social security number
     *         false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(getSocialSecurityNumber(), patient.getSocialSecurityNumber());
    }

    /**
     * toString
     * @return first name;last name;diagnosis;general practitioner;social security number
     */
    @Override
    public String toString() {
        return   firstName + ';' +
                 lastName + ';' +
                 diagnosis + ';' +
                 generalPractitioner + ';'+
                 socialSecurityNumber;
    }
}
