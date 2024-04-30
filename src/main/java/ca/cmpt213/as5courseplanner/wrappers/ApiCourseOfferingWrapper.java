package ca.cmpt213.as5courseplanner.wrappers;

import ca.cmpt213.as5courseplanner.model.CourseOffering;

import java.util.ArrayList;
import java.util.List;

public class ApiCourseOfferingWrapper {
    public long courseOfferingId;
    public String location;
    public String instructors;
    public String term;
    public long semesterCode;
    public int year;

    public static ApiCourseOfferingWrapper makeFromCourseOffering(CourseOffering courseOffering){
        ApiCourseOfferingWrapper wrapper = new ApiCourseOfferingWrapper();
        wrapper.courseOfferingId = courseOffering.getOfferingId();
        wrapper.location = courseOffering.getCampus();
        wrapper.instructors = courseOffering.getInstructor();
        wrapper.term = courseOffering.getSemester().getTerm();
        wrapper.semesterCode = Integer.parseInt(courseOffering.getSemester().getCode());
        wrapper.year = courseOffering.getSemester().getYear();
        return wrapper;
    }

    public static List<ApiCourseOfferingWrapper> makeFromCourseOfferings(Iterable<CourseOffering> courseOfferings){
        List<ApiCourseOfferingWrapper> wrappers = new ArrayList<>();
        for(CourseOffering courseOffering: courseOfferings){
            ApiCourseOfferingWrapper wrapper = ApiCourseOfferingWrapper.makeFromCourseOffering(courseOffering);
            wrappers.add(wrapper);
        }
        return wrappers;
    }

}
