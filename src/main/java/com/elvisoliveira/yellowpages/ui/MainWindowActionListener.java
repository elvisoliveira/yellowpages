package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.webservice.Telelistas;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindowActionListener implements ActionListener {

    private final String action;
    private final ArrayList<String> arguments;

    private static final JPanel contactPanel = new JPanel();
    private static final JFrame contactWindow = new JFrame();

    MainWindowActionListener(String action, ArrayList<String> arguments) {
        this.action = action;
        this.arguments = arguments;
    }

    @Override
    public final void actionPerformed(ActionEvent e) {
        // switch the click statements
        switch (this.action) {
            // if the clicked action was on the "View on Browser" buttom
            case "viewOnBrowser":
                try {
                    Desktop.getDesktop().browse(new URI((String) arguments.get(0)));
                }
                catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            // if the clicked action was on the "Details" buttom
            case "viewOnApp":

                Integer userInfo = Telelistas.getUserID((String) arguments.get(0));

                MainWindow.setContactInfo(Telelistas.getContactInfo(userInfo));

                break;
        }
    }
}
