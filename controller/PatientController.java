
package controller;

import interfaces.PatientManagementInterface;
import interfaces.PatientManagementSystemUI;

/**
 * 
 * PatientController --- Date of last modification: 26-Apr-2015
 * A singleton controller class that connects the different parts of the 
 * program (GUI, model).
 * 
 * @author Csaba Farkas R00117945
 * 
 */
public class PatientController {
    
    private static PatientController instance;
    
    /**
     * Static method that returns an instance of this class. The advantage of 
     * using this method is that this object needs to be created only once in
     * memory, and that instance can be called from anywhere in the project.
     * 
     * @return an instance of this object
     */
    public static PatientController getInstance() {
        if(instance == null) {
            instance = new PatientController();
        }
        return instance;
    }
    
    private PatientManagementInterface patientList;
    private PatientManagementSystemUI gui;
    
    /**
     * This function connects a data structure object that has implemented 
     * PatientManagementInterface interface to the project.
     * 
     * @param patientList an instance of PatientManagementInterface object
     * used as a data structure by the program.
     */
    public void setDataStructure(PatientManagementInterface patientList) {
        this.patientList = patientList;
    }
    
    /**
     * This function connects a user interface object that has implemented
     * PatientManagementSystemUI interface to the project.
     * 
     * @param gui an instance of PatientManagementSystemUI object used as an 
     * user interface by the program.
     */
    public void setGUIReference(PatientManagementSystemUI gui) {
        this.gui = gui;
    }

    /**
     * Getter function that returns patientList field variable.
     * @return patientList
     */
    public PatientManagementInterface getPatientList() {
        return this.patientList;
    }

    /**
     * Getter function that returns gui field variable.
     * 
     * @return gui
     */
    public PatientManagementSystemUI getGui() {
        return this.gui;
    }
    
}
    
