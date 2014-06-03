package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import com.elvisoliveira.yellowpages.webservice.Telelistas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Document;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class MainWindow {

    private static JScrollPane contactsListing;
    private static JTable table;
    private static DefaultTableModel contactsTable;
    private static JTextField searchInput;
    private static JButton searchButton;
    private static JButton viewButton;
    private static JButton detailsButton;
    private static ArrayList<Map> contactsArray;
    
    private static final ArrayList<String> arguments = new ArrayList();
    private static final JFrame window = new JFrame("YellowPages");
    private static final JPanel panel = new JPanel();
    private static final JPanel panelContact = new JPanel();

    public static void setContacts(String name) throws IOException {

        // layout configuration        
        panel.setLayout(new MigLayout());

        // serach field
        searchInput = new JTextField();
        // position the serach field in MigLayout
        panel.add(searchInput, "growx, growy, split 2");

        // search button
        searchButton = new JButton();
        searchButton.setText("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                searchButton();
            }
        });
        // position the searchButton in MigLayout
        panel.add(searchButton, "wrap");

        // instantiate the widget that will list the contacts
        contactsListing = new JScrollPane();
        // in the beginning of the execution
        // the name is not set, give the user instructions to search
        contactsListing.setViewportView(new JLabel("Search a contact", JLabel.CENTER));
        // set the widget dimentions, required to define layout properties
        contactsListing.setPreferredSize(new Dimension(400, 400));
        // position the scroll panel in MigLayout
        panel.add(contactsListing, "wrap");

        // panel of the contact
        panelContact.setLayout(new AbsoluteLayout());
        panelContact.setVisible(false);
        panel.add(panelContact, "growx");

        // window configuration
        window.add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        window.setResizable(false);

    }

    public static void searchButton() {

        try {

            // get the loading image
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream in = classLoader.getResourceAsStream("ajax-loader.gif");
            Image ajaxLoaders = Toolkit.getDefaultToolkit().createImage(IOUtils.toByteArray(in));

            // BufferedImage ajaxLoaders = ImageIO.read(stream);
            // set the loading screen, while the SwingWorker class is working
            contactsListing.setViewportView(new JLabel("loading... ", new ImageIcon(ajaxLoaders), JLabel.CENTER));
            // get the inputted name
            final String name = searchInput.getText();
            // set the SwingWorker class as a buffer streammer
            SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
                @Override // required implementation
                public Void doInBackground() throws IOException {
                    // if isser, remove the detailed pannel
                    panelContact.removeAll();
                    panelContact.setMinimumSize(new Dimension(0, 0));
                    panelContact.setVisible(false);
                    panel.validate();
                    // this will be executed in background
                    changeContacts(name);
                    // return anything
                    return null;
                }
            };
            // make the swingWorker work
            swingWorker.execute();
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void changeContacts(String name) throws IOException {
        // make the request and return the Document
        Document document = Telelistas.generateDocument(name);
        // from the requested Document, verify how many contacts were returned
        Integer total = Telelistas.totalContacts(document);
        // if the total of returned contacts are zero, feedback one messange
        if (total.equals(0)) {
            contactsListing.setViewportView(new JLabel("None contacts with this name", JLabel.CENTER));
        } // if the total of returned contacts are greater than zero, return them
        else {
            List<ContactBean> contactsList = Telelistas.telelistas(document);

            table = new JTable();

            contactsArray = new ArrayList<>();
            contactsTable = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            contactsTable.addColumn("Name");
            contactsTable.addColumn("Address");

            for (ContactBean object : contactsList) {

                Map<String, String> map = new HashMap<>();

                map.put("name", object.getName());
                map.put("address", object.getAddress());
                map.put("link", object.getLink());

                contactsArray.add(map);

                contactsTable.addRow(new Object[]{object.getName(), object.getAddress().trim()});
            }

            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectContact(contactsArray.get(table.getSelectedRow()));
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            table.setModel(contactsTable);

            contactsListing.setViewportView(table);
        }

    }

    public static void selectContact(final Map info) {

        arguments.clear();
        arguments.add((String) info.get("link"));
        
        // view detailed information on browser
        viewButton = new JButton();
        viewButton.setText("View on Browser");
        viewButton.addActionListener(new MainWindowActionListener("viewOnBrowser", arguments));
        
        // view detailed information on app
        detailsButton = new JButton();
        detailsButton.setText("Details");
        detailsButton.addActionListener(new MainWindowActionListener("viewOnApp", arguments));

        panelContact.removeAll();
        panelContact.setLayout(new AbsoluteLayout());
        panelContact.setMinimumSize(new Dimension(400, 115));
        panelContact.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        panelContact.setVisible(true);

        panelContact.add(new JLabel((String) info.get("name")), new AbsoluteConstraints(10, 5, 380, 20));
        panelContact.add(new JLabel((String) info.get("address")), new AbsoluteConstraints(10, 25, 380, 20));
        panelContact.add(new JLabel((String) info.get("link")), new AbsoluteConstraints(10, 45, 380, 20));
        panelContact.add(new JSeparator(), new AbsoluteConstraints(10, 70, 380, -1));
        panelContact.add(viewButton, new AbsoluteConstraints(10, 80, -1, -1));
        panelContact.add(detailsButton, new AbsoluteConstraints(130, 80, -1, -1));

        panel.validate();

    }

}
