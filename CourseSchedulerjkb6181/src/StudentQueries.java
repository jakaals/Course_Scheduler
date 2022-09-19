
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jackb
 */
public class StudentQueries {

    private static Connection connection;
    //private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudentList;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;

    public static void addStudent(StudentEntry i) {
        //adds a student to the studententry tableset
        connection = DBConnection.getConnection();
        try {
            addStudent = connection.prepareStatement("insert into app.StudentEntry (STUDENTID,FIRSTNAME,LASTNAME) values (?,?,?)");
            addStudent.setString(1, i.getstudentID());
            addStudent.setString(2, i.getfirst());
            addStudent.setString(3, i.getlast());
            addStudent.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static ArrayList<StudentEntry> getAllStudents() {
        //retrieves an arraylist of all students for use in comboboxes
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<>();
        try {
            getStudentList = connection.prepareStatement("select * from app.studentEntry");
            resultSet = getStudentList.executeQuery();

            while (resultSet.next()) {
                students.add(new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return students;

    }

    public static StudentEntry getStudent(String studentID) {
        //retrieves a single student
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<>();
        StudentEntry temp = null;
        try {
            getStudent = connection.prepareStatement("select * from app.studentEntry where StudentID = ?");
            getStudent.setString(1, studentID);
            resultSet = getStudent.executeQuery();

            while (resultSet.next()) {
                temp = (new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return temp;

    }

    public static void dropStudent(String studentID) {
        connection = DBConnection.getConnection();
        try {
            dropStudent = connection.prepareStatement("DELETE FROM app.StudentEntry WHERE StudentId = ?");
            dropStudent.setString(1, studentID);

            dropStudent.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
