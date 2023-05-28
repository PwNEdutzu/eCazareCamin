import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DormRepartitionList {
    public static void create(JPanel dormRepartitionPanel) {
        JButton generateBtn = new JButton("Creaza lista de repartizare in camin");
        dormRepartitionPanel.add(generateBtn);

        generateBtn.addActionListener(ae -> {
            createList(dormRepartitionPanel);
            // Remove Generate List button from panel
            dormRepartitionPanel.remove(generateBtn);
        });
    }

    public static void createList(JPanel dormRepartitionPanel) {
        List<BookingDetails> repartitionList = JRepartitionList.getRepartitionList();

        // Create a DefaultTableModel with column names
        DefaultTableModel repartitionTable = new DefaultTableModel();
        repartitionTable.addColumn("Student");
        repartitionTable.addColumn("Coleg Camera");
        repartitionTable.addColumn("Domiciliu");
        repartitionTable.addColumn("An");
        repartitionTable.addColumn("M. Anuala");
        repartitionTable.addColumn("M. Admitere");
        repartitionTable.addColumn("Tip Taxa");

        // Add user data to the model
        for (BookingDetails repartition : repartitionList) {
            User userById = JUsers.getUserById(repartition.getUserId(), "users");
            User getColegCameraNameById = JUsers.getUserById(repartition.getColegCamera(), "users");
            if (getColegCameraNameById == null) {
                continue;
            }
            Object[] rowData = {
                    userById.getUsername(),
                    getColegCameraNameById.getUsername(),
                    repartition.getDomiciliu(),
                    repartition.getAn(),
                    repartition.getMedieAnuala(),
                    repartition.getMedieAdmitere(),
                    formatTipTaxa(repartition.getFaraTaxa())
            };
            repartitionTable.addRow(rowData);
        }

        // Create the JTable with the model
        JTable table = new JTable(repartitionTable);

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 340)); // Set preferred size
        dormRepartitionPanel.add(scrollPane);
    }

    public static String formatTipTaxa(Boolean faraTaxa) {
        if (faraTaxa)
            return "Fara Taxa";
        return "Cu Taxa";
    }
}