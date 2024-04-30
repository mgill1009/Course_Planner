# Designed a RESTful API to provide information about courses offered by a university. 

Here's a breakdown of its functionality:

## About Information Endpoint (/api/about):

Returns basic information about the course planner, such as its name and the developer's name.

## Dump Model Endpoint (/api/dump-model):
Prints a dump of the sample data from the model to the console. This endpoint doesn't return any data to the client but is useful for debugging and testing purposes.

## Departments Endpoint (/api/departments):
Returns a list of departments available in the course planner system.

## Courses Endpoint (/api/departments/{id}/courses):
Takes a department ID as a parameter and returns a list of courses offered by that department.

## Course Offerings Endpoint (/api/departments/{deptId}/courses/{courseId}/offerings):
Takes a department ID and a course ID as parameters and returns a list of course offerings (sections) for that course.

## Offering Sections Endpoint (/api/departments/{deptId}/courses/{courseId}/offerings/{offeringId}):
Takes a department ID, a course ID, and an offering ID as parameters and returns information about the sections available for that course offering.

The controller uses wrapper classes (ApiAboutWrapper, ApiDepartmentWrapper, ApiCourseWrapper, ApiCourseOfferingWrapper, ApiOfferingSectionWrapper) to format the data returned by the API endpoints.
