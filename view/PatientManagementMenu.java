
package view;

import interfaces.PatientManagementSystemUI;
import controller.PatientController;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import model.Patient;
import model.enums.Gender;
import model.enums.MaritalStatus;
import model.exceptions.DateMismatchFormatException;
import model.exceptions.PatientNotInTheListException;

/**
 * <h1>PatientManagementMenu --- Data of last modification 5-May-2015</h1>
 * This class is the user interface, the 'view' part of MVC design.
 * @author Csaba
 * Date: 20-Apr-2015
 */

public class PatientManagementMenu implements PatientManagementSystemUI {

    //static variables
    private final static String WELCOME_MESSAGE = "\nPlease select one of the following options:\n ";   //Menu item
    private final static String MAIN_MENU = "1. Add a new patient\n" +
                                            "2. Remove a patient\n" +
                                            "3. Remove all patients\n" +
                                            "4. Sort By...\n" +
                                            "5. Search By...\n" +
                                            "6. Reporting...\n" +
                                            "7. Exit\n" +
                                            "Please enter a number 1-7: ";                              //Menu item
    
    private final static String ADD_NEW_PATIENT_LABEL = "\nAdd new patient" +
                                                        "\n---------------";                            //Menu item
    private final static String PATIENT_ID_LABEL = "\nPatient ID: ";                                    //Menu item     
    private final static String SURNAME_LABEL = "Surname: ";                                            //Menu item
    private final static String FIRST_NAME_LABEL = "First name: ";                                      //Menu item
    private final static String ADDRESS_LABEL = "Address: ";                                            //Menu item
    private final static String AGE_LABEL = "Age: ";                                                    //Menu item
    private final static String GENDER_LABEL = "\nGender" +
                                               "\n------" +
                                               "\n      1. Male" +
                                               "\n      2. Female" +
                                               "\nPlease enter a number 1-2: ";                         //Menu item
    
    private final static String MARITAL_STATUS_LABEL = "\nMarital Status" + 
                                                       "\n--------------" +
                                                       "\n      1. Single" +
                                                       "\n      2. Married" +
                                                       "\n      3. Divorced" +
                                                       "\n      4. In civil partnership" +
                                                       "\n      5. Widow/Widower" +
                                                       "\nPlease enter a number 1-5: ";                 //Menu item
    private final static String ADMIT_DATE_LABEL = "\nDate of admission" +  
                                                   "\n-----------------";                               //Menu item
    private final static String DISCHARGE_DATE_LABEL = "\nDate of discharge" +
                                                       "\n-----------------";                           //Menu item
    private final static String DATE_LABEL = "\nPlease enter date in the following format (dd/mm/yyyy): ";  //Menu item
    private final static String DOCTORS_NAME_LABEL = "\nDoctor's name: ";                               //Menu item
    private final static String DEPARTMENT_LABEL = "Department: ";                                      //Menu item
    private final static String PATIENT_ADDED_LABEL = "\nPatient was added to the list!";               //Menu item
    private final static String REMOVE_PATIENT_LABEL = "\nRemove a patient" +       
                                                           "\n----------------" +
                                                           "\nPleas enter Patient ID: ";                //Menu item
    private final static String REMOVE_PATIENT_FEEDBACK = "\nPatient removed!";                         //Menu item
    private final static String REMOVE_ALL_PATIENTS_LABEL = "\nRemove all patients" +       
                                                           "\n-------------------" +        
                                                           "\nAre you sure you want to delete all patients from the list? [y/n]: "; //Menu item
    private final static String PATIENTS_REMOVED_LABEL = "\nAll patients removed!";                     //Menu item
    private final static String SORT_BY_MENU = "\nSort by..." +                                         
                                               "\n----------";                                          //Menu item
    private final static String SEARCH_BY_MENU = "\nSearch by..." +
                                                 "\n------------";                                      //Menu item
    private final static String ATTRIBUTES_LIST = "\n1. Patient ID" +
                                                  "\n2. Name" +
                                                  "\n3. Address" +
                                                  "\n4. Age" +
                                                  "\n5. Gender" +
                                                  "\n6. Marital Status" +
                                                  "\n7. Date of Admission" +
                                                  "\n8. Date of Discharge" +
                                                  "\n9. Doctor's name" +
                                                  "\n10. Department" +
                                                  "\nPlease enter a number 1-10: ";                     //Menu item
    private final static String REPORTING_MENU = "\nReporting" +
                                                 "\n---------" +                                        
                                                 "\n1. Number of days spent in hospital";               //Menu item
    //static int variables indicating different parts of the user menu
    private final static int MAIN_MENU_INDEX = 0;
    private final static int GENDER_SELECT_INDEX = 1;
    private final static int MARITAL_STATUS_SELECT_INDEX = 2;
    private final static int SORT_BY_MENU_INDEX = 3;
    private final static int SEARCH_BY_MENU_INDEX = 4;
    
