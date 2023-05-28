import javax.swing.*;
import java.awt.*;

public class ComisieTabs extends WindowRouter {
    public static final JPanel bookingListPanel = new JPanel();
    public static final JPanel dormRepartitionPanel = new JPanel();
    public static void create() {
        JTabbedPane comisieJTabs = new JTabbedPane();

        BookingList.create(bookingListPanel);
        DormRepartitionList.create(dormRepartitionPanel);

        comisieJTabs.addTab("Lista cereri de cazare", bookingListPanel);
        comisieJTabs.addTab("Repartizare", dormRepartitionPanel);

        // Add comisie tabs to home window panel
        homeWindowPanel.add(comisieJTabs, BorderLayout.CENTER);
    }
}
