package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class ContactDetails {

    // contact information panel    
    private final JDialog cDialog;
    private JPanel cPanel;

    public ContactDetails(JFrame window) {
        // setup jdialog
        cDialog = new JDialog(window, "", true);

        // dialog of the detailed information about the selected contact
        cDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        cDialog.setSize(new Dimension(500, 500));
        cDialog.setVisible(false);

        // dialog of contact information (with maps)
        cDialog.setLayout(new MigLayout("debug", "[grow, fill]"));
    }

    public void setContactInfo(ContactBean info) {

        cPanel = new JPanel();
        cPanel.setLayout(new MigLayout("debug, inset 0"));
        
        cPanel.add(new JLabel("Name"), "cell 0 0");
        cPanel.add(new JLabel("Address"), "cell 0 1");
        cPanel.add(new JLabel("Telephone"), "cell 0 2");
        
        cPanel.add(new JLabel(info.getName()), "cell 1 0, growx");
        cPanel.add(new JLabel(info.getAddress()), "cell 1 1, growx");
        cPanel.add(new JLabel(info.getTelephone()), "cell 1 2, growx");
        
        cDialog.getContentPane().removeAll();
        cDialog.getContentPane().add(cPanel);

        cDialog.setTitle("Contact Information: " + info.getName());
        cDialog.revalidate();
        cDialog.repaint();

        cDialog.setVisible(true);
    }

}
