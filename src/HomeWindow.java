import javax.swing.*;
import java.awt.*;

public class HomeWindow extends WindowRouter {

    public static void init() {
        // ---- User Information ----
        User loggedUser = Storage.getLoggedUser();
        // Username and accountType Labels
        JPanel userPanel = new JPanel(new GridLayout(2, 1));
        JLabel userValueLabel = new JLabel(loggedUser.getUsername());
        JLabel accountTypeValue = new JLabel(loggedUser.getAccountType());
        // Add username and accountType to userPanel
        userPanel.add(new JLabel("Username:"));
        userPanel.add(userValueLabel);
        userPanel.add(new JLabel("Account Type:"));
        userPanel.add(accountTypeValue);

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(ae -> {
            logoutUser();
        });
        Icon testIcon = new ImageIcon("./Images/printer icon.png");
        Icon printIcon = new ImageIcon(((ImageIcon) testIcon).getImage().getScaledInstance(30, 22, java.awt.Image.SCALE_SMOOTH));
        JButton printButton = new JButton(printIcon);
        printButton.setPreferredSize(new Dimension(35, 25));
        printButton.addActionListener(ae ->{
            JTabbedPane panelType = StudentTabs.studentJTabs;

            if (loggedUser.getAccountType().equals(AccountTypes.accountTypeComisie)) {
                panelType = ComisieTabs.comisieJTabs;
            }
            int selectedIndex = panelType.getSelectedIndex();
            String selectedTabTitle = panelType.getTitleAt(selectedIndex);
            Component selectedTabComponent = panelType.getComponentAt(selectedIndex);

            System.out.println("Selected Tab Title: " + selectedTabTitle);
            JPanel panelToPrint = (JPanel) selectedTabComponent;
            Print.init(panelToPrint);
        });
        AccountSettings.init(logoutPanel);
        logoutPanel.add(printButton);
        logoutPanel.add(logoutButton);

        // Create topside panel and add user and logout button panels
        JPanel accountInformationPanel = new JPanel(new GridLayout(0, 2));
        accountInformationPanel.add(userPanel);
        accountInformationPanel.add(logoutPanel);

        // Switch Home Window Content based on account type (STUDENT OR MEMBRU COMISIE)
        if (loggedUser.getAccountType().equals(AccountTypes.accountTypeStudent)) {
            StudentTabs.create();
        }
        if (loggedUser.getAccountType().equals(AccountTypes.accountTypeComisie)) {
            ComisieTabs.create();
        }

        // Add all panels to homeWindowPanel
        homeWindowPanel.add(accountInformationPanel, BorderLayout.NORTH);
    }

    public static void logoutUser() {
        Storage.resetAllStorages();
        windowLayout.show(windowPanel, "loginWindowPanel");
        WindowRouter.resetFrame();
    }
}
