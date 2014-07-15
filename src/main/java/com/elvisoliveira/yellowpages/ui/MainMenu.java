package com.elvisoliveira.yellowpages.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu {

    public static JMenuBar getMenu() {
        JMenuItem menuExit = new JMenuItem("Exit");
        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem menuList = new JMenuItem("List");
        menuList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu menuFile = new JMenu("File");
        menuFile.add(menuExit);

        JMenu menuContacts = new JMenu("Contacts");
        menuContacts.add(menuList);

        JMenuBar menu = new JMenuBar();
        menu.add(menuFile);
        menu.add(menuContacts);

        return menu;

    }

}
