package ca.cmpt213.as5courseplanner.model;

/**
 * Accepts semester code and holds/returns semester name and year
 */

public class Semester {
    private String code;
    private String term;
    private int year;

    public Semester(String code) {
        this.code = code.trim();
        generateDetails();
    }

    private void generateDetails(){
        switch(code.substring(3)){
            case "1":
                term = "Spring";
                break;
            case "4":
                term = "Summer";
                break;
            case "7":
                term = "Fall";
                break;
        }
        int X = Integer.parseInt(code.substring(0, 1));
        int Y = Integer.parseInt(code.substring(1, 2));
        int Z = Integer.parseInt(code.substring(2, 3));
        year = 1900 + 100*X + 10*Y + 1*Z;
    }

    public String getCode() {
        return code;
    }

    public String getTerm() {
        return term;
    }

    public int getYear() {
        return year;
    }
}
