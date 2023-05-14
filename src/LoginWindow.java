import javax.swing.JComboBox;
import javax.swing.*;
import java.awt.*;

public class LoginWindow extends AuthenticationWindow {
    private static String email;
    private static String password;
    private static String accountType;
    public static void init() {
        GridLayout layout = new GridLayout(0, 1);

        JPanel accountTypePanel = new JPanel(layout);
        final JComboBox<String> accountTypeField = new JComboBox<>(AccountTypes.accountTypeValues);
        accountTypePanel.add(new JLabel("Selectati tipul de cont:"));
        accountTypePanel.add(accountTypeField);


        JPanel emailPanel = new JPanel(layout);
        JTextField emailField = new JTextField(20);
        emailPanel.add(new JLabel("Email:"));
        emailPanel.add(emailField);


        JPanel passwordPanel = new JPanel(layout);
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(new JLabel("Password:"));
        passwordPanel.add(passwordField);

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionsPanel.add(Box.createVerticalStrut(50)); // a spacer
        JButton loginBtn = new JButton("Conecteaza-te");
        JButton createAccountBtn = new JButton("Creeaza un cont nou");
        actionsPanel.add(loginBtn);
        actionsPanel.add(createAccountBtn);

        // Actions Events
        loginBtn.addActionListener(ae -> {
            email = emailField.getText();
            password = String.valueOf(passwordField.getPassword());
            accountType = accountTypeField.getItemAt(accountTypeField.getSelectedIndex());
            boolean loggedIn = JConnection.checkLogin(email, password, accountType);

            if (!loggedIn) {
                JOptionPane.showMessageDialog(null, "Account not found");
                return;
            }

            System.out.println("Logged in");
        });
        createAccountBtn.addActionListener(ae -> {
            System.out.println("createAcconut");
            authenticationLayout.next(authenticationPanel);
        });

        // Panel Initialization
        loginWindowPanel.add(accountTypePanel);
        loginWindowPanel.add(emailPanel);
        loginWindowPanel.add(passwordPanel);
        loginWindowPanel.add(actionsPanel);
    }
}