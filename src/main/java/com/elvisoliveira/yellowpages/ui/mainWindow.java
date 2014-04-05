package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.beans.contactbean;
import com.elvisoliveira.yellowpages.webservice.telelistas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import net.miginfocom.swing.MigLayout;

public class mainWindow {

    private static JScrollPane contactsListing;
    private static JTree tree;
    private static JTextField searchInput;
    private static JButton searchButton;
    private static List<String> strings;

    private static final JFrame window = new JFrame("YellowPages");
    private static final JPanel panel = new JPanel();

    public static void setContacts(String name) throws IOException {

        panel.setLayout(new MigLayout());

        // Search Fields
        searchInput = new JTextField();
        searchInput.setText("Maria");

        panel.add(searchInput, "growx, growy, split 2");

        // Search Button
        searchButton = new JButton();
        searchButton.setText("Buscar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    contactsListing.setViewportView(changeContacts(searchInput.getText()));
                } catch (IOException ex) {
                    Logger.getLogger(mainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        panel.add(searchButton, "wrap");

        //
        contactsListing = new JScrollPane();
        contactsListing.setViewportView(changeContacts(name));

        panel.add(contactsListing);

        // Window configuration
        window.add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(700, 600);
        window.pack();
        window.setVisible(true);
        window.setResizable(false);

    }

    public static JTree changeContacts(String name) throws IOException {

        List<contactbean> contactsList = telelistas.telelistas(name);

        tree = new JTree();

        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("Contacts");

        for (contactbean object : contactsList) {
            DefaultMutableTreeNode nodeName = new DefaultMutableTreeNode(object.getName());
            DefaultMutableTreeNode nodeAddress = new DefaultMutableTreeNode(object.getAddress());
            DefaultMutableTreeNode nodeLink = new DefaultMutableTreeNode(object.getLink());

            nodeName.add(nodeAddress);
            nodeName.add(nodeLink);

            treeNode.add(nodeName);

        }

       
        tree.setModel(new DefaultTreeModel(treeNode));
        tree.setAutoscrolls(true);

        return tree;

    }

}
