package com.elvisoliveira.yellowpages;

import com.elvisoliveira.yellowpages.ui.mainWindow;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class yellowpages {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

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

        // App startup
        mainWindow.setContacts("");

    }

}
