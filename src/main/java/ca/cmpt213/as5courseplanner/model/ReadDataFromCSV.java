package ca.cmpt213.as5courseplanner.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read Data from CSV and populate model classes
 */

public class ReadDataFromCSV {

    private final String filename = "data/small_data.csv";
    private DepartmentManager manager = new DepartmentManager();

    private OfferingDetails offeringDetails;
    private Semester semester;
    private CourseOffering courseOffering;
    private CatalogNumber catalogNumber;
    private Department department;
    private String section;
    private int departmentId = 0;
    private int courseId = 0;
    private int courseOfferingID = 0;

    private Department foundDepartment;
    private CatalogNumber foundCatalogNumber;
    private CourseOffering foundOffering;

    public ReadDataFromCSV() {
        readData();
    }

    public DepartmentManager getManager() {
        return manager;
    }

    private void readData(){
        BufferedReader br;

        try{
            br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            line = br.readLine();

            while(line != null){
                String[] attributes = line.split(",");
                processData(attributes);

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processData(String[] attributes) {
        String sem = attributes[0].trim();
        String subject = attributes[1].trim();
        String courseNumber = attributes[2].trim();
        String campus = attributes[3].trim();
        int enrollCapacity = Integer.parseInt(attributes[4].trim());
        int enrollTotal = Integer.parseInt(attributes[5].trim());
        String instructor = attributes[6].trim();
        section = attributes[7].trim();

        if(attributes.length >= 8 && attributes[6].length() > 0){
            List<String> instructors = new ArrayList<>();
            for(int i = 6; i < attributes.length - 1; i++){
                instructors.add(attributes[i].replaceAll("\"", "").trim());;
            }
            section = attributes[attributes.length - 1].trim();
            instructor = "";
            for(String instructor1: instructors){
                if(instructors.indexOf(instructor1) != instructors.size()-1){
                    if(instructor1 != "" && instructor1 != "<null>" && instructor1 != "(null)"){
                        instructor = instructor + instructor1 + ", ";
                    }

                }else{
                    instructor = instructor + instructor1;
                }

            }
        }

        if(instructor.equals("<null>") || instructor.equals("(null)")){
            instructor = "";
        }

        offeringDetails = new OfferingDetails(section, enrollCapacity, enrollTotal);
        semester = new Semester(sem);
        courseOffering = new CourseOffering(courseOfferingID, courseNumber, semester, instructor, campus, offeringDetails);
        catalogNumber = new CatalogNumber(subject, courseNumber, courseId, courseOffering);
        department = new Department(departmentId, subject, catalogNumber);

        if (manager != null) {
            if (manager.getCount() > 0) {
                boolean found = false;
                for (Department dep : manager) {
                    if (dep.getSubject().equals(subject)) {
                        foundDepartment = dep;
                        findCatalogNumber(courseNumber, campus, instructor);
                        found = true;
                        break;
                    }
                }
                if(!found){
                    addDepartment();
                }
            }else{
                addDepartment();
            }
        }
    }

    private void addDepartment(){
        manager.addDepartment(department);
        departmentId++;
    }

    private void findCatalogNumber(String courseNumber, String campus, String instructor){
        List<CatalogNumber> courseNumbers = foundDepartment.getCourseNumbers();
        if (courseNumbers != null) {
            boolean found = false;
            for (CatalogNumber catNumber : courseNumbers) {
                if (catNumber.getCourseNumber().equals(courseNumber)) {
                    // found the catalog number
                    foundCatalogNumber = catNumber;
                    found = true;
                    boolean foundOffering = findCourseOffering(campus, instructor);
                    if(!foundOffering){
                        foundOffering = findSimilarCourseOffering(campus, instructor);
                        if(!foundOffering){
                            addCourseOffering();
                        }
                    }
                    break;
                }
            }
            if(!found){
                addCatalogNumber();
            }
        }
    }

    private void addCatalogNumber(){
        manager.removeDepartment(foundDepartment);
        foundDepartment.addCatalogNumber(catalogNumber);
        courseId++;
        manager.setNextCourseId(courseId);
        manager.addDepartment(foundDepartment);
    }

    private boolean findCourseOffering(String campus, String instructor) {
        boolean found = false;
        List<CourseOffering> courseOfferings = foundCatalogNumber.getCourseOfferings();
        if (courseOfferings != null) {
            for (CourseOffering courseOffering : courseOfferings) {
                if (courseOffering.getSemester().getCode().equals(semester.getCode()) && courseOffering.getCampus().equals(campus) &&
                                    courseOffering.getInstructor().equals(instructor)) {
                        foundOffering = courseOffering;
                        addOfferingDetail();
                        found = true;
                        break;
                    }
                }
        }
        return found;
    }

    private boolean findSimilarCourseOffering(String campus, String instructor){
        boolean found = false;
        List<CourseOffering> courseOfferings = foundCatalogNumber.getCourseOfferings();
        if (courseOfferings != null) {
            for (CourseOffering courseOffering : courseOfferings) {
                if (courseOffering.getSemester().getCode().equals(semester.getCode()) && courseOffering.getCampus().equals(campus)) {
                    foundOffering = courseOffering;
                    addInstructor(instructor);
                    addOfferingDetail();
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    private void addCourseOffering(){
        foundDepartment.removeCatalogNumber(foundCatalogNumber);
        manager.removeDepartment(foundDepartment);

        foundCatalogNumber.addCourseOffering(courseOffering);
        courseOfferingID++;
        manager.setNextOfferingId(courseOfferingID);
        foundDepartment.addCatalogNumber(foundCatalogNumber);
        manager.addDepartment(foundDepartment);
    }

    private void addOfferingDetail (){
        foundCatalogNumber.removeCourseOffering(foundOffering);
        foundDepartment.removeCatalogNumber(foundCatalogNumber);
        manager.removeDepartment(foundDepartment);

        if(foundOffering.hasSection(section)){
            List<OfferingDetails> offeringSections = foundOffering.getOfferingDetails();
            for(OfferingDetails offeringSection: offeringSections){
                if(offeringSection.getSection().equals(section)){
                    foundOffering.removeOfferingDetail(offeringSection);
                    offeringSection.addToEnrollmentCap(offeringDetails.getEnrollmentCap());
                    offeringSection.addToEnrollmentTotal(offeringDetails.getEnrollmentTotal());
                    foundOffering.addOfferingDetail(offeringSection);
                    break;
                }
            }
        }else{
            foundOffering.addOfferingDetail(offeringDetails);
        }

        foundCatalogNumber.addCourseOffering(foundOffering);
        foundDepartment.addCatalogNumber(foundCatalogNumber);
        manager.addDepartment(foundDepartment);
    }

    private void addInstructor(String instructor){
        foundCatalogNumber.removeCourseOffering(foundOffering);
        foundDepartment.removeCatalogNumber(foundCatalogNumber);
        manager.removeDepartment(foundDepartment);

        foundOffering.addInstructor(instructor);
        foundCatalogNumber.addCourseOffering(foundOffering);
        foundDepartment.addCatalogNumber(foundCatalogNumber);
        manager.addDepartment(foundDepartment);
    }

    public String dumpSampleData(){
        List<String> locations;
        List<String> semesterCodes;
        CourseOfferingsManager offeringsManager = new CourseOfferingsManager(manager);
        String dumpData = "";

        for(Department department: manager){
            List<CatalogNumber> catalogNumbers = department.getCourseNumbers();
            for(CatalogNumber catalogNumber: catalogNumbers){
                dumpData = dumpData + department.getSubject() + " " + catalogNumber.getCourseNumber() + "\n";
                List<CourseOffering> courseOfferings = catalogNumber.getCourseOfferings();
                semesterCodes = offeringsManager.getSemesterCodes(courseOfferings);
                for(String semester: semesterCodes){
                    locations = offeringsManager.getCampusList(courseOfferings, semester);
                    for(String campus: locations){
                        CourseOffering offering = offeringsManager.filterCourseOfferings(courseOfferings, semester, campus);
                        dumpData = dumpData + "\t" + semester + " in " + campus + " by " + offering.getInstructor();

                        List<OfferingDetails> offeringDetails = offering.getOfferingDetails();

                        dumpData = dumpData + "\n";

                        List<String> sections = offeringsManager.getSectionsList(offeringDetails);
                        for(String section: sections){
                            dumpData = dumpData + "\t\tTYPE=" + section + ", Enrollment=";

                            int enrollCap = 0;
                            int enrollTotal = 0;
                            for(OfferingDetails offDetails: offeringDetails){
                                if(offDetails.getSection().trim().equals(section)){
                                    enrollCap = enrollCap + offDetails.getEnrollmentCap();
                                    enrollTotal = enrollTotal + offDetails.getEnrollmentTotal();
                                }
                            }
                            dumpData = dumpData + enrollTotal + "/" + enrollCap + "\n";
                        }
                    }
                }
            }
        }
        return dumpData;
    }
}
