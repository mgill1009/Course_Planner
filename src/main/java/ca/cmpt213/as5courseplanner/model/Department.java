package ca.cmpt213.as5courseplanner.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Holds department name and list of course numbers within that
 */

class SortCourses implements Comparator<CatalogNumber>
{
    public int compare(CatalogNumber a, CatalogNumber b){
        return (a.getCourseNumber().compareTo(b.getCourseNumber()));
    }
}

public class Department {
    private int departmentId;
    private String subject;
    private List<CatalogNumber> courseNumbers = new ArrayList<>();

    public int getDepartmentId() {
        return departmentId;
    }

    public Department(int departmentId, String subject, CatalogNumber courseNumber) {
        this.departmentId = departmentId;
        this.subject = subject.trim();
        this.courseNumbers.add(courseNumber);
    }

    public String getSubject() {
        return subject;
    }

    public List<CatalogNumber> getCourseNumbers() {
        return courseNumbers;
    }

    public void addCatalogNumber(CatalogNumber catalogNumber){

        courseNumbers.add(catalogNumber);
        sortCourseNumbers();
    }

    public void removeCatalogNumber(CatalogNumber catalogNumber){
        courseNumbers.remove(catalogNumber);
    }

    private void sortCourseNumbers(){
        Collections.sort(courseNumbers, new SortCourses());
    }
}
