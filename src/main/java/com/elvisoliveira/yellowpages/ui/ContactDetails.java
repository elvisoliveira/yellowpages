package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class ContactDetails {

    // contact information panel    
    public static JDialog contactinfo;

    public ContactDetails(JFrame window) {
        contactinfo = new JDialog(window, "", true);

        // dialog of the detailed information about the selected contact
        contactinfo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        contactinfo.setSize(new Dimension(500, 500));
        contactinfo.setVisible(false);

        // dialog of contact information (with maps)
        contactinfo.setLayout(new MigLayout(""));
    }

    public static void setContactInfo(ContactBean info) {

        // get information
        String name = info.getName();
        String address = info.getAddress();
        String telephone = info.getTelephone();

        contactinfo.getContentPane().removeAll();

        contactinfo.getContentPane().add(new JLabel("Name"), "cell 0 0");
        contactinfo.getContentPane().add(new JLabel(name), "cell 1 0");
        contactinfo.getContentPane().add(new JLabel("Address"), "cell 0 1");
        contactinfo.getContentPane().add(new JLabel(address), "cell 1 1");
        contactinfo.getContentPane().add(new JLabel("Telephone"), "cell 0 2");
        contactinfo.getContentPane().add(new JLabel(telephone), "cell 1 2");

        contactinfo.setTitle("Contact Information");
        contactinfo.revalidate();
        contactinfo.repaint();

        contactinfo.setVisible(true);
    }

}
