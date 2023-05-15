import java.awt.*;
import javax.swing.*;

public class WindowRouter extends JPanel {
    public static CardLayout windowLayout;
    public static JPanel windowPanel;
    public static JPanel loginWindowPanel = new JPanel();
    public static JPanel createAccountWindowPanel = new JPanel();
    public static JPanel homeWindowPanel = new JPanel();

    public WindowRouter() {
        LoginWindow.init();
        CreateAccountWindow.init();
        // Create the card layout
        windowLayout = new CardLayout();

        // Create the card panel
        windowPanel = new JPanel();
        windowPanel.setLayout(windowLayout);

        // Set the layout of loginWindowPanel and createAccountWindowPanel to BoxLayout with Y_AXIS
        loginWindowPanel.setLayout((new BoxLayout (loginWindowPanel, BoxLayout.Y_AXIS)));
        createAccountWindowPanel.setLayout((new BoxLayout (createAccountWindowPanel, BoxLayout.Y_AXIS)));
        homeWindowPanel.setLayout((new BoxLayout (homeWindowPanel, BoxLayout.Y_AXIS)));

        // Add some components(PANELS) to the card panel
        windowPanel.add(loginWindowPanel, "loginWindowPanel");
        windowPanel.add(createAccountWindowPanel, "createAccountWindowPanel");
        windowPanel.add(homeWindowPanel, "homeWindowPanel");
        // Add the card panel and button panel to this panel
        add(windowPanel);
    }

    public static void init() {
        // Create the panel and add it to a JFrame
        WindowRouter panel = new WindowRouter();
        JFrame frame = new JFrame("Authentication");
        frame.getContentPane().add(panel);
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}