package stud.ntnu.IDATT2001.MappeDel2.task5;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Objectifies a single row from the table view in order to store them in the patient registry
 *
 * @author Thadshajini
 * @version 2020-05-05
 */
@Entity(name = "Patient")
public class PatientDb {
    @Id
    private String socialSecurityNumber;
    @Column
    private String firstName;
    private String lastName;
    private String diagnosis;
    private String generalPractitioner;


    /**
     * Constructor
     * @param firstName
     * @param lastName
     * @param generalPractitioner
     * @param socialSecurityNumber
     * @param diagnosis
     */
    public PatientDb(String firstName, String lastName, String generalPractitioner, String socialSecurityNumber, String diagnosis){
        setFirstName(firstName);
        setLastName(lastName);
        setGeneralPractitioner(generalPractitioner);
        setSocialSecurityNumber(socialSecurityNumber);
        setDiagnosis(diagnosis);
    }

    // Empty constructor
    public PatientDb() {

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
     * Mutator for socialSecurityNumber
     * @param socialSecurityNumber
     */
    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    /**
     * Mutator for firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Mutator for lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Mutator for diagnosis
     * @param diagnosis
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Mutator for generalPractitioner
     * @param generalPractitioner
     */
    public void setGeneralPractitioner(String generalPractitioner) {
        this.generalPractitioner = generalPractitioner;
    }

    /**
     * toString
     * @return social security number/first name/last name/diagnosis/general practitioner
     */
    @Override
    public String toString() {
        return "Patient{" +
                "socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", generalPractitioner='" + generalPractitioner + '\'' +
                '}';
    }
}
