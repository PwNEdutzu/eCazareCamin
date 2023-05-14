import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JConnection {
    public static Connection conn;
    public static void ConnecrDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ecazare";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("connected");
//            conn.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static boolean checkEmailExists(String email) {
        boolean exists = false;
        String query = "SELECT * FROM users WHERE email = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error while checking email existence: " + e.getMessage());
        }

        return exists;
    }

    public static boolean createAccount(String username, String email, String password, String accountType) throws SQLException {
        boolean success = false;
        // Check if email already exists in database
        if (checkEmailExists(email)) {
            JOptionPane.showMessageDialog(null, "An account with this email already exists.");
            return false;
        }

        String query = "INSERT INTO users (username, email, password, account_type) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement  = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, accountType);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account created successfully.");
                success = true;
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error while creating account: " + e.getMessage());
        }

        return success;
    }

    public static List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String accountType = rs.getString("account_type");
            User user = new User(id, username, email, password, accountType);
            userList.add(user);
        }
        for (User user : userList) {
            System.out.println(user.getId());
            System.out.println(user.getUsername());
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            System.out.println(user.getAccountType());
            System.out.println("---- USER ----");
        }
        return userList;
    }

    public static boolean checkLogin(String email, String password, String accountType) {
        boolean loggedIn = false;
        try {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND account_type = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, accountType);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                // User exists and matches login credentials
                loggedIn = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedIn;
    }
}
