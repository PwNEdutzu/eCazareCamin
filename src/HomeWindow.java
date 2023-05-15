import javax.swing.*;

public class HomeWindow extends WindowRouter {
    public static void init() {
        JTextField test = new JTextField(20);

        homeWindowPanel.add(test);

        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(ae -> {
            logoutUser();
        });
        homeWindowPanel.add(logoutButton);
    }
    public static void logoutUser() {
        windowLayout.show(windowPanel, "loginWindowPanel");
        homeWindowPanel.removeAll();
    }
}
