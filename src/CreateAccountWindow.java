import javax.swing.*;
import java.sql.SQLException;

public class CreateAccountWindow extends AuthenticationWindow {
    private static String username;
    private static String email;
    private static String password;
    private static String accountType;

    public static void init() {
        JLabel accountTypeLabel = new JLabel("Selectati tipul de cont:");
        final JComboBox<String> accountTypeField = new JComboBox<>(AccountTypes.accountTypeValues);

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
            // Get values from fields
            username = usernameField.getText();
            email = emailField.getText();
            password = String.valueOf(passwordField.getPassword());
            accountType = accountTypeField.getItemAt(accountTypeField.getSelectedIndex());

            // Check if account already exists
            boolean createAccountWithSuccess = false;
            try {
                createAccountWithSuccess  = JConnection.createAccount(username, email, password, accountType);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (!createAccountWithSuccess) {
                return;
            }

            System.out.println("Account created with success");
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