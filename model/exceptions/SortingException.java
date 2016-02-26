
package model.exceptions;

/**
 * Custom exception thrown when list is already sorted or number of items stored
 * in list is 1.
 * 
 * @author Csaba Farkas R00117945
 */
public class SortingException extends java.lang.RuntimeException {

    public SortingException() {
        super();
    }
    
    public SortingException(String message) {
        super(message);
        System.out.println(message);
    }
}
