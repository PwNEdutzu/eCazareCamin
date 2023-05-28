import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;

public class AccountSettings {
    public static void init(JPanel accountPanel) {
        Icon testIcon = new ImageIcon("./Images/account icon.png");
        Icon accountIcon = new ImageIcon(((ImageIcon) testIcon).getImage().getScaledInstance(28, 19, java.awt.Image.SCALE_SMOOTH));
        JButton accountBtn = new JButton(accountIcon);
        accountBtn.setPreferredSize(new Dimension(35, 25));
        accountPanel.add(accountBtn);
        accountBtn.addActionListener(ae -> {
            changePasswordPanel();
        });
    }

    public static void changePasswordPanel() {
        JPanel changePassPanel = new JPanel();
        changePassPanel.setLayout((new BoxLayout (changePassPanel, BoxLayout.Y_AXIS)));
        JTextField currentPassField = new JTextField(10);
        JTextField newPassField = new JTextField(10);
        JTextField confirmPassField = new JTextField(10);

        changePassPanel.add(new JLabel("Current Password"));
        changePassPanel.add(currentPassField);
        changePassPanel.add(new JLabel("New Password:"));
        changePassPanel.add(newPassField);
        changePassPanel.add(new JLabel("Confirm new Password:"));
        changePassPanel.add(confirmPassField);

        int panelResult = JOptionPane.showConfirmDialog(
                null, changePassPanel,
                "Actualizare parola",
                JOptionPane.OK_CANCEL_OPTION);
    }

}