
package main;

import controller.PatientController;
import interfaces.PatientManagementInterface;
import model.PatienLinkedtList;
import model.Patient;
import model.PatientArrayList;
import test.RandomPatientCreator;
import view.PatientManagementMenu;

/**
 * <h1>PatientManagementApplication --- Date of last modification: 30-Apr-2015</h1>
 * This is the main class that does the following:
 * 1. It creates a RandomPatientCreator object, that creates 20 random patients.
 * 2. It creates one of the two data structures. <b>Please uncomment/comment the
 *    appropriate line.</b>
 * 3. By using the PatientController static class, it sets the data structure 
 *    that was created to be the data structure of the program.
 * 4. The data structure is populated with the 20 random patients.
 * 5. All of the patients are printed to the screen.
 * 6. A PatientManagementMenu object is created and is set to be the user interface
 *    of the program.
 * 7. The program is started.
 * 
 * @author Csaba Farkas R00117945
 */
public class PatientManagementApplication {

    public static void main(String[] args) {
        
        RandomPatientCreator creator = new RandomPatientCreator();
        Patient patients[] = creator.createPatients();
        
        //PatientManagementInterface list = new PatienLinkedtList();
        PatientManagementInterface list = new PatientArrayList();
        
        PatientController.getInstance().setDataStructure(list);
        
        for(Patient patient : patients) {
            list.addPatient(patient);
        }
        
        PatientController.getInstance().getPatientList().printPatients();
        PatientManagementMenu menu = new PatientManagementMenu();
        
        PatientController.getInstance().setGUIReference(menu);
        PatientController.getInstance().getGui().start();
    }

}
