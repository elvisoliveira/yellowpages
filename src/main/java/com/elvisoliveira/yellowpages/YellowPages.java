package com.elvisoliveira.yellowpages;

import com.elvisoliveira.yellowpages.ui.MainWindow;

import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class YellowPages {

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

        MainWindow.MainWindow(null);
    }
}
