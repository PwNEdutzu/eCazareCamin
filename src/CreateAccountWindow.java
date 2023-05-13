import javax.swing.*;
import java.sql.SQLException;

public class CreateAccountWindow extends AuthenticationWindow {
    private static String username;
    private static String email;
    private static String password;
    private static String accountType;

    public static void init() {
        JLabel accountTypeLabel = new JLabel("Account Type:");
        JTextField accountTypeField = new JTextField(20);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JButton backToLogin = new JButton("Inapoi la login");
        backToLogin.addActionListener(ae -> {
            authenticationLayout.previous(authenticationPanel);
        });

        // Create new account
        JButton createAccountBtn = new JButton("Creeaza contul");
        createAccountBtn.addActionListener(ae -> {
            System.out.println("createAcconut");
            // Get values from fields
            username = usernameField.getText();
            email = emailField.getText();
            password = String.valueOf(passwordField.getPassword());
            accountType = accountTypeField.getText();
            try {
                JConnection.createAccount(username, email, password, accountType);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Account Created");
        });

        // Panel Initialization
        createAccountWindowPanel.add(accountTypeLabel);
        createAccountWindowPanel.add(accountTypeField);
        createAccountWindowPanel.add(usernameLabel);
        createAccountWindowPanel.add(usernameField);
        createAccountWindowPanel.add(emailLabel);
        createAccountWindowPanel.add(emailField);
        createAccountWindowPanel.add(passwordLabel);
        createAccountWindowPanel.add(passwordField);
        createAccountWindowPanel.add(confirmPasswordLabel);
        createAccountWindowPanel.add(confirmPasswordField);
        createAccountWindowPanel.add(backToLogin);
        createAccountWindowPanel.add(createAccountBtn);

    }
}