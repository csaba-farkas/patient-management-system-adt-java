
package model;

import java.util.Comparator;

/**
 * <h1>Comparators --- Date of last modification: 13-Apr-2015</h1>
 * This class and its subclasses creates the Comparator objects that are going 
 * to be created during the sorting of the data set. Constructor of
 * ListComparators creates a Comparator object that compares patients by their
 * patient ID.
 * 
 * @author Csaba Farkas R00117945
 */
public class ListComparators implements Comparator<Patient> {
    
    /**
     * Override Comparator.compare method
     * Compares two patients by using their ID as the basis of the comparison.
     * 
     * @param o1 Patient object
     * @param o2 Patient object
     * @return an integer that is used by the Comparator.sort function
     */
    @Override
    public int compare(Patient o1, Patient o2) {
        return o1.getPatientID().compareToIgnoreCase(o2.getPatientID());
    }
    
    //--------------------------------------------------------------------------
    /**
     * <h1>PatientNameComparator --- Subclass of ListComparators</h1>
     * Comparator class.
     * 
     */
    public class PatientNameComparator extends ListComparators {
        
        /**
        * Override Comparator.compare method
        * Compares two patients by using their name as the basis of the comparison. 
        * 
        * @param o1 Patient object
        * @param o2 Patient object
        * @return an integer that is used by the Comparator.sort function
        */
        @Override
        public int compare(Patient o1, Patient o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
        }      
    }
    
    //--------------------------------------------------------------------------
    /**
     * <h1>PatientAddressComparator --- Subclass of ListComparators</h1>
     * Comparator class.
     */
    public class PatientAddressComparator extends ListComparators {

        /**
        * Override Comparator.compare method
        * Compares two patients by using their address as the basis of the comparison. 
        * 
        * @param o1 Patient object
        * @param o2 Patient object
        * @return an integer that is used by the Comparator.sort function
        */
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getAddress().compareToIgnoreCase(o2.getAddress());
        }
    }
    
    //--------------------------------------------------------------------------
    /**
     * <h1>PatientAgeComparator --- Subclass of ListComparators</h1>
     * Comparator class.
     * 
     */
    public class PatientAgeComparator extends ListComparators {

        /**
        * Override Comparator.compare method
        * Compares two patients by using their age as the basis of the comparison. 
        * 
        * @param o1 Patient object
        * @param o2 Patient object
        * @return an integer that is used by the Comparator.sort function
        */
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getAge() - o2.getAge();
        } 
    }
    
    //--------------------------------------------------------------------------
    /**
     * <h1>PatientGenderComparator --- Subclass of ListComparators</h1>
     * Comparator class.
     *
     */
    public class PatientGenderComparator extends ListComparators {

        /**
        * Override Comparator.compare method
        * Compares two patients by using their gender as the basis of the comparison. 
        * 
        * @param o1 Patient object
        * @param o2 Patient object
        * @return an integer that is used by the Comparator.sort function
        */
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getGender().compareTo(o2.getGender());
        }  
    }
    
    //--------------------------------------------------------------------------
    /**
     * <h1>PatientMaritalStatusComparator --- Subclass of ListComparators</h1>
     * Comparator class.
     */
    public class PatientMaritalStatusComparator extends ListComparators {

        /**
        * Override Comparator.compare method
        * Compares two patients by using their marital status as the basis of the comparison. 
        * 
        * @param o1 Patient object
        * @param o2 Patient object
        * @return an integer that is used by the Comparator.sort function
        */
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getMaritalStatus().compareTo(o2.getMaritalStatus());
        }
    }
    
    //--------------------------------------------------------------------------
    /**
     * <h1>PatientAdmitDateComparator --- Subclass of ListComparators</h1>
     * Comparator class.
     *
     */
    public class PatientAdmitDateComparator extends ListComparators  {

        /**
        * Override Comparator.compare method
        * Compares two patients by using their admission date as the basis of the comparison. 
        * 
        * @param o1 Patient object
        * @param o2 Patient object
        * @return an integer that is used by the Comparator.sort function
        */
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getAdmitDate().compareTo(o2.getAdmitDate());
        }
    }
    
    //--------------------------------------------------------------------------
    /**
     * <h1>PatientDischargeDateComparator --- Subclass of ListComparators</h1>
     * Comparator class.
     * 
     */
    public class PatientDischargeDateComparator extends ListComparators {

        /**
        * Override Comparator.compare method
        * Compares two patients by using their discharge date as the basis of the comparison. 
        * 
        * @param o1 Patient object
        * @param o2 Patient object
        * @return an integer that is used by the Comparator.sort function
        */
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getDischargeDate().compareTo(o2.getDischargeDate());
        } 
    }
    
    //--------------------------------------------------------------------------
    /**
     * <h1>PatientDoctorComparator --- Subclass of ListComparators</h1>
     * Comparator class.
     * 
     */
    public class PatientDoctorComparator extends ListComparators {

        /**
        * Override Comparator.compare method
        * Compares two patients by using their doctor's name as the basis of the comparison. 
        * 
        * @param o1 Patient object
        * @param o2 Patient object
        * @return an integer that is used by the Comparator.sort function
        */
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getDoctorsName().compareToIgnoreCase(o2.getDoctorsName());
        }
    }
    
    //--------------------------------------------------------------------------
    /**
     * <h1>PatientDepartmentComparator --- Subclass of ListComparators</h1>
     * Comparator class.
     * 
     */
    public class PatientDepartmentComparator extends ListComparators {

        /**
        * Override Comparator.compare method
        * Compares two patients by using the department as the basis of the comparison. 
        * 
        * @param o1 Patient object
        * @param o2 Patient object
        * @return an integer that is used by the Comparator.sort function
        */
        @Override
        public int compare(Patient o1, Patient o2) {
            return o1.getDepartment().compareToIgnoreCase(o2.getDepartment());
        }
        
    }
    
    //--------------------------------------------------------------------------
    /**
     * <h1>PatientNumberOfDaysInHospitalComparator --- Subclass of ListComparators</h1>
     * Comparator class.
     * 
     */
    public class PatientNumberOfDaysInHospitalComparator extends ListComparators {
        
        /**
        * Override Comparator.compare method
        * Compares two patients by using the number of days they spent in the
        * hospital as the basis of the comparison. 
        * 
        * @param o1 Patient object
        * @param o2 Patient object
        * @return an integer that is used by the Comparator.sort function
        */
        @Override
        public int compare(Patient o1, Patient o2) {
            return (int) (o1.getNumberOfDaysInHospital() - o2.getNumberOfDaysInHospital());
        }
    }
}
