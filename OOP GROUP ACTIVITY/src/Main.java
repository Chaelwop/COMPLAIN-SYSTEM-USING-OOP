import gui.LoginFrame;
import service.UserManager;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        UserManager.initialize();

        SwingUtilities.invokeLater(() -> {

            new LoginFrame().setVisible(true);

        });
    }
}