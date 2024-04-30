package ca.cmpt213.as5courseplanner.wrappers;

import ca.cmpt213.as5courseplanner.model.Department;
import ca.cmpt213.as5courseplanner.model.DepartmentManager;

import java.util.ArrayList;
import java.util.List;

public class ApiDepartmentWrapper {
    public long deptId;
    public String name;

    public static ApiDepartmentWrapper makeFromDepartment(Department department){
        ApiDepartmentWrapper wrapper = new ApiDepartmentWrapper();
        wrapper.deptId = department.getDepartmentId();
        wrapper.name = department.getSubject();

        return wrapper;
    }

    public static List<ApiDepartmentWrapper> makeFromDepartments(DepartmentManager manager){
        List<ApiDepartmentWrapper> wrappers = new ArrayList<>();
        for(Department department: manager){
            ApiDepartmentWrapper wrapper = ApiDepartmentWrapper.makeFromDepartment(department);
            wrappers.add(wrapper);
        }
        return wrappers;
    }
}
