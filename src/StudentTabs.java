import javax.swing.*;
import java.awt.*;

public class StudentTabs extends WindowRouter {
    public static void create() {
        // Student details panel creation
        JTabbedPane studentJTabs = new JTabbedPane();

        // Adding tabs to studentDetailsPane
        JPanel studentDetailsPanel = new JPanel();
        JPanel requestDormBooking = new JPanel();

        studentJTabs.addTab("Detalii Student", studentDetailsPanel);
        studentJTabs.addTab("Cerere de cazare", requestDormBooking);

        // Add student tabs to home window panel
        homeWindowPanel.add(studentJTabs, BorderLayout.CENTER);
    }
}