    //Instance variables
    private final Scanner reader;
    private int selection;
    private int day, month, year;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private String patientID, name, address, department, doctorsName;
    private Object searchTerm;
    private Calendar dischargeDate, admitDate;
    private int age;
    
    /**
     * Creates a PatientManagementMenu object with Scanner initialized.
     */
    public PatientManagementMenu() {
        this.reader = new Scanner(System.in);
        
    }
        
    /**
     * Override start method. A recursive method that displays the main menu and
     * takes/validates a user input. 
     * 
     * @throws NumberFormatException User input cannot be converted into an integer.
     */
    @Override
    public void start() {
        System.out.println(PatientManagementMenu.WELCOME_MESSAGE);
        System.out.print(PatientManagementMenu.MAIN_MENU);
        
        while(true) {
            try {
                this.selection = getAndValidateIntegerInput(1, 7);
                checkMainMenuSelection();
                break;
            } catch(NumberFormatException e) {
                System.out.println("\nIllegal Input!\n");
                System.out.print(PatientManagementMenu.MAIN_MENU);
            }
        }
        start();
    }
    
    /**
     * Getter method. 
     * @return selection
     */
    public int getSelection() {
        return this.selection;
    }
     
    /**
     * If this program was developed with a GUI this function would contain the
     * fireDataChanged() function that tells the frame to refresh itself.
     * In this case it prints the table to the screen every time this function is
     * called.
     */
    @Override
    public void refresh() {
        if(!PatientController.getInstance().getPatientList().isEmpty()) {
            PatientController.getInstance().getPatientList().printPatients();
        }
    }

    /**
     * Function that validates user integer input in a given range.
     * 
     * @param lower Lower bound of range.
     * @param upper Upper bound of range.
     * @return Validated input.
     * @throws NumberFormatException Input is not a number or input is not in given range.
     */
    private int getAndValidateIntegerInput(int lower, int upper) {
        int input = Integer.parseInt(this.reader.nextLine().trim());
        if(input < lower || input > upper) {
            throw new NumberFormatException();
        }
        return input;
    }

