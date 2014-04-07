package com.elvisoliveira.yellowpages;

import com.elvisoliveira.yellowpages.ui.mainWindow;
import com.elvisoliveira.yellowpages.webservice.geocode;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class yellowpages {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, Exception {

// Metal
// Nimbus
// CDE/Motif
// GTK+
        // Ninbus look and feel
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("GTK+".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        // testing the geocode API
        // geocode geocode = new geocode();
        // geocode.geocode("1600 Amphitheatre Parkway, Mountain View, CA");

        // App startup
        mainWindow.setContacts("");

    }

}
