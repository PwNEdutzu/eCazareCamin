package authentication;

import javax.swing.*;

public class CreateAccountWindow extends AuthenticationWindow {
    public static void init() {
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JButton backToLogin = new JButton("Inapoi la login");
        backToLogin.addActionListener(ae -> {
            authenticationLayout.previous(authenticationPanel);
        });

        JButton createAccountBtn = new JButton("Creeaza contul");
        createAccountBtn.addActionListener(ae -> {
            System.out.println("createAcconut");
            authenticationLayout.next(authenticationPanel);
        });

        // Panel Initialization
        createAccountWindowPanel.add(usernameLabel);
        createAccountWindowPanel.add(usernameField);
        createAccountWindowPanel.add(passwordLabel);
        createAccountWindowPanel.add(passwordField);
        createAccountWindowPanel.add(confirmPasswordLabel);
        createAccountWindowPanel.add(confirmPasswordField);
        createAccountWindowPanel.add(backToLogin);
        createAccountWindowPanel.add(createAccountBtn);

    }
}