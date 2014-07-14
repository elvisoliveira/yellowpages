package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import com.elvisoliveira.yellowpages.webservice.Telelistas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import org.jsoup.nodes.Document;

public class MainWindow {

    private static JScrollPane contactsListing;
    private static JTable table;
    private static DefaultTableModel contactsTable;
    private static JTextField searchInput;
    private static JButton searchButton;
    private static ArrayList<Map> contactsArray;
    private static JProgressBar progress;

    private static final JFrame window = new JFrame("YellowPages");
    private static final JPanel panel = new JPanel();

    // contact information panel    
    private static final JDialog contactinfo = new JDialog(window, "", true);
    private static final JPanel panelMaps = new JPanel();

    public static void setContacts(String name) throws IOException {

        // serach field
        searchInput = new JTextField();

        // search button
        searchButton = new JButton();
        searchButton.setText("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                searchButton();
            }
        });

        // in the beginning of the execution
        // the name is not set, give the user instructions to search
        // set the widget dimentions, required to define layout properties
        // instantiate the widget that will list the contacts
        contactsListing = new JScrollPane();
        contactsListing.setViewportView(new JLabel("Search a contact", JLabel.CENTER));
        contactsListing.setPreferredSize(new Dimension(400, 400));

        progress = new JProgressBar();
        progress.setIndeterminate(false);

        // layout configuration        
        panel.setLayout(new MigLayout("debug"));
        panel.add(searchInput, "grow, split 2");
        panel.add(searchButton, "wrap");
        panel.add(contactsListing, "wrap");
        panel.add(progress, "growx, wrap");

        // dialog of the detailed information about the selected contact
        contactinfo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        contactinfo.setSize(new Dimension(500, 500));
        contactinfo.setVisible(false);

        // dialog of contact information (with maps)
        contactinfo.setLayout(new MigLayout(""));

        // window configuration
        window.add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        window.setResizable(false);
    }

    public static void searchButton() {
        // loading
        progress.setIndeterminate(true);

        final String name = searchInput.getText();

        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() throws IOException {

                // this will be executed in background
                MainWindow.changeContacts(name);

                // return anything
                return null;
            }
        };
        swingWorker.execute();
    }

    public static void changeContacts(String name) throws IOException {

        // make the request and return the Document
        Document document = Telelistas.generateDocument(name);

        // from the requested Document, verify how many contacts were returned
        Integer total = Telelistas.totalContacts(document);

        // if the total of returned contacts are zero, feedback one messange
        if (total.equals(0)) {
            contactsListing.setViewportView(new JLabel("no contacts with this name", JLabel.CENTER));
        }
        // if the total of returned contacts are greater than zero, return them
        else {

            contactsTable = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            contactsTable.addColumn("Name");
            contactsTable.addColumn("Address");

            List<ContactBean> contactsList = Telelistas.telelistas(document);

            contactsArray = new ArrayList<>();

            for (ContactBean object : contactsList) {

                Map<String, String> map = new HashMap<>();

                map.put("name", object.getName());
                map.put("address", object.getAddress());
                map.put("link", object.getLink());

                contactsArray.add(map);
                contactsTable.addRow(new Object[]{object.getName(), object.getAddress().trim()});
            }

            table = new JTable();
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setModel(contactsTable);
            table.addMouseListener(new MouseAdapter() {
                // right click
                @Override
                public void mouseReleased(MouseEvent event) {
                    Integer row = table.getSelectedRow();
                    //
                    if (row > 0) {
                        selectContact(event, contactsArray.get(row));
                    }
                }
                // normal click
                // @Override
                // public void mousePressed(MouseEvent me) {
                //     selectContact(contactsArray.get(table.getSelectedRow()));
                // }
            });

            contactsListing.setViewportView(table);

            // loading
            progress.setIndeterminate(false);

        }

    }

    public static void selectContact(MouseEvent event, final Map info) {

        JTable source = (JTable) event.getSource();

        JMenuItem itemDetails = new JMenuItem("Details");
        JMenuItem itemBrowser = new JMenuItem("Open on \"{Default Browser}\"");

        final JPopupMenu popupMenu = new JPopupMenu();

        // details buttom
        itemDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // loading
                progress.setIndeterminate(true);

                SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
                    @Override
                    public Void doInBackground() throws IOException {

                        Integer userInfo = Telelistas.getUserID((String) (String) info.get("link"));

                        // loading
                        progress.setIndeterminate(false);

                        // set and show the contact window information
                        MainWindow.setContactInfo(Telelistas.getContactInfo(userInfo));

                        // return anything
                        return null;
                    }
                };

                swingWorker.execute();

            }
        });

        // browser buttom
        // @TODO: Change {Default Browser} for the name of the user default browser
        itemBrowser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println((String) info.get("address"));
                System.out.println((String) info.get("link"));
                System.out.println((String) info.get("name"));
            }
        });

        // setup menu itens
        popupMenu.add(itemDetails);
        popupMenu.add(itemBrowser);

        if (event.isPopupTrigger()) {

            int row = source.rowAtPoint(event.getPoint());
            int column = source.columnAtPoint(event.getPoint());

            if (!source.isRowSelected(row)) {
                source.changeSelection(row, column, false, false);
            }

            popupMenu.show(event.getComponent(), event.getX(), event.getY());
        }

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
        contactinfo.getContentPane().add(panelMaps, "cell 0 3 2 1,grow");

        contactinfo.setTitle("Contact Information");
        contactinfo.revalidate();
        contactinfo.repaint();

        contactinfo.setVisible(true);
    }

}
