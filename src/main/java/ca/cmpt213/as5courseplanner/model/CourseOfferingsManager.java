package ca.cmpt213.as5courseplanner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages data retrieval for course offerings
 */

public class CourseOfferingsManager {
    DepartmentManager manager;

    public CourseOfferingsManager(DepartmentManager manager) {
        this.manager = manager;
    }

    public List<CourseOffering> getCourseOfferings(String subject, String courseNumber){
        List<CatalogNumber> catalogNumbers = null;
        List<CourseOffering> courseOfferings = null;
        for(Department department: manager){
            if(department.getSubject().equals(subject.trim())){
                catalogNumbers = department.getCourseNumbers();
                break;
            }
        }

        assert catalogNumbers != null;
        for(CatalogNumber catalogNumber: catalogNumbers){
            if(catalogNumber.getCourseNumber().equals(courseNumber.trim())){
                courseOfferings = catalogNumber.getCourseOfferings();
                break;
            }
        }
        return courseOfferings;
    }

    // get course offering for particular semester and campus
    public CourseOffering filterCourseOfferings(List<CourseOffering> allCourseOfferings, String semesterCode, String campus){
        CourseOffering offering = null;
        for(CourseOffering courseOffering: allCourseOfferings){
            if(courseOffering.getSemester().getCode().equals(semesterCode.trim()) && courseOffering.getCampus().equals(campus.trim())){
                offering = courseOffering;
            }
        }
        return offering;
    }

    // find all semester codes from these course offerings
    public List<String> getSemesterCodes(List<CourseOffering> courseOfferings){
        List<String> semesterCodes = new ArrayList<>();
        List<String> semesterCodesDistinct = new ArrayList<>();
        for(CourseOffering courseOffering: courseOfferings){
            semesterCodes.add(courseOffering.getSemester().getCode());
        }
        // remove duplicates
        for(String semesterCode: semesterCodes){
            if(!semesterCodesDistinct.contains(semesterCode)){
                semesterCodesDistinct.add(semesterCode);
            }
        }
        return semesterCodesDistinct;
    }

    // find list of campuses from these courseOfferings for a particular semester
    public List<String> getCampusList(List<CourseOffering> courseOfferings, String semesterCode){
        List<String> locations = new ArrayList<>();
        List<String> locationsDistinct = new ArrayList<>();
        String code;

        for(CourseOffering courseOffering: courseOfferings){
            code = courseOffering.getSemester().getCode();
            if(code.equals(semesterCode.trim())){
                locations.add(courseOffering.getCampus());
            }
        }

        // remove duplicates
        for(String campus: locations){
            if(!locationsDistinct.contains(campus)){
                locationsDistinct.add(campus);
            }
        }
        return locationsDistinct;
    }

    // Returns a list of all sections in this offering details list
    public List<String> getSectionsList(List<OfferingDetails> offeringDetails){
        List<String> sections = new ArrayList<>();
        List<String> sectionsDistinct = new ArrayList<>();

        for(OfferingDetails details: offeringDetails){
            sections.add(details.getSection());
        }

        // remove duplicates

        for(String section: sections){
            if(!sectionsDistinct.contains(section)){
                sectionsDistinct.add(section);
            }
        }

        return sectionsDistinct;
    }
}
