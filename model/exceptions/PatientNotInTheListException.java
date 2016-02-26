
package model.exceptions;

/**
 * Custom exception thrown when patient is not in the list.
 * 
 * @author Csaba Farkas R00117945
 */
public class PatientNotInTheListException extends java.lang.RuntimeException {

    public PatientNotInTheListException() {
        super("Patient is not in the list!");
    }

}
