import java.awt.*;
import javax.swing.*;

public class AuthenticationWindow extends JPanel {
    public static CardLayout authenticationLayout;
    public static JPanel authenticationPanel;
    public static JPanel loginWindowPanel = new JPanel();
    public static JPanel createAccountWindowPanel = new JPanel();

    public AuthenticationWindow() {
        LoginWindow.init();
        CreateAccountWindow.init();
        // Create the card layout
        authenticationLayout = new CardLayout();

        // Create the card panel
        authenticationPanel = new JPanel();
        authenticationPanel.setLayout(authenticationLayout);

        // Set the layout of loginWindowPanel and createAccountWindowPanel to BoxLayout with Y_AXIS
        loginWindowPanel.setLayout((new BoxLayout (loginWindowPanel, BoxLayout.Y_AXIS)));
        createAccountWindowPanel.setLayout((new BoxLayout (createAccountWindowPanel, BoxLayout.Y_AXIS)));

        // Add some components(PANELS) to the card panel
        authenticationPanel.add(loginWindowPanel, "card1");
        authenticationPanel.add(createAccountWindowPanel, "card1");

        // Add the card panel and button panel to this panel
        add(authenticationPanel);
    }

    public static void init() {
        // Create the panel and add it to a JFrame
        AuthenticationWindow panel = new AuthenticationWindow();
        JFrame frame = new JFrame("Authentication");
        frame.getContentPane().add(panel);
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}