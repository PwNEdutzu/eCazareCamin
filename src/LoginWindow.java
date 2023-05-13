import javax.swing.*;

public class LoginWindow extends AuthenticationWindow {
    private static String username;
    private static String password;
    public static void init() {
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginBtn = new JButton("Conecteaza-te");
        loginBtn.addActionListener(ae -> {
            username = usernameField.getText();
            password = String.valueOf(passwordField.getPassword());
            System.out.println("Login:" + username + password);
        });

        JButton createAccountBtn = new JButton("Creeaza un cont nou");
        createAccountBtn.addActionListener(ae -> {
            System.out.println("createAcconut");
            authenticationLayout.next(authenticationPanel);
        });

        // Panel Initialization
        loginWindowPanel.add(usernameLabel);
        loginWindowPanel.add(usernameField);
        loginWindowPanel.add(passwordLabel);
        loginWindowPanel.add(passwordField);
        loginWindowPanel.add(loginBtn);
        loginWindowPanel.add(createAccountBtn);
    }
}