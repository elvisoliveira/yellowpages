package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import com.elvisoliveira.yellowpages.webservice.Telelistas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import net.miginfocom.swing.MigLayout;

public class ContactDetails {

    // contact information panel    
    private final JDialog cDialog;
    private JPanel cPanel;
    private JPanel aPanel;
    private JButton nButton;
    private JButton pButton;

    private static final JProgressBar progress = new JProgressBar();
    private static final JTextField name = new JTextField();
    private static final JTextField address = new JTextField();
    private static final JTextField telephone = new JTextField();

    public ContactDetails(JFrame window) {
        // setup jdialog
        cDialog = new JDialog(window, "", true);

        // dialog of the detailed information about the selected contact
        cDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        cDialog.setSize(new Dimension(400, 166));
        cDialog.setResizable(false);
        cDialog.setVisible(false);

        // dialog of contact information (with maps)
        cDialog.setLayout(new MigLayout("", "[grow, fill]"));
    }

    public void setContactInfo(ContactBean info, final List<ContactBean> contacts) {

        name.setText(info.getName());
        address.setText(info.getAddress());
        telephone.setText(info.getTelephone());

        nButton = new JButton();
        nButton.setText("Next");
        nButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer target = MainWindow.changeContacts(Boolean.TRUE);
                String contactLink = contacts.get(target).getLink();

                ContactDetails.setContactInfo(contactLink);
            }
        });

        pButton = new JButton();
        pButton.setText("Previous");
        pButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer target = MainWindow.changeContacts(Boolean.FALSE);
                String contactLink = contacts.get(target).getLink();

                ContactDetails.setContactInfo(contactLink);
            }
        });

        aPanel = new JPanel();
        aPanel.setLayout(new MigLayout("inset 0", "[grow][][]", "[]"));
        aPanel.add(new JButton("Insert"), "cell 0 0");
        aPanel.add(pButton, "cell 1 0");
        aPanel.add(nButton, "cell 2 0");

        cPanel = new JPanel();
        cPanel.setLayout(new MigLayout("inset 0", "[][grow]", "[][][][grow]"));
        cPanel.add(name, "cell 1 0, growx");
        cPanel.add(address, "cell 1 2, growx");
        cPanel.add(telephone, "cell 1 1, growx");
        cPanel.add(new JLabel("Name"), "cell 0 0, alignx trailing");
        cPanel.add(new JLabel("Address"), "cell 0 2, alignx trailing");
        cPanel.add(new JLabel("Telephone"), "cell 0 1, alignx trailing");
        cPanel.add(new JSeparator(), "cell 0 3 2 1, growx");
        cPanel.add(aPanel, "cell 0 4 2 1,grow");
        cPanel.add(new JSeparator(), "cell 0 5 2 1, growx");
        cPanel.add(progress, "cell 0 6 2 1, grow");

        cDialog.getContentPane().removeAll();
        cDialog.getContentPane().add(cPanel, "cell 0 0,grow");
        cDialog.setTitle("Contact Information: " + info.getName());
        cDialog.revalidate();
        cDialog.repaint();
        cDialog.setVisible(true);
    }

    private static void setContactInfo(String link) {

        final Integer userInfo = Telelistas.getUserID(link);

        // loading
        progress.setIndeterminate(true);

        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() throws IOException {
                // this will be executed in background
                ContactBean beanContact = Telelistas.getContactInfo(userInfo);

                name.setText(beanContact.getName());
                address.setText(beanContact.getAddress());
                telephone.setText(beanContact.getTelephone());

                progress.setIndeterminate(false);
                
                // return anything
                return null;
            }
        };
        swingWorker.execute();

    }

}
