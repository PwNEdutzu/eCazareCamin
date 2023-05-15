import javax.swing.*;

public class HomeWindow extends WindowRouter {

    public static void init() {
        // ---- User Information ----
        User loggedUser = UserStorage.getLoggedUser();
        // Username Labels
        JPanel userPanel = new JPanel();
        JLabel userNameLabel = new JLabel("Username:");
        JLabel userValueLabel = new JLabel(loggedUser.getUsername());
        userPanel.add(userNameLabel);
        userPanel.add(userValueLabel);
        // Account Type Labels
        JPanel accountTypePanel = new JPanel();
        JLabel accountTypeLabel = new JLabel("Account Type:");
        JLabel accountTypeValue = new JLabel(loggedUser.getAccountType());
        accountTypePanel.add(accountTypeLabel);
        accountTypePanel.add(accountTypeValue);


        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(ae -> {
            logoutUser();
        });

        // Add all panels to homeWindowPanel
        homeWindowPanel.add(userPanel);
        homeWindowPanel.add(accountTypePanel);
        homeWindowPanel.add(logoutButton);
    }

    public static void logoutUser() {
        windowLayout.show(windowPanel, "loginWindowPanel");
        homeWindowPanel.removeAll();
        UserStorage.clearLoggedUser();
    }
}