    /**
     * This function is call after user selected an option from the main menu.
     * It executes the task the user chose from the main menu.
     * 
     * @throws PatientNotInTheListException Patient is not in list.
     * @throws NumberFormatException Invalid input or input is not in correct range.
     */
    private void checkMainMenuSelection() {
        switch (this.selection) {
            //Add new patient to the list
            case 1:
                System.out.print(PatientManagementMenu.ADD_NEW_PATIENT_LABEL);
                
                //Get patient ID
                System.out.print(PatientManagementMenu.PATIENT_ID_LABEL);
                this.patientID = this.reader.nextLine();
                
                //Get name
                System.out.print(PatientManagementMenu.SURNAME_LABEL);
                this.name = this.reader.nextLine();
                System.out.print(PatientManagementMenu.FIRST_NAME_LABEL);
                this.name = name + ", " + this.reader.nextLine();
                
                //Get address
                System.out.print(PatientManagementMenu.ADDRESS_LABEL);
                this.address = this.reader.nextLine();
            
                //Get and validate age
                while(true) {
                    try {
                        System.out.print(PatientManagementMenu.AGE_LABEL);
                        this.age = getAndValidateIntegerInput(0, 120);
                        break;
                    } catch(NumberFormatException e) {
                        System.out.println("Illegal input! Age can only be a numeric value from 0-120!");
                    }
                }
                
                //Get gender
                setEnum(PatientManagementMenu.GENDER_SELECT_INDEX);
                
                //Get marital status
                setEnum(PatientManagementMenu.MARITAL_STATUS_SELECT_INDEX);
                
                //Get and validate date of admission
                while(true) {
                    try {
                        System.out.print(PatientManagementMenu.ADMIT_DATE_LABEL);
                        getAndValidateDate();
                        this.admitDate = new GregorianCalendar(this.year, (this.month - 1), this.day);
                        break;
                    } catch(DateMismatchFormatException | NumberFormatException e) {
                        System.out.println("Illegal input! Pleas enter a valid date in the following format dd/mm/yyy!");
                    }
                }
                
                //Get and validate date of discharge
                while(true) {
                    try {
                        System.out.print(PatientManagementMenu.DISCHARGE_DATE_LABEL);
                        getAndValidateDate();
                        this.dischargeDate = new GregorianCalendar(this.year, (this.month - 1), this.day);
                        if(this.dischargeDate.compareTo(this.admitDate) <= 0) {
                            throw new DateMismatchFormatException();
                        }
                        break;
                    } catch(DateMismatchFormatException | NumberFormatException e) {
                        System.out.println("Illegal input! Please enter a valid date in the following format dd/mm/yyy!" +
                                           "\nDate of discharge must be later than date of admission!");
                    }
                }
                
                //Get doctor's name
                System.out.print(PatientManagementMenu.DOCTORS_NAME_LABEL);
                this.doctorsName = this.reader.nextLine();
                
                //Get department
                System.out.print(PatientManagementMenu.DEPARTMENT_LABEL);
                this.department = this.reader.nextLine();
                
                //Create a new patient with the values entered
                Patient newPatient = new Patient(patientID, name, address, this.age, this.gender, this.admitDate, this.dischargeDate,
                                                 this.maritalStatus, this.doctorsName, this.department);
                
                //Call the controller and add patient to the list
                PatientController.getInstance().getPatientList().addPatient(newPatient);
                System.out.println("Patient ID: " + this.patientID + PatientManagementMenu.PATIENT_ADDED_LABEL);
                
                //Print all patients
                PatientController.getInstance().getGui().refresh();
                break;
                
            //Remove a patient
            case 2:
                //Get and validate patient ID. Call controller to remove patient from list
                try {
                    System.out.print(PatientManagementMenu.REMOVE_PATIENT_LABEL);
                    this.patientID = this.reader.nextLine();
                    PatientController.getInstance().getPatientList().removePatient(this.patientID);
                    System.out.println("Patient ID : " + this.patientID + PatientManagementMenu.REMOVE_PATIENT_FEEDBACK + "\n");
                    PatientController.getInstance().getGui().refresh();
                } catch (PatientNotInTheListException e) {
                    //Throw exception if patient with given patient ID was not found in the list
                    System.out.println("Patient is not in the list. Returning to the main menu.\n");
                }
                break;
            
            //Remove all patients
            case 3:
                removeAllPatients();
                break;
            
            //Sort patients by...
            case 4:
                while(true) {
                    try {
                        displayAttributeListAndGetSelection(PatientManagementMenu.SORT_BY_MENU_INDEX);
                        System.out.println("\nSorted List: ");
                        PatientController.getInstance().getPatientList().sortBy(this.selection - 1);
                        PatientController.getInstance().getGui().refresh();

                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("\nInvalid input! Please enter a number 1-10!");
                    }
                }
                break;
                
            //Search
            case 5:
                while(true) {
                    try {
                        displayAttributeListAndGetSelection(PatientManagementMenu.SEARCH_BY_MENU_INDEX);
                        switch (this.selection) {
                            //Passing Strings to searchBy function
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 9:
                            case 10:
                                System.out.print("Search term: ");
                                String input = this.reader.nextLine();
                                this.searchTerm = input.toLowerCase();
                                System.out.println("\nSearch Results: ");
                                PatientController.getInstance().getPatientList().searchBy(this.selection - 1, this.searchTerm);
                                break;
                            //Converting enums to String in case 5 and 6 and passing them to searchBy
                            case 5:
                                setEnum(PatientManagementMenu.GENDER_SELECT_INDEX);
                                switch (this.gender) {
                                    case MALE:
                                        this.searchTerm = "Male";
                                        break;
                                    default:
                                        this.searchTerm = "Female";
                                }
                                System.out.println("\nSearch Results: ");
                                PatientController.getInstance().getPatientList().searchBy(this.selection - 1, this.searchTerm);
                                break;
                            case 6:
                                setEnum(PatientManagementMenu.MARITAL_STATUS_SELECT_INDEX);
                                switch (this.maritalStatus) {
                                    case SINGLE:
                                        this.searchTerm = "Single";
                                        break;
                                    case MARRIED:
                                        this.searchTerm = "Married";
                                        break;
                                    case DIVORCED:
                                        this.searchTerm = "Divorced";
                                        break;
                                    case CIVIL_PARTNERSHIP:
                                        this.searchTerm = "In civil partnership";
                                        break;
                                    case WIDOW_WIDOWER:
                                        this.searchTerm = "Widow";
                                }
                                System.out.println("\nSearch Results: ");
                                PatientController.getInstance().getPatientList().searchBy(this.selection - 1, this.searchTerm);
                                break;
                            //Passing dates to searchBy
                            default:
                                getAndValidateDate();
                                this.searchTerm = new GregorianCalendar(this.year, (this.month - 1), this.day);
                                System.out.println("\nSearch Results: ");
                                PatientController.getInstance().getPatientList().searchBy(this.selection - 1, this.searchTerm);
                                break;    
                        }
                        break;
                    } catch (NumberFormatException e) {
                        //Input is not in correct range or not a number
                        System.out.println("\nInvalid input! Please enter a number 1-10!");
                    } catch (PatientNotInTheListException e) {
                        //Patient is not in list
                        System.out.println("\nNo results!");
                        break;
                    }
                }
                break;
            //Reporting
            case 6:
                System.out.println(PatientManagementMenu.REPORTING_MENU);
                PatientController.getInstance().getPatientList().sortAndDisplayByNumberOfDays();
                break;
            //Exit
            case 7:
                System.out.println("\nGood Bye!");
                System.exit(0);
        }
    }

