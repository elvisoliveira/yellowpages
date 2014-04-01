package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.contactbean;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class mainWindow {

    private static JScrollPane conatctsListing;
    private static JList listing;
    private static JTree tree;
    private static JFrame window;
    private static List<String> strings;

    public static void setContacts(List<contactbean> contactsList) {

        window = new JFrame("YellowPages");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 400);
        window.setVisible(true);
        window.setResizable(true);

        conatctsListing = new JScrollPane();

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
        tree.setToolTipText("aaa");
        tree.setAutoscrolls(true);

        conatctsListing.setViewportView(tree);

        GroupLayout layout = new GroupLayout(window.getContentPane());

        window.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(conatctsListing, GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(conatctsListing, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }

}
