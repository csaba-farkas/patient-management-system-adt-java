
package model;

import interfaces.PatientManagementInterface;
import java.util.Calendar;
import model.exceptions.PatientListIsEmptyException;
import model.exceptions.PatientListIsFullException;
import model.exceptions.PatientNotInTheListException;
import model.exceptions.SortingException;

/**
 * <h1>PatientArrayList --- Date of last modification: 02-May-2015</h1>
 * This is a data structure that implements PatientManagementInterface interface.
 * It is an array based data structure.
 * 
 * @author Csaba Farkas R00117945
 * 
 */
public class PatientArrayList implements PatientManagementInterface {

    private int numberOfPatients;   //indicates the number of patients stored in the list
    private Patient[] patients;     //empty array that is capable of storing Patient objects
    
    /**
     * Constructor method 
     * Creates an empty array that is capable of storing up to 200 Patients.
     * It also assigns 0 to the value of numberOfPatients because the list is empty
     * initially. 
     */
    public PatientArrayList() {
        this.numberOfPatients = 0;
        this.patients = new Patient[200];
    }
    
    /**
     * Override isEmpty method.
     * Method that checks if the list is empty
     * 
     * @return true if number of patients stored in the array is 0, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.numberOfPatients == 0;
    }

    /**
     * Override addPatient method.
     * This method adds a new patient to the list. It uses the number of patients
     * stored in the list as the index number of where the patients is going to be
     * inserted. It throws an exception if list is full.
     * 
     * @param patient: Patient object that is added to the list
     * @throws PatientListIsFullException List is full.
     */
    @Override
    public void addPatient(Patient patient) {
        if(this.numberOfPatients != this.patients.length) {
            this.patients[this.numberOfPatients] = patient;
            this.numberOfPatients++;
            return;
        }
        throw new PatientListIsFullException();
    }

    /**
     * Override removePatient method.
     * Function that loops through the patients that are stored in the list and 
     * removes the patient object with the patient ID that was passed as a parameter.
     * It throws an exception if the list is empty, or if the patient that is 
     * to be deleted is not in the list.
     * When deleting, and the patient to be deleted is not the last patient on the
     * list it calls the removeAndShift() function that calls the swapData() method
     * to delete the patient and shift all the remaining patients to the left.
     * 
     * @param patientID Patient identification number.
     * @throws PatientNotInTheListException Patient is not in the list.
     * @throws PatientListIsEmptyException List is empty.
     */
    @Override
    public void removePatient(String patientID) {
        if(this.isEmpty()) {
            throw new PatientListIsEmptyException();
        }
        for(int i = 0; i < this.numberOfPatients; i++) {
            if(this.patients[i].getPatientID().equals(patientID)) {
                if(i != this.numberOfPatients - 1) {
                    removeAndShift(i);
                    this.numberOfPatients--;
                    return;
                }
                else {
                    this.patients[i] = null;
                    this.numberOfPatients--;
                    return;
                }
            }
        }
        throw new PatientNotInTheListException();
    }

    /**
     * Override printPatients method.
     * Method that displays all the patients that are stored in the list in a 
     * table form. Throws an exception if number of patients in the list is 0.
     * 
     * @throws PatientListIsEmptyException List is empty.
     */
    @Override
    public void printPatients() {
        if(this.numberOfPatients > 0) {
            printTableHeader();
            for(Patient patient : this.patients) {
                if(patient == this.patients[this.numberOfPatients]) {
                    break;
                }
                System.out.print(patient);
            }
            printTableLine();
            return;
        }
        throw new PatientListIsEmptyException();
    }

    /**
     * Override removeAllPatients method.
     * Method that creates a new list.
     */
    @Override
    public void removeAllPatients() {
        this.patients = new Patient[200];
        this.numberOfPatients = 0;
    }

    /**
     * Override getNumberOfPatients method.
     * Method that returns the number of patients that are stored in the list.
     * 
     * @return number of patients in the list.
     */
    @Override
    public int getNumberOfPatients() {
        return this.numberOfPatients;
    }