    /**
     * This function takes user input and validates it. If date is not in the 
     * format of dd/mm/yyyy, it throws an exception.
     * 
     * @throws NumberFormatException Numbers are not in correct range.
     * @throws DateMismatchFormatException Date is not a valid date.
     */
    private void getAndValidateDate() throws NumberFormatException {
        
        System.out.print(PatientManagementMenu.DATE_LABEL);
        String date = this.reader.nextLine();
        if(date.length() != 10) {
            throw new DateMismatchFormatException();
        }
        if(date.charAt(2) != '/' || date.charAt(5) != '/') {
            throw new DateMismatchFormatException();
        }
        if(Integer.parseInt(date.substring(0, 2)) < 1 || Integer.parseInt(date.substring(0, 2)) > 31) {
            throw new DateMismatchFormatException();
        }
        if(Integer.parseInt(date.substring(3, 5)) < 1 || Integer.parseInt(date.substring(3, 5)) > 12) {
            throw new DateMismatchFormatException();
        }
        this.day = Integer.parseInt(date.substring(0, 2));
        this.month = Integer.parseInt(date.substring(3, 5));
        this.year = Integer.parseInt(date.substring(6));
    }

    /**
     * Function confirms if user really wants to remove every patient from the
     * list. Removes all patients after confirmation.
     */
    private void removeAllPatients() {
        System.out.print(PatientManagementMenu.REMOVE_ALL_PATIENTS_LABEL);
        String answer = this.reader.nextLine();
        switch (answer) {
            case "y":
            case "Y":
                System.out.println(PatientManagementMenu.PATIENTS_REMOVED_LABEL + "\n");
                PatientController.getInstance().getPatientList().removeAllPatients();
                PatientController.getInstance().getGui().refresh();
                break;
            case "n":
            case "N":
                System.out.println("Returning to the main menu.\n");
                break;
            default:
                System.out.println("Illegal input!\n");
                removeAllPatients();
        }
    }

