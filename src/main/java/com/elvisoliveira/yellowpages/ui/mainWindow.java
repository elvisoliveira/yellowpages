package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.beans.contactbean;
import com.elvisoliveira.yellowpages.webservice.telelistas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class mainWindow {

    private static JScrollPane conatctsListing;
    private static JTree tree;
    private static JFrame window;
    private static JTextField searchInput;
    private static JButton searchButton;
    private static List<String> strings;

    public static void setContacts(List<contactbean> contactsList) {

        window = new JFrame("YellowPages");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 400);
        window.setVisible(true);
        window.setResizable(true);

        // Search Fields
        searchInput = new JTextField();
        searchInput.setText("Maria");

        searchButton = new JButton();
        searchButton.setText("Buscar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    telelistas.telelistas(searchInput.getText());
                } catch (IOException ex) {
                    Logger.getLogger(mainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

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

        conatctsListing = new JScrollPane();
        conatctsListing.setViewportView(tree);

        GroupLayout layout = new GroupLayout(window.getContentPane());

        window.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout
                        .createSequentialGroup()
                        .addComponent(searchInput, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton))
                .addComponent(conatctsListing)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout
                        .createSequentialGroup()
                        .addGroup(layout
                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(searchInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchButton))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(conatctsListing, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addContainerGap())
        );

    }

}
