package ca.cmpt213.as5courseplanner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds each course offering details - semester, instructors, campus, offering details
 */

public class CourseOffering {
    private int offeringId;
    private String courseNumber;
    private Semester semester;
    private String instructor;
    private String campus;
    private List<OfferingDetails> offeringDetails = new ArrayList<>();

    public CourseOffering(int offeringId, String courseNumber, Semester semester, String instructor, String campus, OfferingDetails offeringDetail) {
        this.offeringId = offeringId;
        this.courseNumber = courseNumber.trim();
        this.semester = semester;
        this.instructor = instructor.trim();
        this.campus = campus.trim();
        this.offeringDetails.add(offeringDetail);
    }

    public int getOfferingId() {
        return offeringId;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public Semester getSemester() {
        return semester;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getCampus() {
        return campus;
    }

    public List<OfferingDetails> getOfferingDetails() {
        return offeringDetails;
    }

    public void addOfferingDetail(OfferingDetails offeringDetail){
        offeringDetails.add(offeringDetail);
    }

    public void removeOfferingDetail(OfferingDetails offeringDetail){
        offeringDetails.remove(offeringDetail);
    }

    public void addInstructor(String instructor){
        if(!instructor.equals("")){
            if(this.instructor.equals("") || this.instructor.equals(" ")){
                this.instructor = this.instructor + instructor;
            }else if(this.instructor.toLowerCase().contains(instructor.toLowerCase())){
                // do nothing

            }else{
                this.instructor = this.instructor + ", " + instructor;
            }
        }

    }

    public boolean hasSection(String section){
        for(OfferingDetails offeringSection: offeringDetails){
            if(offeringSection.getSection().toLowerCase().equals(section.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