    /**
     * Method that is used by either the sortBy or searchBy functions. It prints
     * the list of attributes to the screen and takes/validates user input.
     * 
     * @param menuIndex Indicates is searchBy or sortBy was selected.
     */
    private void displayAttributeListAndGetSelection(int menuIndex) {
        String label = "";
        if(menuIndex == PatientManagementMenu.SORT_BY_MENU_INDEX) {
            label = PatientManagementMenu.SORT_BY_MENU;
        }
        else if(menuIndex == PatientManagementMenu.SEARCH_BY_MENU_INDEX) {
            label = PatientManagementMenu.SEARCH_BY_MENU;
        }
        System.out.print(label + PatientManagementMenu.ATTRIBUTES_LIST);
        this.selection = getAndValidateIntegerInput(1, 10);
    }

    /**
     * Method that takes user input and converts it to an enum value. It is used
     * when selecting the gender and marital status of a patient.
     * 
     * @param selectionIndex Indicates if method creates gender or marital status.
     * @throws NumberFormatException Invalid input.
     */
    private void setEnum(int selectionIndex) {
        int subMenuSelection;
        switch (selectionIndex) {
            //Gender
            case PatientManagementMenu.GENDER_SELECT_INDEX:
                while(true) {
                    try { 
                        System.out.print(PatientManagementMenu.GENDER_LABEL);
                        subMenuSelection = getAndValidateIntegerInput(1, 2);
                        switch (subMenuSelection) {
                            //1 for male, 2 for female
                            case 1:
                                this.gender = Gender.MALE;
                                break;
                            default:
                                this.gender = Gender.FEMALE;
                        }
                        break; 
                    } catch(NumberFormatException e) {
                        System.out.println("Illegal input! Please press 1 for 'Male' or 2 for 'Female'!");
                    }
                }
                break;
            //Marital status
            case PatientManagementMenu.MARITAL_STATUS_SELECT_INDEX:
                while(true) {
                    try {
                        System.out.print(PatientManagementMenu.MARITAL_STATUS_LABEL);
                        subMenuSelection = getAndValidateIntegerInput(1, 5);
                        switch (subMenuSelection) {
                            //1 for single
                            case 1:
                                this.maritalStatus = MaritalStatus.SINGLE;
                                break;
                            //2 for married
                            case 2:
                                this.maritalStatus = MaritalStatus.MARRIED;
                                break;
                            //3 for divorced
                            case 3:
                                this.maritalStatus = MaritalStatus.DIVORCED;
                                break;
                            //4 for marital status
                            case 4:
                                this.maritalStatus = MaritalStatus.CIVIL_PARTNERSHIP;
                                break;
                            //5 for widow or widower
                            default:
                                this.maritalStatus = MaritalStatus.WIDOW_WIDOWER;
                        }
                        break;
                    } catch(NumberFormatException e) {
                        //Invalid input
                        System.out.println("Illegal input! Please press a number 1-5!");
                    }
                }
        }
    }
}
