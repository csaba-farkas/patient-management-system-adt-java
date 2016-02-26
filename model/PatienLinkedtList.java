
package model;

import model.exceptions.PatientNotInTheListException;
import interfaces.PatientManagementInterface;
import java.util.Calendar;
import model.ListComparators.PatientNameComparator;
import model.ListComparators.PatientAddressComparator;
import model.ListComparators.PatientAdmitDateComparator;
import model.ListComparators.PatientAgeComparator;
import model.ListComparators.PatientDischargeDateComparator;
import model.ListComparators.PatientDoctorComparator;
import model.ListComparators.PatientGenderComparator;
import model.ListComparators.PatientMaritalStatusComparator;
import model.exceptions.PatientListIsEmptyException;
import model.exceptions.SortingException;

/**
 * <h1>PatientLinkedList --- Date of last modification: 25-Apr-2015</h1>
 * One of the data structure classes. Creates a doubly linked list that can store
 * Patient objects. It implements the ADT interface. 
 * 
 * @author Csaba Farkas R00117945
 */
public class PatienLinkedtList implements PatientManagementInterface {

    //public static final integer variables that are used within this class and others
    public static final int PATIENT_ID_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int ADDRESS_INDEX = 2;
    public static final int AGE_INDEX = 3;
    public static final int GENDER_INDEX = 4;
    public static final int MARITAL_STATUS_INDEX = 5;
    public static final int ADMIT_DATE_INDEX = 6;
    public static final int DISCHARGE_DATE_INDEX = 7;
    public static final int DOCTORS_NAME_INDEX = 8;
    public static final int DEPARTMENT_INDEX = 9;
    public static final int NUMBER_OF_DAYS_INDEX = 10;
   
    //Instance variables
    private int numberOfPatients;       //Indicates the number of patients stored in the list
    private Node head;                  //Indicates the 'dummy' head Node of the list
    
    /**
     * Constructor method that creates and empty PatientLinkedList. The empty list
     * contains the 'dummy' Node in which 'previous' and 'next' pointers are 
     * pointing to the 'dummy' Node itself.
     */
    public PatienLinkedtList() {
        this.numberOfPatients = 0;
        this.head = new Node();
        this.head.setPrevious(this.head);
        this.head.setNext(this.head);
    }
    
    /**
     * Override isEmpty method. 
     * 
     * @return boolean value indicating if the list empty or not
     */
    @Override
    public boolean isEmpty() {
        return this.numberOfPatients == 0;
    }

    /**
     * Override addPatient method.
     * Method creates a new Node:
     * 1. The 'previous' pointer of the new Node is set to point to the Node 
     *    previous to the head Node. 
     * 2. The 'next' pointer of the new Node is set to point to the head Node.
     * The 'previous' pointer of the head is set to point to the new Node.
     * The 'next' pointer of the Node previous to the new Node is set to point
     * to the new Node. The number of patients stored in the list is incremented
     * by 1. 
     * 
     * @param patient Patient object that is to be stored in the new Node
     */
    @Override
    public void addPatient(Patient patient) {
        Node newNode = new Node(this.head.getPrevious(), this.head, patient);
        this.head.setPrevious(newNode);
        newNode.getPrevious().setNext(newNode);
        this.numberOfPatients++;
    }

    /**
     * Override removePatient method.
     * This method removes a Node from the list, thus removes a Patient object
     * from the data structure.
     * Linked lists are not direct access lists, so the Node that is to be deleted
     * must be found. This is to be done by looping through the Nodes until the
     * appropriate Node is found, or if the Node is not in the list, an exception
     * is thrown.
     * The Node is removed by:
     *  1. Set the 'next' pointer of the previous Node to point to the next Node.
     *  2. Set the 'previous' pointer of the next Node to point to the previous Node.
     *  3. Decrement the number of patients by 1.
     * 
     * @param patientID String object. Patient ID.
     * @throws PatientNotInTheListException Patient is not in the list.
     */
    @Override
    public void removePatient(String patientID) {
        Node delNode = this.head.getNext();
        while(!delNode.getPatient().getPatientID().equals(patientID) && delNode != this.head) {
            delNode = delNode.getNext();
        }
        if(delNode == this.head) {
            throw new PatientNotInTheListException();
        }
        
        delNode.getPrevious().setNext(delNode.getNext());
        delNode.getNext().setPrevious(delNode.getPrevious());
        this.numberOfPatients--;
    }
    
