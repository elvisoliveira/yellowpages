package com.elvisoliveira.yellowpages.ui;

import java.awt.Button;
import java.awt.Panel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EtchedBorder;
import net.miginfocom.swing.MigLayout;

public class ListingWindow {

    private final JDialog cDialog;

    private final JScrollPane listContacts;
    private final JScrollPane listNames;

    public ListingWindow(JFrame window) {
        // setup jdialog
        cDialog = new JDialog(window, "", true);

        // dialog of the detailed information about the selected contact
        cDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        cDialog.setBounds(100, 100, 450, 227);
        cDialog.setResizable(false);
        cDialog.setVisible(false);

        // dialog of contact information (with maps)
        cDialog.setLayout(new MigLayout("", "[grow]", "[grow]"));

        listContacts = new JScrollPane();
        listContacts.setViewportView(new JLabel("Names", JLabel.CENTER));

        listNames = new JScrollPane();
        listNames.setViewportView(new JLabel("Contacts", JLabel.CENTER));

        Button button = new Button("Insert");

        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(listContacts);
        splitPane.setRightComponent(listNames);

        Panel pOptions = new Panel();
        pOptions.setLayout(new MigLayout("inset 0", "[][]", "[]"));
        pOptions.add(button, "cell 0 0");

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setLayout(new MigLayout("", "[grow, fill]", "[grow][30.00, bottom]"));
        panel.add(splitPane, "cell 0 0,grow");
        panel.add(pOptions, "cell 0 1");

        cDialog.getContentPane().add(panel, "cell 0 0, grow");

    }
}
