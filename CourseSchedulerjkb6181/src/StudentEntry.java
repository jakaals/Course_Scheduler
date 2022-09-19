/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jackb
 */
public class StudentEntry {
    private String studentID;
    private String first;
    private String last;
    
    public StudentEntry(String studentID,String first, String last){
        this.studentID = studentID;
        this.first = first;
        this.last = last;
    }

    public String getstudentID() {
        return studentID;
    }

    public String getfirst() {
        return first;
    }

    public String getlast() {
        return last;
    }
    
    public String toString() {
        return String.format("%s, %s",this.getlast(),this.getfirst());
    }

    
}
