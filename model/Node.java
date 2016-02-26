
package model;

/**
 * <h1>Node --- Date of last modification: 14-Apr-2015</h1>
 * This class is the basic element of the linked list data structure. Each node
 * contains a Patient object, and two pointers that are pointing to the next and
 * to the previous Node objects in the list.
 * 
 * @author Csaba Farkas R00117945
 */
public class Node {
    
    //Instance variables
    private Patient patient;            //Indicates a Patient object that is stored in the Node
    private Node previous;              //Indicates the Node object previous to the Node
    private Node next;                  //Indicates the Node object next to the Node
    
    /**
     * Constructor method 1 --- Constructs a "dummy" Node where the Patient object
     * is a dummy object that stores no data. 
     */
    public Node() {
        patient = new Patient();
    }
    
    /**
     * Constructor method 2 --- Constructs a Node object that stores a 'real' 
     * patient object with 'real' data.
     * 
     * @param previous Node object previous to this object
     * @param next Node object next to this object
     * @param patient Patient object to be stored in this Node
     */
    public Node(Node previous, Node next, Patient patient) {
        this.previous = previous;
        this.next = next;
        this.patient = patient;
    }
    
    /**
     * Getter method - patient
     * 
     * @return patient
     */
    public Patient getPatient() {
        return this.patient;
    }

    /**
     * Setter method - patient
     * @param patient Patient object
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Getter method - previous
     * 
     * @return previous 
     */
    public Node getPrevious() {
        return this.previous;
    }

    /**
     * Setter method - previous
     * 
     * @param previous Node object used as a pointer.
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }
    
    /**
     * Getter method - next
     * 
     * @return next 
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * Setter method - next
     * 
     * @param next Node object used as a pointer.
     */
    public void setNext(Node next) {
        this.next = next;
    }
    
}
