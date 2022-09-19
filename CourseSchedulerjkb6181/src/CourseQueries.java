
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jackb
 */
public class CourseQueries {

    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement dropCourse;
    private static PreparedStatement getCourseList;
    private static PreparedStatement getSeats;
    private static ResultSet resultSet;
    
    public static void addCourse(CourseEntry i) {
        //adds a new course to the course tableset
        connection = DBConnection.getConnection();
        try {
            addCourse = connection.prepareStatement("insert into app.CourseEntry (SEMESTER,COURSEID,DESCRIPTION,SEATS) values (?,?,?,?)");
            addCourse.setString(1, i.getSemester());
            addCourse.setString(2, i.getCourseID());
            addCourse.setString(3, i.getDescription());
            addCourse.setInt(4, i.getSeats());
            
            addCourse.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
    }
    
    public static void dropCourse(String semester, String coursecode) {
            connection = DBConnection.getConnection();
        try {
            dropCourse = connection.prepareStatement("DELETE FROM app.CourseEntry WHERE SEMESTER=? AND COURSEID=?");
            dropCourse.setString(1, semester);
            dropCourse.setString(2, coursecode);

            dropCourse.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }    
    }

    public static ArrayList<CourseEntry> getAllCourses(String currentSemester) {
        //retrieves all courses and returns them in an arraylist
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<>();
        try {
            getCourseList = connection.prepareStatement("select * from app.courseEntry order by SEMESTER");
            resultSet = getCourseList.executeQuery();
            
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(currentSemester)) {
                    courses.add(new CourseEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
                } else {;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return courses;
        
    }
        public static ArrayList<String> getAllCourseCodes(String currentSemester) {
        //retrieves all course codes and returns them in an arraylist
        connection = DBConnection.getConnection();
        ArrayList<String> courseIDs = new ArrayList<>();
        try {
            getCourseList = connection.prepareStatement("select SEMESTER,COURSEID from app.courseEntry order by SEMESTER");
            resultSet = getCourseList.executeQuery();
            
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(currentSemester)) {
                    courseIDs.add(resultSet.getString(2));
                } else {;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return courseIDs;
        
    }
    
    public static Integer getCourseSeats(String semester, String courseID) {
        //finds the amount of seats possible to fill in a course
        connection = DBConnection.getConnection();
        Integer count = 0;
        try {
            getSeats = connection.prepareStatement("select SEATS from app.courseEntry where SEMESTER = ? and COURSEID = ?");
            getSeats.setString(1,semester);
            getSeats.setString(2,courseID);
            
            resultSet = getSeats.executeQuery();
            
            while (resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return count;
        
    }
    
    
}
