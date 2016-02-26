
package model.exceptions;

/**
 * Custom exception that is thrown when list is empty.
 * 
 * @author Csaba Farkas R00117945
 */
public class PatientListIsEmptyException extends java.lang.RuntimeException {

    public PatientListIsEmptyException() {
        super("List is empty!");
    }
}
