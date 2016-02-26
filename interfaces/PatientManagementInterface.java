
package interfaces;

import model.Patient;

/**
 * <h1>PatientManagementInterface --- Date of last modification: 26-Apr-2015</h1>
 * This interface contains the functions that must be implemented by both of
 * the data structure classes.
 * 
 * @author Csaba Farkas R00117945
 *
 */
public interface PatientManagementInterface {

    public boolean isEmpty();
    public void addPatient(Patient patient);
    public void removePatient(String patientID);
    public void printPatients();
    public void removeAllPatients();
    public int getNumberOfPatients();
    public void sortBy(int variableIndex);
    public void searchBy(int variableIndex, Object searchTerm);
    public void sortAndDisplayByNumberOfDays();
}
