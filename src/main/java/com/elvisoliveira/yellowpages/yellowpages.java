package com.elvisoliveira.yellowpages;

import com.elvisoliveira.yellowpages.ui.mainWindow;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class yellowpages {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, Exception {

        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            switch (info.getName()) {
                case "Windows":
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                case "GTK+":
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
