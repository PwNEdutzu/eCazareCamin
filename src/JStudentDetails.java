import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JStudentDetails extends JConnection {
    public static void addStudentsDetails(
            int userId, String nume, String prenume, String facultate, String anFacultate,
            String specializare, String CNP, String domiciliu, String tipDeStudii) throws SQLException {

        String query = "INSERT INTO students_details " +
                "(userId, nume, prenume, facultate, anFacultate, specializare, cnp, domiciliu, tipDeStudii) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement  = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(userId));
            statement.setString(2, nume);
            statement.setString(3, prenume);
            statement.setString(4, facultate);
            statement.setString(5, anFacultate);
            statement.setString(6, specializare);
            statement.setString(7, CNP);
            statement.setString(8, domiciliu);
            statement.setString(9, tipDeStudii);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student Details added");
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error while adding student details " + e.getMessage());
        }
    }

    public static void getStudentDetails(String userId) {
        try {
            String sql = "SELECT * FROM students_details WHERE userId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String facultate = rs.getString("facultate");
                String anFacultate = rs.getString("anFacultate");
                String specializare = rs.getString("specializare");
                String CNP = rs.getString("CNP");
                String domiciliu = rs.getString("domiciliu");
                String tipDeStudii = rs.getString("tipDeStudii");

                StudentDetails studentDetails = new StudentDetails(userId, nume, prenume, facultate, anFacultate, specializare, CNP, domiciliu, tipDeStudii);
                Storage.setStudentDetails(studentDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
