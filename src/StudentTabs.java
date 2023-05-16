import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class StudentTabs extends WindowRouter {
    private static final User loggedUser = Storage.getLoggedUser();
    private static final JPanel studentDetailsPanel = new JPanel();

    public static void create() {
        // Student details panel creation
        JTabbedPane studentJTabs = new JTabbedPane();

        // Adding tabs to studentDetailsPane
        createStudentsDetails();
        JPanel requestDormBooking = new JPanel();

        studentJTabs.addTab("Detalii Student", studentDetailsPanel);
        studentJTabs.addTab("Cerere de cazare", requestDormBooking);

        // Add student tabs to home window panel
        homeWindowPanel.add(studentJTabs, BorderLayout.CENTER);
    }

    public static void createStudentsDetails() {
        studentDetailsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JTextField numeField = new JTextField(20);
        JTextField prenumeField = new JTextField(20);
        JTextField facultateField = new JTextField(20);
        JTextField facultateAnField = new JTextField(20);
        JTextField specializareField = new JTextField(20);
        JTextField cnpField = new JTextField(20);
        JTextField domiciliuField = new JTextField(20);
        final JComboBox<String> tipDeStudiiDropDown = new JComboBox<>(new String[]{"Cu Taxa", "Fara Taxa"});

        // Get Student Details from backend if exists and populate data into fields
        JStudentDetails.getStudentDetails(String.valueOf(loggedUser.getId()));
        StudentDetails studentDetails = Storage.getStudentDetails();

        // Access StudentDetails in Storage
        if (studentDetails != null) {
            numeField.setText(studentDetails.getNume());
            prenumeField.setText(studentDetails.getPrenume());
            facultateField.setText(studentDetails.getFacultate());
            facultateAnField.setText(studentDetails.getAnFacultate());
            specializareField.setText(studentDetails.getSpecializare());
            cnpField.setText(studentDetails.getCNP());
            domiciliuField.setText(studentDetails.getDomiciliu());
            tipDeStudiiDropDown.setSelectedItem(studentDetails.getTipDeStudii());
        }

        studentDetailsPanel.add(new JLabel("Nume:"));
        studentDetailsPanel.add(numeField);
        studentDetailsPanel.add(new JLabel("Prenume:"));
        studentDetailsPanel.add(prenumeField);
        studentDetailsPanel.add(new JLabel("Facultate:"));
        studentDetailsPanel.add(facultateField);
        studentDetailsPanel.add(new JLabel("An Facultate:"));
        studentDetailsPanel.add(facultateAnField);
        studentDetailsPanel.add(new JLabel("Specializare:"));
        studentDetailsPanel.add(specializareField);
        studentDetailsPanel.add(new JLabel("CNP:"));
        studentDetailsPanel.add(cnpField);
        studentDetailsPanel.add(new JLabel("Domiciliu:"));
        studentDetailsPanel.add(domiciliuField);
        studentDetailsPanel.add(new JLabel("Tip de studii:"));
        studentDetailsPanel.add(tipDeStudiiDropDown);

        JLabel emptySpace = new JLabel("");
        JButton submitDetails = new JButton("Salveaza");
        studentDetailsPanel.add(emptySpace);
        studentDetailsPanel.add(submitDetails);

        submitDetails.addActionListener(ae -> {
            String nume = numeField.getText();
            String prenume = prenumeField.getText();
            String facultate = facultateField.getText();
            String anFacultate = facultateAnField.getText();
            String specializare = specializareField.getText();
            String CNP = cnpField.getText();
            String domiciliu = domiciliuField.getText();
            String tipDeStudii = tipDeStudiiDropDown.getItemAt(tipDeStudiiDropDown.getSelectedIndex());

            // Add student details to students_details table in database/backend
            try {
                JStudentDetails.addStudentsDetails(loggedUser.getId(),nume, prenume, facultate, anFacultate, specializare, CNP, domiciliu, tipDeStudii);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