    /**
     * Override removeAllPatients method.
     * This method creates a new empty list with the instance variables
     * re-initialized.
     */
    @Override
    public void removeAllPatients() {
        this.numberOfPatients = 0;
        this.head = new Node();
        this.head.setPrevious(this.head);
        this.head.setNext(this.head);
    }

    /**
     * Override getNumberOfPatients method.
     * Getter method - number of patients.
     * 
     * @return number of patients in the list
     */
    @Override
    public int getNumberOfPatients() {
        return this.numberOfPatients;
    }

    /**
     * Override sortBy method.
     * Depending on what variable index was passed to the sortBy function, the function
     * creates a comparator class and it sorts the list using bubble sort algorithm.
     * 
     * @param variableIndex indicates the attribute that is the basis of the comparison
     * @throws SortingException if number of patients in list is less than 2
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
     
        /*
        Bubble sort:
            1. Create a boolean variable $sorted an initialize it false
            2. A for loop iterating while value of $sorted is false
                a. $sorted set true
                b. A Node object called $current is created and referencing the
                   Node after head
                c. While current doesn't reference the Node before the head Node
                    - Compare two adjacent Nodes (current and current.next) using
                      $comparator. If current.next > current, swap the value in the
                      Nodes and set sorted to false.
                    - Make current referencing current.next
        */
        boolean sorted = false;
        
