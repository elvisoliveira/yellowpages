package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class ContactDetails {

    // contact information panel    
    private final JDialog cDialog;
    private JPanel cPanel;
    private JPanel aPanel;

    public ContactDetails(JFrame window) {
        // setup jdialog
        cDialog = new JDialog(window, "", true);

        // dialog of the detailed information about the selected contact
        cDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        cDialog.setSize(new Dimension(400, 160));
        cDialog.setVisible(false);

        // dialog of contact information (with maps)
        cDialog.setLayout(new MigLayout("debug", "[grow, fill]"));
    }

    public void setContactInfo(ContactBean info) {

        cPanel = new JPanel();
        cPanel.setLayout(new MigLayout("", "[][grow]", "[][][][grow]"));

        cPanel.add(new JTextField(info.getName()), "cell 1 2,growx");
        cPanel.add(new JTextField(info.getAddress()), "cell 1 1,growx");
        cPanel.add(new JTextField(info.getTelephone()), "cell 1 0,growx");
        cPanel.add(new JLabel("Name"), "cell 0 2,alignx trailing");
        cPanel.add(new JLabel("Address"), "cell 0 0,alignx trailing");
        cPanel.add(new JLabel("Telephone"), "cell 0 1,alignx trailing");

        cPanel.add(new JSeparator(), "cell 0 3 2 1,growx");
        
        aPanel = new JPanel();
        aPanel.setLayout(new MigLayout("inset 0", "[grow][][]", "[]"));
        aPanel.add(new JButton("Insert"), "cell 0 0");
        aPanel.add(new JButton("Previous"), "cell 1 0");
        aPanel.add(new JButton("Next"), "cell 2 0");

        cPanel.add(aPanel, "cell 0 4 2 1,grow");

        cDialog.getContentPane().removeAll();
        cDialog.getContentPane().add(cPanel, "cell 0 0,grow");

        cDialog.setTitle("Contact Information: " + info.getName());
        cDialog.revalidate();
        cDialog.repaint();

        cDialog.setVisible(true);
    }
}
