import javax.swing.JOptionPane;

public void logout() {

    int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Logout",
            JOptionPane.YES_NO_OPTION
    );

    if (choice == JOptionPane.YES_OPTION) {

        dispose();

        new LoginFrame();
    }
}