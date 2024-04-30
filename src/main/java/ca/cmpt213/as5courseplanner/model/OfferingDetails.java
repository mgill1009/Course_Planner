package ca.cmpt213.as5courseplanner.model;

/**
 *  Holds component type (section), enrolled number and capacity for each course offering
 */

public class OfferingDetails {
    private String section;
    private int enrollmentCap;
    private int enrollmentTotal;

    public OfferingDetails(String section, int enrollmentCap, int enrollmentTotal) {
        this.section = section.trim();
        this.enrollmentCap = enrollmentCap;
        this.enrollmentTotal = enrollmentTotal;
    }

    public String getSection() {
        return section;
    }

    public int getEnrollmentCap() {
        return enrollmentCap;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public void addToEnrollmentCap(int additionalCap){
        enrollmentCap = enrollmentCap + additionalCap;
    }

    public void addToEnrollmentTotal(int additionalTotal){
        enrollmentTotal = enrollmentTotal + additionalTotal;
    }
}
