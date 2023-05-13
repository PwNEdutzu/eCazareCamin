import javax.swing.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginWindow() {
        super("Login Window");

        // username si input field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        // parola si input field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        // login button
        JButton loginButton = new JButton("Login");

        // cream panoul
        JPanel panel = new JPanel();
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);


        add(panel);

        // proprietatile frameului
        setSize(350, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginWindow();
    }
}