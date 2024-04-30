package ca.cmpt213.as5courseplanner.model;

import java.util.*;

/**
 * Manages list of all departments
 */

class SortDepartments implements Comparator<Department>
{
    public int compare(Department a, Department b){
        return (a.getSubject().compareTo(b.getSubject()));
    }
}

public class DepartmentManager implements Iterable<Department> {
    private List<Department> departments = new ArrayList<>();
    private int nextDepartmentId;
    private int nextCourseId;
    private int nextOfferingId;
    private int count = 0;

    public DepartmentManager() {

    }

    public void addDepartment(Department department){
        departments.add(department);
        count++;
        nextDepartmentId = department.getDepartmentId() + 1;
        sortDepartments();
    }

    public int getNextDepartmentId() {
        return nextDepartmentId;
    }

    public void setNextDepartmentId(int nextDepartmentId) {
        this.nextDepartmentId = nextDepartmentId;
    }

    public int getNextCourseId() {
        return nextCourseId;
    }

    public void setNextCourseId(int nextCourseId) {
        this.nextCourseId = nextCourseId;
    }

    public int getNextOfferingId() {
        return nextOfferingId;
    }

    public void setNextOfferingId(int nextOfferingId) {
        this.nextOfferingId = nextOfferingId;
    }

    public int getCount() {
        return count;
    }

    public void removeDepartment(Department department){
        departments.remove(department);
        count--;
    }

    public int getId (Department department){
        return department.getDepartmentId();
    }

    private void sortDepartments(){
        Collections.sort(departments, new SortDepartments());
    }

    @Override
    public Iterator<Department> iterator() {
        return departments.iterator();
    }
}
