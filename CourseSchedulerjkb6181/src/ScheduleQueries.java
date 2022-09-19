
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
public class ScheduleQueries {

    private static Connection connection;
    private static PreparedStatement addSchedule;
    private static PreparedStatement getSeats;
    private static PreparedStatement getSchedule;
    private static PreparedStatement dropStudentCourse;
    private static PreparedStatement getWaitlistedStudents;
    private static PreparedStatement updateEntry;
    private static PreparedStatement dropCourse;
    private static PreparedStatement isScheduled;
    private static ResultSet resultSet;

    public static void addSchedule(ScheduleEntry i) {
        //creates a new schedule entry with the requested data
        connection = DBConnection.getConnection();
        try {
            addSchedule = connection.prepareStatement("insert into app.ScheduleEntry (SEMESTER,STUDENTID,COURSECODE,STATUS,TIMESTAMP) values (?,?,?,?,?)");
            addSchedule.setString(1, i.getSemester());
            addSchedule.setString(2, i.getStudentID());
            addSchedule.setString(3, i.getCourseID());
            addSchedule.setString(4, i.getStatus());
            addSchedule.setTimestamp(5, i.getTime());

            addSchedule.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static void dropStudentScheduleByCourse(String Semester, String studentID, String courseCode) {

        connection = DBConnection.getConnection();
        try {
            dropStudentCourse = connection.prepareStatement("DELETE FROM app.ScheduleEntry WHERE SEMESTER=? AND STUDENTID=? AND COURSECODE=?");
            dropStudentCourse.setString(1, Semester);
            dropStudentCourse.setString(2, studentID);
            dropStudentCourse.setString(3, courseCode);

            dropStudentCourse.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
    
        public static void dropScheduleByCourse(String Semester, String courseCode) {

        connection = DBConnection.getConnection();
        try {
            dropCourse = connection.prepareStatement("DELETE FROM app.ScheduleEntry WHERE SEMESTER=? AND COURSECODE=?");
            dropCourse.setString(1, Semester);
            dropCourse.setString(2, courseCode);

            dropCourse.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
    
    

    public static ArrayList<ScheduleEntry> getScheduleByStudent(String currentSemester, String studentID) {
        //retrieves the schedule of a specified student in a semester
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedules = new ArrayList<>();
        try {
            getSchedule = connection.prepareStatement("select * from app.scheduleEntry WHERE semester = ? and studentID = ?");
            getSchedule.setString(1, currentSemester);
            getSchedule.setString(2, studentID);
            resultSet = getSchedule.executeQuery();

            while (resultSet.next()) {
                schedules.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return schedules;

    }
    
    public static Boolean isScheduled(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        String temp = null;
        Boolean i = null;
        try {
            isScheduled = connection.prepareStatement("select status from app.scheduleEntry where semester = ? and studentID = ? and coursecode = ?");
            isScheduled.setString(1, semester);
            isScheduled.setString(2, studentID);
            isScheduled.setString(3, courseCode);
            
            resultSet = isScheduled.executeQuery();
            while (resultSet.next()) {temp = resultSet.getString(1);}
            if (temp.equalsIgnoreCase("s"))
                {i = true;}
            else {i = false;}
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    
        return i;
    }

    public static Integer getCourseSeats(String currentSemester, String courseCode) {
        //finds the amount of students in a course already
        connection = DBConnection.getConnection();
        Integer count = 0;
        try {
            getSeats = connection.prepareStatement("select count(StudentID) from app.scheduleentry where semester = ? and courseCode = ?");
            getSeats.setString(1, currentSemester);
            getSeats.setString(2, courseCode);

            resultSet = getSeats.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return count;

    }

    public static ArrayList<ScheduleEntry> getWaitListedStudentsByCourse(String semester, String courseCode) {
        //retrieves an arraylist of all waitlisted schedule entrys
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> students = new ArrayList<>();
        try {
            getWaitlistedStudents = connection.prepareStatement("select * from app.ScheduleEntry where semester=? and coursecode=? and status='w' ORDER BY timestamp ASC");
            getWaitlistedStudents.setString(1, semester);
            getWaitlistedStudents.setString(2, courseCode);
            resultSet = getWaitlistedStudents.executeQuery();

            while (resultSet.next()) {
                students.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return students;

    }

    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode) {
        //retrieves an arraylist of all schedules schedule entrys
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> students = new ArrayList<>();
        try {
            getWaitlistedStudents = connection.prepareStatement("select * from app.ScheduleEntry where semester=? and coursecode=? and status='s' ORDER BY timestamp ASC");
            getWaitlistedStudents.setString(1, semester);
            getWaitlistedStudents.setString(2, courseCode);
            resultSet = getWaitlistedStudents.executeQuery();

            while (resultSet.next()) {
                students.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return students;

    }

    public static void updateScheduleEntry(String semester, ScheduleEntry entry) {
        connection = DBConnection.getConnection();
        try {
            updateEntry = connection.prepareStatement("UPDATE app.ScheduleEntry SET status='s' WHERE SEMESTER=? AND STUDENTID=? AND COURSECODE=?");
            updateEntry.setString(1, semester);
            updateEntry.setString(2, entry.getStudentID());
            updateEntry.setString(3, entry.getCourseID());

            updateEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        
    }
}
