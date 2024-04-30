package ca.cmpt213.as5courseplanner.wrappers;

import ca.cmpt213.as5courseplanner.model.CatalogNumber;

import java.util.ArrayList;
import java.util.List;

public class ApiCourseWrapper {
    public long courseId;
    public String catalogNumber;

    public static ApiCourseWrapper makeFromCourse(CatalogNumber catalogNumber){
        ApiCourseWrapper wrapper = new ApiCourseWrapper();
        wrapper.courseId = catalogNumber.getCourseId();
        wrapper.catalogNumber = catalogNumber.getCourseNumber();
        return wrapper;
    }

    public static List<ApiCourseWrapper> makeFromCourses(Iterable<CatalogNumber> catalogNumbers){
        List<ApiCourseWrapper> wrappers = new ArrayList<>();
        for(CatalogNumber catalogNumber: catalogNumbers){
            ApiCourseWrapper wrapper = ApiCourseWrapper.makeFromCourse(catalogNumber);
            wrappers.add(wrapper);
        }
        return wrappers;
    }
}