        while(!sorted) {
            sorted = true;
            Node current = this.head.getNext();
            while(current != this.head.getPrevious()) {
                if(comparator.compare(current.getPatient(), current.getNext().getPatient()) > 0) {
                    swapData(current, current.getNext());
                    sorted = false;
                }
                current = current.getNext();
            }
        }
    }

    /**
     * Override searchBy method.
     * Two parameters are passed to this method. First one is indicating the type of
     * attribute of the second one.
     * It loops through the Nodes to find the appropriate Patient objects.
     * Matching records are stored in an array and the contents of this array is
     * displayed at the end of the search.
     * If no matching records are found a PatientNotInTheListException is thrown.
     * 
     * @param variableIndex indicates the class and type of 'searchTerm'
     * @param searchTerm indicates an object that is to be searched
     * @throws PatientNotInTheListException Patient is not in the list
     */
    @Override
    public void searchBy(int variableIndex, Object searchTerm) {
        Node current = this.head.getNext();
        Patient results[] = new Patient[this.numberOfPatients];
        int counter = 0;
        while(current != this.head) {
            switch(variableIndex) {
                    case PatienLinkedtList.PATIENT_ID_INDEX:
                        if(current.getPatient().getPatientID().toLowerCase().equals((String) searchTerm)) {
                            results[counter] = current.getPatient();
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.NAME_INDEX:
                        if(current.getPatient().getName().toLowerCase().startsWith ((String) searchTerm) || 
                                current.getPatient().getName().equalsIgnoreCase((String) searchTerm)) {
                            results[counter] = current.getPatient();
                            counter++;                        }
                        break;
                    case PatienLinkedtList.ADDRESS_INDEX:
                        if(current.getPatient().getAddress().equalsIgnoreCase((String) searchTerm) ||
                                current.getPatient().getAddress().toLowerCase().startsWith((String) searchTerm)) {
                            results[counter] = current.getPatient();
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.AGE_INDEX:
                        String ageString = searchTerm.toString();
                        int ageInt = Integer.parseInt(ageString);
                        if(current.getPatient().getAge() == ageInt) {
                            results[counter] = current.getPatient();
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.GENDER_INDEX:
                        if(current.getPatient().getGender().equals((String) searchTerm)) {
                            results[counter] = current.getPatient();
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.MARITAL_STATUS_INDEX:
                        if(current.getPatient().getGender().equals((String) searchTerm) ||
                                current.getPatient().getMaritalStatus().startsWith((String) searchTerm)) {
                            results[counter] = current.getPatient();
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.ADMIT_DATE_INDEX:
                        if(current.getPatient().getAdmitDate().equals((Calendar) searchTerm)) {
                            results[counter] = current.getPatient();
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.DISCHARGE_DATE_INDEX:
                        if(current.getPatient().getDischargeDate().equals((Calendar) searchTerm)) {
                            results[counter] = current.getPatient();
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.DOCTORS_NAME_INDEX:
                        if(current.getPatient().getDoctorsName().toLowerCase().equals((String) searchTerm)) {
                            results[counter] = current.getPatient();
                            counter++;
                        }
                        break;
                    case PatienLinkedtList.DEPARTMENT_INDEX:
                        if(current.getPatient().getDepartment().toLowerCase().equals((String) searchTerm)) {
                            results[counter] = current.getPatient();
                            counter++;
                        }
            }
            current = current.getNext();
        }
        
        //Print results
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
     * This method sorts the patients by the number of days they spent in the
     * hospital. Then it prints a table to the screen that starts with the patient
     * who spent the least number of days and ends with the one who spent the most
     * number of days in the hospital.
     * It also calculates the total number of days spent in the hospital by all
     * of the patients.
     */
    @Override
    public void sortAndDisplayByNumberOfDays() {
        long totalNumberOfDays = 0;
        this.sortBy(10);
        System.out.println("+-------------------------------------------------------------+");
        System.out.format("%s %-10s %1s %-25s %5s %-14s %s %n", "|", "Patient ID", "|", "Name", "|", "Number of Days", "|");
        System.out.println("+-------------------------------------------------------------+");
        
        if(this.numberOfPatients > 0) {
            Node current = this.head.getNext();
            while(current != this.head) {
                System.out.format("%s %-10s %1s %-25s %5s %-14s %s %n", "|", current.getPatient().getPatientID(), "|",
                                  current.getPatient().getName(), "|", current.getPatient().getNumberOfDaysInHospital(), "|");
                totalNumberOfDays += current.getPatient().getNumberOfDaysInHospital();
                current = current.getNext();
            }
            System.out.println("+-------------------------------------------------------------+");
            System.out.format("%s %42s %s %-14d %s %n", "|", "Total Number of Days", "|", totalNumberOfDays, "|");
            System.out.println("+-------------------------------------------------------------+");
            return;
        }
        throw new PatientListIsEmptyException();
    }
    
    /**
     * Override printPatients method.
     * Method that prints a table containing all of the Patients to the screen.
     * 
     * @throws PatientListIsEmptyException List is empty.
     */
    @Override
    public void printPatients() {
        
        if(this.numberOfPatients > 0) {
            printTableHeader();
            Node current = this.head.getNext();
            while(current != this.head) {
                System.out.print(current.getPatient());
                current = current.getNext();
                
            }
            printTableLine();
            return;
        }
        throw new PatientListIsEmptyException();
    }

    /**
     * Private method that is used during sorting. It swaps the Patient objects
     * between two Nodes.
     * 
     * @param previous
     * @param next 
     */
    private void swapData(Node previous, Node next) {
        Patient temp = previous.getPatient();
        previous.setPatient(next.getPatient());
        next.setPatient(temp);
    }
    
    /**
     * Private method that is used by printPatients method.
     * It prints the header of the table in the required format.
     */
    private void printTableHeader() {
        printTableLine();
        System.out.format("%s %-10s %1s %-25s %5s %-35s %1s %-5s %1s %-10s %1s %-20s %1s %-20s %1s %-15s %1s %-20s %1s %-15s %5s %n", 
                          "|", "Patient ID", "|", "Name", "|", "Address", "|", "Age", "|", "Gender", "|", "Marital Status",
                          "|", "Doctor's Name", "|", "Department", "|", "Date of Admission", "|", "Date of Discharge", "|");
        printTableLine();
    }
    
    /**
     * Private method that prints a line to the screen. It is used when printing
     * the table.
     */
    private void printTableLine() {
        System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
    }

}