    /**
     * Override sortBy method.
     * Sorting method. This method can sort patients based on different attributes.
     * User can choose an attribute and that is passed in the form of an integer to
     * this method. The method creates a comparator object that is used during a 
     * selection sort.
     * 
     * @param variableIndex indicates the attribute that is the basis of the comparison
     * @throws SortingException if list contains less than 1 item
     */
    @Override
    public void sortBy(int variableIndex) {
        if(this.numberOfPatients < 2) {
            throw new SortingException("List is empty or list contains only one Patient! List is already sorted!");
        }
        ListComparators comparator = new ListComparators();
        
        if(variableIndex == PatienLinkedtList.NAME_INDEX) {
            comparator = comparator.new PatientNameComparator();
        }
        else if(variableIndex == PatienLinkedtList.ADDRESS_INDEX) {
            comparator = comparator.new PatientAddressComparator();
        }
        else if(variableIndex == PatienLinkedtList.AGE_INDEX) {
            comparator = comparator.new PatientAgeComparator();
        }
        else if(variableIndex == PatienLinkedtList.GENDER_INDEX) {
            comparator = comparator.new PatientGenderComparator();
        }
        else if(variableIndex == PatienLinkedtList.MARITAL_STATUS_INDEX) {
            comparator = comparator.new PatientMaritalStatusComparator();
        }
        else if(variableIndex == PatienLinkedtList.ADMIT_DATE_INDEX) {
            comparator = comparator.new PatientAdmitDateComparator();
        }
        else if(variableIndex == PatienLinkedtList.DISCHARGE_DATE_INDEX) {
            comparator = comparator.new PatientDischargeDateComparator();
        }
        else if(variableIndex == PatienLinkedtList.DOCTORS_NAME_INDEX) {
            comparator = comparator.new PatientDoctorComparator();
        }
        else if(variableIndex == PatienLinkedtList.DEPARTMENT_INDEX) {
            comparator = comparator.new PatientDepartmentComparator();
        }
        else if(variableIndex == PatienLinkedtList.NUMBER_OF_DAYS_INDEX) {
            comparator = comparator.new PatientNumberOfDaysInHospitalComparator();
        }
       
        //Selection sort
        int min;    //integer variable that indicates the index number of the smallest item
        
        for(int begin = 0; begin < this.numberOfPatients - 1; begin++) {
            min = begin;
            for(int move = begin + 1; move < this.numberOfPatients; move++) {
                if(comparator.compare(this.patients[min], this.patients[move]) > 0) {
                    min = move;
                }
            }
            Patient temp = this.patients[begin];
            this.patients[begin] = this.patients[min];
            this.patients[min] = temp;
        }
    }

