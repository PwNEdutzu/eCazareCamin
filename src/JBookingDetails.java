import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JBookingDetails extends JConnection {

    public static void addBookingDetails(
            int userId, String colegCamera, String domiciliu, String an, String medieAnuala, String medieAdmitere)
            throws SQLException {
        String query = "INSERT INTO booking_details " +
                "(userId, colegCamera, domiciliu, an, medieAnuala, medieAdmitere) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(userId));
            statement.setString(2, colegCamera);
            statement.setString(3, domiciliu);
            statement.setString(4, an);
            statement.setString(5, medieAnuala);
            statement.setString(6, medieAdmitere);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking details added");
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error while adding booking details " + e.getMessage());
        }
    }

    public static void getBookingDetails(String userId) {
        System.out.println(userId);
        try {
            String sql = "SELECT * FROM booking_details WHERE userId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String colegCamera = rs.getString("colegCamera");
                String domiciliu = rs.getString("domiciliu");
                String an = rs.getString("an");
                String medieAnuala = rs.getString("medieAnuala");
                String medieAdmitere = rs.getString("medieAdmitere");

                BookingDetails bookingDetails = new BookingDetails(userId, colegCamera, domiciliu, an, medieAnuala, medieAdmitere);
                Storage.setBookingDetails(bookingDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<BookingDetails> getBookingList()  {
        List<BookingDetails> bookingList = new ArrayList<>();

        try {
            String query = "SELECT * FROM booking_details";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String userId = rs.getString("userId");
                String colegCamera = rs.getString("colegCamera");
                String domiciliu = rs.getString("domiciliu");
                String an = rs.getString("an");
                String medieAnuala = rs.getString("medieAnuala");
                String medieAdmitere = rs.getString("medieAdmitere");
                BookingDetails bookingDetails = new BookingDetails(userId, colegCamera, domiciliu, an, medieAnuala, medieAdmitere);
                bookingList.add(bookingDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingList;
    }
}