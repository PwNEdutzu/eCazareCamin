import javax.swing.*;
import java.awt.*;

public class ComisieTabs extends WindowRouter {
    public static void create() {
        JTabbedPane comisieJTabs = new JTabbedPane();

        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();

        comisieJTabs.addTab("Tab 1", tab1);
        comisieJTabs.addTab("Tab 2", tab2);

        // Add comisie tabs to home window panel
        homeWindowPanel.add(comisieJTabs, BorderLayout.CENTER);
    }
}
