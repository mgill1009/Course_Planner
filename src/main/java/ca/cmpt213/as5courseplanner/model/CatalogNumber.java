package ca.cmpt213.as5courseplanner.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Holds the course number and its list of course offerings
 */

class SortCourseOfferings implements Comparator<CourseOffering>
{
    public int compare(CourseOffering a, CourseOffering b){
        return (a.getSemester().getCode().compareTo(b.getSemester().getCode()));
    }
}

public class CatalogNumber {
    private String department;
    private String courseNumber;
    private int courseId;
    private List<CourseOffering> courseOfferings = new ArrayList<>();

    public String getDepartment() {
        return department;
    }

    public CatalogNumber(String department, String courseNumber, int courseId, CourseOffering courseOffering) {
        this.department = department.trim();
        this.courseNumber = courseNumber.trim();
        this.courseId = courseId;
        this.courseOfferings.add(courseOffering);
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public List<CourseOffering> getCourseOfferings() {
        return courseOfferings;
    }

    public int getCourseId(){
        return courseId;
    }

    public void addCourseOffering(CourseOffering courseOffering){

        courseOfferings.add(courseOffering);

        sortCourseOfferings();
    }

    public void removeCourseOffering(CourseOffering courseOffering){
        courseOfferings.remove(courseOffering);
    }

    private void sortCourseOfferings() {
        Collections.sort(courseOfferings, new SortCourseOfferings());
    }
}
