package ca.cmpt213.as5courseplanner.controllers;

import ca.cmpt213.as5courseplanner.model.*;
import ca.cmpt213.as5courseplanner.wrappers.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoursePlannerController {

    ReadDataFromCSV readData = new ReadDataFromCSV();
    DepartmentManager manager = readData.getManager();

    @GetMapping("/api/about")
    public ApiAboutWrapper getAboutInformation(){
        return new ApiAboutWrapper("SFU course planner", "Manpreet Gill");
    }

    @GetMapping("/api/dump-model")
    public void dumpModel(){
        String dump = readData.dumpSampleData();
        System.out.println(dump);
    }

    @GetMapping("api/departments")
    public List<ApiDepartmentWrapper> getDepartments(){
        return ApiDepartmentWrapper.makeFromDepartments(manager);
    }

    @GetMapping("api/departments/{id}/courses")
    public List<ApiCourseWrapper> getCourses(@PathVariable("id") int id){
        Department department = null;
        for(Department dept: manager){
            if(dept.getDepartmentId() == id){
                department = dept;
            }
        }
        if(department == null){
            throw new FileNotFound("Error: Department not found");
        }

        return ApiCourseWrapper.makeFromCourses(department.getCourseNumbers());
    }

    @GetMapping("api/departments/{deptId}/courses/{courseId}/offerings")
    public List<ApiCourseOfferingWrapper> getOfferings(@PathVariable("deptId") int deptId, @PathVariable("courseId") int courseId){
        Department department = null;
        CatalogNumber catalogNumber = null;
        for(Department dept: manager){
            if(dept.getDepartmentId() == deptId){
                department = dept;
            }
        }
        if(department == null){
            throw new FileNotFound("Error: Department not found");
        }
        List<CatalogNumber> catalogNumbers = department.getCourseNumbers();
        for(CatalogNumber courseNumber: catalogNumbers){
            if(courseNumber.getCourseId() == courseId){
                catalogNumber = courseNumber;
            }
        }
        if(catalogNumber == null){
            throw new FileNotFound("Error: Course not found");
        }
        return ApiCourseOfferingWrapper.makeFromCourseOfferings(catalogNumber.getCourseOfferings());
    }

    @GetMapping("api/departments/{deptId}/courses/{courseId}/offerings/{offeringId}")
    public List<ApiOfferingSectionWrapper> getOfferingSections(@PathVariable("deptId") int deptId, @PathVariable("courseId") int courseId,
                                                               @PathVariable("offeringId") int offeringId){

        Department department = null;
        CatalogNumber catalogNumber = null;
        CourseOffering courseOffering = null;
        for(Department dept: manager){
            if(dept.getDepartmentId() == deptId){
                department = dept;
            }
        }
        if(department == null){
            throw new FileNotFound("Error: Department not found");
        }
        List<CatalogNumber> catalogNumbers = department.getCourseNumbers();
        for(CatalogNumber courseNumber: catalogNumbers){
            if(courseNumber.getCourseId() == courseId){
                catalogNumber = courseNumber;
            }
        }
        if(catalogNumber == null){
            throw new FileNotFound("Error: Course not found");
        }
        List<CourseOffering> courseOfferings = catalogNumber.getCourseOfferings();
        for(CourseOffering offering: courseOfferings){
            if(offering.getOfferingId() == offeringId){
                courseOffering = offering;
            }
        }
        if(courseOffering == null){
            throw new FileNotFound("Error: Course Offering not found");
        }
        return ApiOfferingSectionWrapper.makeFromSections(courseOffering.getOfferingDetails());

    }


    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public  static class FileNotFound extends RuntimeException {
        public FileNotFound() {}
        public FileNotFound(String str) {
            super(str);
        }
    }
}