    /**
     * Override searchBy method.
     * This method works in a very similar way as the searchBy method in the
     * PatientLinkedList object. It loops through the list until it finds the 
     * matching records and displays them.
     * 
     * @param variableIndex Indicates the type of object passed to the method.
     * @param searchTerm Contains the object that is to be searched.
     * @throws PatientNotInTheListException No match was found.
     */
    @Override
    public void searchBy(int variableIndex, Object searchTerm) {
        Patient results[] = new Patient[this.numberOfPatients];
        int counter = 0;
        for(int i = 0; i < this.numberOfPatients; i++) {
            switch(variableIndex) {
                    case PatienLinkedtList.PATIENT_ID_INDEX:
                        if(this.patients[i].getPatientID().toLowerCase().equals((String) searchTerm)) {
                            results[counter] = this.patients[i];
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.NAME_INDEX:
                        if(this.patients[i].getName().toLowerCase().startsWith((String) searchTerm) || 
                                this.patients[i].getName().equalsIgnoreCase((String) searchTerm)) {
                            results[counter] = this.patients[i];
                            counter++;                        }
                        break;
                    case PatienLinkedtList.ADDRESS_INDEX:
                        if(this.patients[i].getAddress().equalsIgnoreCase((String) searchTerm) ||
                                this.patients[i].getAddress().toLowerCase().startsWith((String) searchTerm)) {
                            results[counter] = this.patients[i];
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.AGE_INDEX:
                        String ageString = searchTerm.toString();
                        int ageInt = Integer.parseInt(ageString);
                        if(this.patients[i].getAge() == ageInt) {
                            results[counter] = this.patients[i];
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.GENDER_INDEX:
                        if(this.patients[i].getGender().equals((String) searchTerm)) {
                            results[counter] = this.patients[i];
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.MARITAL_STATUS_INDEX:
                        if(this.patients[i].getGender().equals((String) searchTerm) ||
                                this.patients[i].getMaritalStatus().startsWith((String) searchTerm)) {
                            results[counter] = this.patients[i];
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.ADMIT_DATE_INDEX:
                        if(this.patients[i].getAdmitDate().equals((Calendar) searchTerm)) {
                            results[counter] = this.patients[i];
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.DISCHARGE_DATE_INDEX:
                        if(this.patients[i].getDischargeDate().equals((Calendar) searchTerm)) {
                            results[counter] = this.patients[i];
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.DOCTORS_NAME_INDEX:
                        if(this.patients[i].getDoctorsName().toLowerCase().equals((String) searchTerm)) {
                            results[counter] = this.patients[i];
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.DEPARTMENT_INDEX:
                        if(this.patients[i].getDepartment().toLowerCase().equals((String) searchTerm)) {
                            results[counter] = this.patients[i];
                            counter++;
                        }
            }
        }
        if(results[0] != null) {
            printTableHeader();
            for (Patient result : results) {
                if (result == null) {
                    break;
                }
                System.out.print(result.toString());
            }
            printTableLine();
            return;
        }
        
        throw new PatientNotInTheListException();
    }

    /**
     * Override sortAndDisplayByNumberOfDays.
     * Again, a very similar implementation to the method in the 
     * PatientLinkedList class. The only difference is the way of looping through
     * the list.
     * 
     * @throws PatientListIsEmptyException List is empty.
     */
    @Override
    public void sortAndDisplayByNumberOfDays() {
        long totalNumberOfDays = 0;
        this.sortBy(10);
        System.out.println("+-------------------------------------------------------------+");
        System.out.format("%s %-10s %1s %-25s %5s %-14s %s %n", "|", "Patient ID", "|", "Name", "|", "Number of Days", "|");
        System.out.println("+-------------------------------------------------------------+");
        
        if(this.numberOfPatients > 0) {
            int counter = 0;
            for(Patient patient : this.patients) {
                if(counter == this.numberOfPatients) {
                    break;
                }
                System.out.format("%s %-10s %1s %-25s %5s %-14s %s %n", "|", patient.getPatientID(), "|",
                                  patient.getName(), "|", patient.getNumberOfDaysInHospital(), "|");
                totalNumberOfDays += patient.getNumberOfDaysInHospital();
                counter++;
            }
            System.out.println("+-------------------------------------------------------------+");
            System.out.format("%s %42s %s %-14d %s %n", "|", "Total Number of Days", "|", totalNumberOfDays, "|");
            System.out.println("+-------------------------------------------------------------+");
            return;
        }
        throw new PatientListIsEmptyException();
    }

    /**
     * Private method that is used by removePatient method. It removes a patient
     * by shifting all of the patients, after the patient that is to be removed,
     * to the left.
     * 
     * @param i marks the position of the patient to be removed from the list
     */
    private void removeAndShift(int i) {
        for(int j = i; j < this.numberOfPatients; j++) {
            this.patients[j] = this.patients[j+1];
        }
    }

    /**
     * Private method. Same method that is in PatientLinkedList class.
     */
    private void printTableHeader() {
        printTableLine();
        System.out.format("%s %-10s %1s %-25s %5s %-35s %1s %-5s %1s %-10s %1s %-20s %1s %-20s %1s %-15s %1s %-20s %1s %-15s %5s %n", 
                          "|", "Patient ID", "|", "Name", "|", "Address", "|", "Age", "|", "Gender", "|", "Marital Status",
                          "|", "Doctor's Name", "|", "Department", "|", "Date of Admission", "|", "Date of Discharge", "|");
        printTableLine();
    }
    
    /**
     * Private method. Same method that is in PatientLinkedList class.
     */
    private void printTableLine() {
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
    }
}
