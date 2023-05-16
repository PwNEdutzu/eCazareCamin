import javax.swing.*;
import java.awt.*;

public class HomeWindow extends WindowRouter {

    public static void init() {
        // ---- User Information ----
        User loggedUser = UserStorage.getLoggedUser();
        // Username and accountType Labels
        JPanel userPanel = new JPanel(new GridLayout(2, 1));
        JLabel userNameLabel = new JLabel("Username:");
        JLabel userValueLabel = new JLabel(loggedUser.getUsername());
        JLabel accountTypeLabel = new JLabel("Account Type:");
        JLabel accountTypeValue = new JLabel(loggedUser.getAccountType());
        // Add username and accountType to userPanel
        userPanel.add(userNameLabel);
        userPanel.add(userValueLabel);
        userPanel.add(accountTypeLabel);
        userPanel.add(accountTypeValue);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(ae -> {
            logoutUser();
        });
        logoutPanel.add(logoutButton);

        // Create topside panel and add user and logout button panels
        JPanel accountInformationPanel = new JPanel(new BorderLayout());
        accountInformationPanel.add(userPanel, BorderLayout.WEST);
        accountInformationPanel.add(Box.createHorizontalStrut(500));
        accountInformationPanel.add(logoutPanel, BorderLayout.EAST);

        // Student details pane creation
        JTabbedPane studentDetailsPane = new JTabbedPane();

        // Adding tabs to studentDetailsPane
        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();
        JPanel tab3 = new JPanel();
        studentDetailsPane.addTab("Tab 1", tab1);
        studentDetailsPane.addTab("Tab 2", tab2);
        studentDetailsPane.addTab("Tab 3", tab3);

        // Add all panels to homeWindowPanel
        homeWindowPanel.add(accountInformationPanel, BorderLayout.NORTH);
        homeWindowPanel.add(studentDetailsPane, BorderLayout.CENTER);
    }

    public static void logoutUser() {
        windowLayout.show(windowPanel, "loginWindowPanel");
        homeWindowPanel.removeAll();
        UserStorage.clearLoggedUser();
    }
}
