# Course Planner API
The Course Planner API provides information about courses offered by a university, including departments, courses, course offerings, and offering sections.

## Endpoints
### About Information
- URL: `/api/about`
- Method: GET
- Description: Returns basic information about the course planner.
- Response: JSON containing the name of the course planner and the developer's name.
  
### Dump Model
- URL: `/api/dump-model`
- Method: GET
- Description: Prints a dump of the sample data from the model to the console.
- Response: None (Prints to console)

### Departments
- URL: `/api/departments`
- Method: GET
- Description: Returns a list of departments available in the course planner system.
- Response: JSON array containing department information.
  
### Courses
- URL: `/api/departments/{id}/courses`
- Method: GET
- Description: Returns a list of courses offered by the specified department.
- Parameters: `{id}` - Department ID
- Response: JSON array containing course information.
  
### Course Offerings
- URL: `/api/departments/{deptId}/courses/{courseId}/offerings`
- Method: GET
- Description: Returns a list of course offerings (sections) for the specified course.
- Parameters: `{deptId}` - Department ID, `{courseId}` - Course ID
- Response: JSON array containing course offering information.
  
### Offering Sections
- URL: `/api/departments/{deptId}/courses/{courseId}/offerings/{offeringId}`
- Method: GET
- Description: Returns information about the sections available for the specified course offering.
- Parameters: `{deptId}` - Department ID, `{courseId}` - Course ID, `{offeringId}` - Offering ID
- Response: JSON array containing offering section information.
  
### Exception Handling
The API handles exceptions using custom exception classes. If requested data is not found, a `404 Not Found` status code is returned.

## Installation
1. Clone the repository to your local machine
  ```
  git clone <repository-url>
  ```
2. Navigate to the project directory:
  ```
  cd as5courseplanner
  ```
3. Run the application using Maven:
  ```
  mvn spring-boot:run
  ```

## Dependencies
- Java Development Kit (JDK) 8 or higher
- Maven (for building and running the application)

## Testing
You can test the API endpoints using tools like Postman or cURL. Here are some example requests:
- Get About Information
```
GET /api/about
```
- Get Departments
```
GET /api/departments
```
- Get Courses for a Department
```
GET /api/departments/{id}/courses
```
- Get Course Offerings for a Course
```
GET /api/departments/{deptId}/courses/{courseId}/offerings
```
- Get Offering Sections for a Course Offering:
```
GET /api/departments/{deptId}/courses/{courseId}/offerings/{offeringId}
```
