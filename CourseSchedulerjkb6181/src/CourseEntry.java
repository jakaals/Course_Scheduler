
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jackb
 */
public class CourseEntry {
    private String semester;
    private String courseID;
    private String description;
    private Integer seats;
    
    public CourseEntry(String semester,String courseid, String description,Integer seats){
        this.semester = semester;
        this.courseID = courseid;
        this.description = description;
        this.seats = seats;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getDescription() {
        return description;
    }

    public Integer getSeats() {
        return seats;
    }
    
}
