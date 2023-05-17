import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class StudentTabs extends WindowRouter {
    private static final User loggedUser = Storage.getLoggedUser();
    private static final JPanel studentDetailsPanel = new JPanel();
    private static final JPanel requestDormBooking = new JPanel();

    public static void create() {
        // Student details panel creation
        JTabbedPane studentJTabs = new JTabbedPane();

        // Adding tabs to studentDetailsPane
        createStudentsDetails();
        createBookingDetails();

        studentJTabs.addTab("Detalii Student", studentDetailsPanel);
        studentJTabs.addTab("Cerere de cazare", requestDormBooking);

        // Add student tabs to home window panel
        homeWindowPanel.add(studentJTabs, BorderLayout.CENTER);
    }

    public static void createBookingDetails() {
        JBookingDetails.getBookingDetails(String.valueOf(loggedUser.getId()));
        BookingDetails bookingDetails = Storage.getBookingDetails();
        if(bookingDetails != null){
            JTextField submitted = new JTextField("Cererea a fost deja trimisa.");
            requestDormBooking.add(submitted);
            return;
        }

        requestDormBooking.setLayout(new GridLayout(0, 2, 10, 10));
        // Creating fields and labels for requestDormBooking panel
        JComboBox<String> colegCameraDropdown = new JComboBox<>(new String[]{"Student A", "Student B"});
        colegCameraDropdown.setMaximumSize((new Dimension(20, 20)));
        JComboBox<String> anDropdown = new JComboBox<>(new String[]{"1", "2", "3", "4"});
        anDropdown.setSelectedIndex(0); // Sets default selection to AN 1
        anDropdown.setMaximumSize((new Dimension(20, 20)));
        JLabel anLabel = new JLabel("AN:");
        JLabel domiciliuLabel = new JLabel("DOMICILIU:");
        JLabel colegCameraLabel = new JLabel("Coleg camera:");
        JLabel medieAnualaLabel = new JLabel("MEDIA ANUALA:");
        JLabel medieAdmitereLabel = new JLabel("MEDIE ADMITERE:");
        JTextField domiciliuField = new JTextField();
        JTextField medieAnualaField = new JTextField();
        JTextField medieAdmitereField = new JTextField();
        domiciliuField.setMaximumSize(new Dimension(1, 200));
        medieAnualaField.setMaximumSize(new Dimension(1, 10));
        medieAdmitereField.setMaximumSize(new Dimension(1, 1));


        // Add action listener to dropdown
        anDropdown.addActionListener(ae -> {
            int selectedYear = Integer.parseInt((String) anDropdown.getSelectedItem());
            boolean showEntryGrade = (selectedYear == 1); // Show entryGradeField only for year 1
            medieAdmitereLabel.setVisible(showEntryGrade);
            medieAdmitereField.setVisible(showEntryGrade);
            medieAnualaLabel.setVisible(!showEntryGrade);
            medieAnualaField.setVisible((!showEntryGrade));

            if (showEntryGrade) {
                requestDormBooking.remove(medieAnualaLabel);
                requestDormBooking.remove(medieAnualaField);
                requestDormBooking.add(medieAdmitereLabel);
                requestDormBooking.add(medieAdmitereField);
            } else {
                requestDormBooking.remove(medieAdmitereLabel);
                requestDormBooking.remove(medieAdmitereField);
                requestDormBooking.add(medieAnualaLabel);
                requestDormBooking.add(medieAnualaField);
            }
            requestDormBooking.revalidate();
            requestDormBooking.repaint();
        });

        JLabel emptySpace = new JLabel("");
        JButton createBooking = new JButton("Confirma cererea");

        // Add everything to the requestDormBooking panel
        requestDormBooking.add(colegCameraLabel);
        requestDormBooking.add(colegCameraDropdown);
        requestDormBooking.add(domiciliuLabel);
        requestDormBooking.add(domiciliuField);
        requestDormBooking.add(anLabel);
        requestDormBooking.add(anDropdown);
        requestDormBooking.add(medieAdmitereLabel);
        requestDormBooking.add(medieAdmitereField);
        requestDormBooking.add(emptySpace);
        requestDormBooking.add(createBooking);

        createBooking.addActionListener(ae -> {
            String colegCamera = colegCameraDropdown.getItemAt(colegCameraDropdown.getSelectedIndex());
            String an = anDropdown.getItemAt(anDropdown.getSelectedIndex());
            String domiciliu = domiciliuField.getText();
            String medieAnuala = medieAnualaField.getText();
            String medieAdmitere = medieAdmitereField.getText();


            try {
                JBookingDetails.addBookingDetails(loggedUser.getId(), colegCamera, domiciliu, an, medieAnuala, medieAdmitere);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
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
