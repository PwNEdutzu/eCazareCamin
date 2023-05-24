import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JUsers extends JConnection {
    public static User getUserById(String userId, String tableName) {
        User userById = null;
        try {
            String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String accountType = rs.getString("account_type");
                userById = new User(id, username, email, password, accountType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userById;
    }
}
