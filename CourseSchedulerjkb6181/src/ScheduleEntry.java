/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jackb
 */

import java.sql.Timestamp;
public class ScheduleEntry {
    private String semester;
    private String courseID;
    private String studentID;
    private String status;
    private Timestamp time;
    
    public ScheduleEntry(String semester,String studentID,String courseID,String status, Timestamp time){
        this.semester = semester;
        this.studentID = studentID;
        this.courseID = courseID;
        this.status = status;
        this.time = time;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTime() {
        return time;
    }
    
    @Override
    public String toString() {
        return String.format("%s",this.getCourseID());
    }
    
    /**public String toStringSpecial() {
        return String.format("%s %s",this.getCourseID(),this.getTime());
    }
    */
    

    
}
