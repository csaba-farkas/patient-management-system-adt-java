
package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.enums.Gender;
import model.enums.MaritalStatus;
import java.util.Calendar;

/**
 * <h1>Patient --- Date of last modification: 27-Mar-2015</h1>
 * This is the Patient class that is stored in both of the data structures.
 * 
 * @author Csaba Farkas R00117945
 */
public class Patient implements Serializable {

    //Instance variables
    private final String patientID;         //Indicates patient ID that cannot be changed
    private String name;                    //Indicates name 
    private String address;                 //Indicates address
    private int age;                        //Indicates age
    private String gender;                  //Indicates gender
    private String maritalStatus;           //Indicates marital status
    private Calendar admitDate;             //Indicates date of admission
    private Calendar dischargeDate;         //Indicates date of discharge
    private String doctorsName;             //Indicates name of doctor treating patient
    private String department;              //Indicates department where patient was treated
    private DateFormat myFormat;            //Indicates a DateFormat object to display dates
    private long numberOfDaysInHospital;    //Indicates the number of days patient spent in hospital
    
    /**
     * Constructor 1
     * Standard implementation of patient class. Values of parameters are assigned
     * to the appropriate instance variables.
     * 
     * @param patientID Patient identification number.
     * @param name Patient's name.
     * @param address Patient's address.
     * @param age Patient's age.
     * @param gender Patient's gender.
     * @param admitDate Date of admission.
     * @param dischargeDate Date of discharge.
     * @param maritalStatus Patient's marital status.
     * @param doctorsName Name of doctor who treated patient.
     * @param department Name of department where patient was treated.
     */
    public Patient(String patientID, 
                   String name, 
                   String address, 
                   int age, 
                   Gender gender, 
                   Calendar admitDate, 
                   Calendar dischargeDate, 
                   MaritalStatus maritalStatus,
                   String doctorsName,
                   String department) {
        
        this.patientID = patientID;
        this.name = name;
        this.address = address;
        this.age = age;
        
        //gender is an enum
        switch(gender) {
            case MALE:
                this.gender = "Male";
                break;
            case FEMALE:
                this.gender = "Female";
                break;
        }
        this.admitDate = admitDate;
        this.dischargeDate = dischargeDate;
        
        //maritalStatus is an enum
        switch(maritalStatus) {
            case SINGLE:
                this.maritalStatus = "Single";
                break;
            case MARRIED:
                this.maritalStatus = "Married";
                break;
            case DIVORCED:
                this.maritalStatus = "Divorced";
                break;
            case CIVIL_PARTNERSHIP:
                this.maritalStatus = "In civil partnership";
                break;
            case WIDOW_WIDOWER:
                switch(gender) {
                    case MALE:
                        this.maritalStatus = "Widower";
                        break;
                    case FEMALE:
                        this.maritalStatus = "Widow";
                }  
        }
        this.doctorsName = doctorsName;
        this.department = department;
        this.myFormat = new SimpleDateFormat("dd/MM/yyyy");
        long difference = this.dischargeDate.getTimeInMillis() - this.admitDate.getTimeInMillis();
        this.numberOfDaysInHospital = difference / 1000 / 60 / 60/ 24;
    } 
    
    /**
     * Constructor 2
     * Creates an 'empty' Patient object. It is used during the construction of 
     * the linked list data structure.
     */
    public Patient() {
        this.patientID = null;
    }
    
    /**
     * Getter method - date of admission.
     * 
     * @return admitDate 
     */
    public Calendar getAdmitDate() {
        return this.admitDate;
    }

    /**
     * Getter method - date of discharge.
     * 
     * @return dischargeDate
     */
    public Calendar getDischargeDate() {
        return this.dischargeDate;
    }

    /**
     * Getter method - marital status.
     * 
     * @return maritalStatus
     */
    public String getMaritalStatus() {
        return this.maritalStatus;
    }

    /**
     * Getter method - patient ID.
     * 
     * @return patientID 
     */
    public String getPatientID() {
        return this.patientID;
    }

    /**
     * Getter method - name.
     * 
     * @return name 
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method - address.
     * 
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Getter method - age.
     * 
     * @return age 
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Getter method - gender.
     * 
     * @return gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Setter method - name of treating doctor.
     * 
     * @return doctorsName 
     */
    public String getDoctorsName() {
        return this.doctorsName;
    }

    /**
     * Getter method - department where patient was treated.
     * 
     * @return department
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * Getter method - total number of days spent in hospital.
     * 
     * @return numberOfDaysInHospital
     */
    public long getNumberOfDaysInHospital() {
        return this.numberOfDaysInHospital;
    }
    
    /**
     * Setter method - name
     * 
     * @param name Patient's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method - address
     * 
     * @param address Patient's address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Setter method - age.
     * 
     * @param age Patient's age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Setter method - gender.
     * 
     * @param gender Patient's gender.
     */
    public void setGender(Gender gender) {
        switch(gender) {
            case MALE:
                this.gender = "Male";
                break;
            case FEMALE:
                this.gender = "Female";
                break;
        }
    }

    /**
     * Setter method - marital status.
     * 
     * @param maritalStatus Patient's marital status.
     */
    public void setMaritalStatus(MaritalStatus maritalStatus) {
        switch(maritalStatus) {
            case SINGLE:
                this.maritalStatus = "Single";
                break;
            case MARRIED:
                this.maritalStatus = "Married";
                break;
            case DIVORCED:
                this.maritalStatus = "Divorced";
                break;
            case CIVIL_PARTNERSHIP:
                this.maritalStatus = "In civil partnership";
                break;
            case WIDOW_WIDOWER:
                switch(this.getGender()) {
                    case "Male":
                        this.maritalStatus = "Widower";
                        break;
                    case "Female":
                        this.maritalStatus = "Widow";
                }  
        }
    }

    /**
     * Setter method - department.
     * 
     * @param department Department where patient was treated.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Setter method - name of treating doctor.
     * 
     * @param doctorsName Doctor who treated patient.
     */
    public void setDoctorsName(String doctorsName) {
        this.doctorsName = doctorsName;
    }

    /**
     * Method that prints the date of admission to the screen in an appropriate
     * format.
     */
    public void printAdmitDate() {
        System.out.println(this.myFormat.format(this.admitDate.getTime()));
    }
    
    /**
     * Method that prints the date of discharge to the screen in an appropriate
     * format.
     */
    public void printDischargeDate() {
        System.out.println(this.myFormat.format(this.dischargeDate.getTime()));
    }
    
    /**
     * Override toString method.
     * Prints all of the attributes to screen in  a format so it can be used in
     * the tables.
     * 
     * @return a String object containing all of details of a patient.
     */
    @Override
    public String toString() {
        
        return String.format("%s %-10s %1s %-25s %5s %-35s %1s %-5s %1s %-10s %1s %-20s %1s %-20s %1s %-15s %1s %-20s %1s %-20s %2s %n", "|", 
                             this.patientID, "|", this.name, "|", this.address, "|", this.age, "|", this.gender, "|", this.maritalStatus,
                             "|", this.doctorsName, "|", this.department, "|", myFormat.format(this.admitDate.getTime()), "|", 
                             myFormat.format(this.dischargeDate.getTime()), "|");
        
        
    }
}
