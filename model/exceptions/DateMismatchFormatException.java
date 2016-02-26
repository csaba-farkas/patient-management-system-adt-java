
package model.exceptions;

/**
 * Custom exception that is thrown when date user input is not valid.
 * 
 * @author Csaba Farkas R00117945
 */
public class DateMismatchFormatException extends java.lang.RuntimeException {
        
        public DateMismatchFormatException() {
            super("Date is not valid! Please enter a valid date!");
        }
}
