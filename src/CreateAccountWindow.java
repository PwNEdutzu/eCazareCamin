import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CreateAccountWindow extends AuthenticationWindow {
    private static String username;
    private static String email;
    private static String password;
    private static String accountType;

    public static void init() {
        GridLayout layout = new GridLayout(0, 1);

        JPanel accountTypePanel = new JPanel(layout);
        final JComboBox<String> accountTypeField = new JComboBox<>(AccountTypes.accountTypeValues);
        accountTypePanel.add(new JLabel("Selectati tipul de cont:"));
        accountTypePanel.add(accountTypeField);

        JPanel usernamePanel = new JPanel(layout);
        JTextField usernameField = new JTextField(20);
        usernamePanel.add(new JLabel(("Username:")));
        usernamePanel.add(usernameField);

        JPanel emailPanel = new JPanel(layout);
        JTextField emailField = new JTextField(20);
        emailPanel.add(new JLabel("Email:"));
        emailPanel.add(emailField);

        JPanel passwordPanel = new JPanel(layout);
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(new JLabel("Password:"));
        passwordPanel.add(passwordField);

        JPanel confirmPasswordPanel = new JPanel(layout);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        confirmPasswordPanel.add(new JLabel("Confirm password"));
        confirmPasswordPanel.add(confirmPasswordField);

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionsPanel.add(Box.createVerticalStrut(50)); // a spacer
        JButton backToLogin = new JButton("Inapoi la login");
        JButton createAccountBtn = new JButton("Creeaza contul");
        actionsPanel.add(backToLogin);
        actionsPanel.add(createAccountBtn);

        backToLogin.addActionListener(ae -> {
            authenticationLayout.previous(authenticationPanel);
        });

        // Create new account
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
        createAccountWindowPanel.add(accountTypePanel);
        createAccountWindowPanel.add(emailPanel);
        createAccountWindowPanel.add(passwordPanel);
        createAccountWindowPanel.add(confirmPasswordPanel);
        createAccountWindowPanel.add(actionsPanel);
    }
}