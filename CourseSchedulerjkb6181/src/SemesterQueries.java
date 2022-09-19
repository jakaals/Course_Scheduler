
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author acv (Template download)
 * @subauthor jkb
 */
public class SemesterQueries {

    private static Connection connection;
    private static PreparedStatement addSemester;
    private static PreparedStatement getSemesterList;
    private static ResultSet resultSet;

    public static void addSemester(String name) {
        //adds a new semester to the semesters arraylist
        connection = DBConnection.getConnection();
        try {
            addSemester = connection.prepareStatement("insert into app.semester (NAME) values (?)");
            addSemester.setString(1, name);
            addSemester.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static ArrayList<String> getSemesterList() {
        //returns a list of all semesters for use in comboboxes
        connection = DBConnection.getConnection();
        ArrayList<String> semester = new ArrayList<String>();
        try {
            getSemesterList = connection.prepareStatement("select NAME from app.semester order by NAME");
            resultSet = getSemesterList.executeQuery();

            while (resultSet.next()) {
                semester.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return semester;

    }

}
