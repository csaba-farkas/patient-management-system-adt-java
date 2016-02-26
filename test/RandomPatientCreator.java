
package test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import model.Patient;
import model.enums.Gender;
import model.enums.MaritalStatus;

/**
 * Class that creates 20 patients with predefined names and random dates.
 * @author Csaba Farkas R00117945
 */
public class RandomPatientCreator {

   
    private String patientID;
    private final String[] randomNames;
    private final String[] randomAddresses;
    private final Gender[] genders;
    private final Calendar[] randomDates;
    private final MaritalStatus[] randomMarrigeStatuses;
    private final String[] randomDoctors;
    private final String[] randomDepartments;
    
    public RandomPatientCreator() {
        this.patientID = "1001";    //Autoincremented
        
        //20
        this.randomNames = new String[]{"Buckley, John", "Smith, Steve", "Doe, Jane", "Barry, Mary", 
                                        "Underwood, Frank", "Downey Jr., Robert", "Murdoch, Matt",
                                        "Wayne, Bruce", "Parker, Peter", "Kent, Clark", "Stark, Eddard",
                                        "Lannister, Tyrion", "Snow, Jon", "Lannister, Jaime", 
                                        "Lannister, Tywin", "Baelish, Petyr", "Stormblessed, Kaladin",
                                        "Davar, Shallan", "Kholin, Jasnah", "Kholin, Elhokar"};
        //20
        this.randomAddresses = new String[]{"Pine Street", "High Street", "Forest Road", "Douglas Street",
                                            "White House", "Starks Industries", "Hell's Kitchen", 
                                            "Gotham City", "New York", "New York", "Winterfell",
                                            "Somewhere beyond the Narrow Sea", "The Wall", "Castelry Rock", 
                                            "Seven Hells", "The Fingers", "Hearhstone",
                                            "Jah Keved", "Kholinar", "Kholinar"};
        
        
        //20 - Paired with names
        this.genders = new Gender[]{Gender.MALE, Gender.MALE, Gender.FEMALE, Gender.FEMALE, Gender.MALE, Gender.MALE, Gender.MALE,
                                    Gender.MALE, Gender.MALE, Gender.MALE, Gender.MALE, Gender.MALE, Gender.MALE, Gender.MALE,
                                    Gender.MALE, Gender.MALE, Gender.MALE, Gender.FEMALE, Gender.FEMALE, Gender.MALE};
        
        //Days in January 2015
        this.randomDates = new Calendar[31];
        for(int i = 1; i <= 31; i++) {
            this.randomDates[i-1] = new GregorianCalendar(2015, 0, i);
        }
        
        this.randomMarrigeStatuses = new MaritalStatus[]{MaritalStatus.MARRIED, MaritalStatus.MARRIED, MaritalStatus.MARRIED, MaritalStatus.MARRIED,
                                                         MaritalStatus.MARRIED, MaritalStatus.MARRIED, MaritalStatus.SINGLE, 
                                                         MaritalStatus.SINGLE, MaritalStatus.SINGLE, MaritalStatus.SINGLE, MaritalStatus.MARRIED,
                                                         MaritalStatus.SINGLE, MaritalStatus.SINGLE, MaritalStatus.SINGLE,
                                                         MaritalStatus.WIDOW_WIDOWER, MaritalStatus.WIDOW_WIDOWER, MaritalStatus.SINGLE,
                                                         MaritalStatus.SINGLE, MaritalStatus.SINGLE, MaritalStatus.MARRIED};
        
        //5 doctors 5 departments
        this.randomDoctors = new String[]{"Dr Alban", "Dr Dre", "Dr Steady", "Dr Wallace", "Dr Shaolin"};
        this.randomDepartments = new String[]{"A&E", "Cardiology", "Hepatology", "Psychiatry", "Haematology"};
    }
    
    public Patient[] createPatients() {
        
        Patient[] patients = new Patient[20];
        int counter = 0;
        
        int lengthOfTreatment;
        int age;
        int docsDepsRadom;
        
        for(int i = 0; i < patients.length; i++) {
            
            age = (int) (Math.random() * 100);
            int randomDateIndex = (int) (Math.random()*31);
            
            lengthOfTreatment = (int) (Math.random() * 31);
            
            docsDepsRadom = (int) (Math.random() * 4 + 1);
            if((randomDateIndex + lengthOfTreatment) > 30) {
                lengthOfTreatment = 30 - randomDateIndex;
            }
            patients[i] =  new Patient(this.patientID, this.randomNames[counter], this.randomAddresses[counter], 
                                   age, this.genders[counter], this.randomDates[randomDateIndex],
                                   this.randomDates[randomDateIndex + lengthOfTreatment], this.randomMarrigeStatuses[counter],
                                   this.randomDoctors[docsDepsRadom], this.randomDepartments[docsDepsRadom]);
            counter++;
            Integer patientId = Integer.parseInt(this.patientID);
            patientId++;
            this.patientID = patientId.toString();
        }
        return patients;
    }
}
