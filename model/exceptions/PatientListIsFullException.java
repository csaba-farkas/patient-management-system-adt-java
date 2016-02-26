
package model.exceptions;

/**
 * Custom exception that is thrown when list is full and user attempts to add a 
 * new patient to the list.
 * 
 * @author Csaba Farkas R00117945
 */
public class PatientListIsFullException extends java.lang.RuntimeException {
    
    public PatientListIsFullException() {
        super("List is full. Patient cannot be added to the list!");
    }
}
