package com.elvisoliveira.yellowpages.ui;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import com.elvisoliveira.yellowpages.beans.LocationBean;
import com.elvisoliveira.yellowpages.webservice.Telelistas;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import org.jsoup.nodes.Document;

public class MainWindow {

    private static JScrollPane contactsListing;
    private static JTable table;
    private static DefaultTableModel contactsTable;
    private static JTextField searchInput;
    private static JButton searchButton;
    private static JButton phonesButton;
    private static JButton digitsButton;
    private static JComboBox statesList;
    private static JComboBox<LocationBean> ctList;
    private static JComboBox<LocationBean> nbList;
    private static JProgressBar progress;
    private static List<ContactBean> contactsList;
    private static ActionListener ctListener;
    private static ActionListener nbListener;
    private static ArrayList<LocationBean> neighbourhood;
    private static ArrayList<LocationBean> cities;

    private static final JFrame window = new JFrame("YellowPages");
    private static final JPanel panel = new JPanel();

    public static void MainWindow(String name) throws IOException {

        // serach field
        searchInput = new JTextField();
        searchInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.searchButton();
            }
        });

        Dimension size = new Dimension();
        size.height = 25;

        // search button
        searchButton = new JButton();
        searchButton.setText("Search");
        searchButton.setPreferredSize(size);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.searchButton();
            }
        });

        // Get phone numbers.
        phonesButton = new JButton();
        phonesButton.setText("Get Phones");
        phonesButton.setPreferredSize(size);
        phonesButton.setEnabled(false);
        phonesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                phonesButton.setEnabled(false);
                MainWindow.phonesButton();
            }
        });

        // States.
        String[] states = {
            "BR", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO",
            "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ",
            "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"
        };
        statesList = new JComboBox(states);
        statesList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String state = (String) statesList.getSelectedItem();
                if (!"BR".equals(state)) {
                    progress.setIndeterminate(true);
                    ctList.setEnabled(false);
                    nbList.setEnabled(false);
                    SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
                        @Override
                        public Void doInBackground() throws IOException {
                            cities = Telelistas.getCities(state);
                            MainWindow.setCity(true, cities);
                            progress.setIndeterminate(false);
                            ctList.setEnabled(true);
                            return null;
                        }
                    };
                    swingWorker.execute();
                } else {
                    MainWindow.setCity(false, null);
                }
            }
        });

        // Cities.
        ctList = new JComboBox();
        ctListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String city = (String) ctList.getSelectedItem().toString();
                final String state = (String) statesList.getSelectedItem().toString();
                if (!"Select the City".equals(city)) {
                    progress.setIndeterminate(true);
                    SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
                        @Override
                        public Void doInBackground() throws IOException {
                            for (LocationBean cityBean : cities) {
                                if (city.equals(cityBean.getLocationName())) {
                                    neighbourhood = Telelistas.getNeighbourhood(state, cityBean.getLocationID().toString());
                                }
                            }
                            MainWindow.setNeighbourhood(true, neighbourhood);
                            progress.setIndeterminate(false);
                            return null;
                        }
                    };
                    swingWorker.execute();
                } else {
                    MainWindow.setNeighbourhood(false, null);
                }
            }
        };
        MainWindow.setCity(false, null);

        // Neighbourhood.
        nbList = new JComboBox<>();
        nbListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(ctList.getSelectedItem());
            }
        };
        MainWindow.setNeighbourhood(false, null);

        // Get last two digits.
        digitsButton = new JButton();
        digitsButton.setText("X");
        digitsButton.setEnabled(false);
        digitsButton.setPreferredSize(size);

        // in the beginning of the execution
        // the name is not set, give the user instructions to search
        // set the widget dimentions, required to define layout properties
        // instantiate the widget that will list the contacts
        contactsListing = new JScrollPane();
        contactsListing.setViewportView(new JLabel("Search a contact", JLabel.CENTER));
        contactsListing.setPreferredSize(new Dimension(400, 400));

        // progress bar
        progress = new JProgressBar();
        progress.setIndeterminate(false);

        // layout configuration        
        panel.setLayout(new MigLayout(""));
        panel.add(searchInput, "grow, split 7");
        panel.add(statesList, "growy");
        panel.add(ctList, "growy");
        panel.add(nbList, "growy, wrap");
        panel.add(searchButton, "grow, split 3");
        panel.add(phonesButton, "grow");
        panel.add(digitsButton, "wrap");
        panel.add(contactsListing, "wrap");
        panel.add(progress, "growx, wrap");

        // window configuration
        window.add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.pack();
    }

    private static void setCity(Boolean enabled, ArrayList cities) {
        String placeholder = "Select the City";
        if (ctList != null) {
            if (enabled) {
                ctList.removeAllItems();
                for (int i = 0; i < cities.size(); i++) {
                    ctList.addItem((LocationBean) cities.get(i));
                }
                ctList.addActionListener(ctListener);
            } else {
                ctList.addItem(Telelistas.setLocation(0, placeholder));
                ctList.removeActionListener(ctListener);
            }
            ctList.setPrototypeDisplayValue(Telelistas.setLocation(0, placeholder));
            ctList.setEnabled(enabled);
        }
    }

    private static void setNeighbourhood(Boolean enabled, ArrayList neighbourhoods) {
        String placeholder = "Select the Neighbourhood";
        if (nbList != null) {
            nbList.setPrototypeDisplayValue(Telelistas.setLocation(0, placeholder));
            if (enabled) {
                nbList.removeAllItems();
                if (neighbourhoods.size() > 1) {
                    for (int i = 0; i < neighbourhoods.size(); i++) {
                        nbList.addItem((LocationBean) neighbourhoods.get(i));
                    }
                } else {
                    nbList.addItem(Telelistas.setLocation(0, "Neighbourhood not found."));
                }
                nbList.addActionListener(nbListener);
            } else {
                nbList.removeActionListener(nbListener);
                nbList.addItem(Telelistas.setLocation(0, placeholder));
            }
            nbList.setPrototypeDisplayValue(Telelistas.setLocation(0, placeholder));
            nbList.setEnabled(enabled);
        }
    }

    public static void phonesButton() {

        // Add Column
        List<String> columns = new ArrayList<>();

        for (int i = 0; i < contactsTable.getColumnCount(); i++) {
            columns.add(contactsTable.getColumnName(i));
        }

        String[] newColumns = {
            "Telephone", "Final"
        };

        for (String newColumnsName : newColumns) {
            if (!columns.contains(newColumnsName)) {
                contactsTable.addColumn(newColumnsName);
            }
        }

        // List Items
        int i = 0;
        for (ContactBean contact : contactsList) {

            ContactBean contactInfo = Telelistas.getContactInfo(contact.getLink());
            contactsTable.setValueAt(contactInfo.getTelephone(), i, 2);
            contactsTable.setValueAt(contactInfo.getFinal(), i, 3);
            i++;
        }

        // Hide column Final.
        table.getColumnModel().getColumn(3).setMinWidth(0);
        table.getColumnModel().getColumn(3).setMaxWidth(0);
        table.getColumnModel().getColumn(3).setWidth(0);
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

    private static void changeContacts(String name) throws IOException {

        String city = ctList != null ? (String) ctList.getSelectedItem().toString() : null;
        String neighborhood = nbList != null ? (String) nbList.getSelectedItem().toString() : null;

        Integer citySubset = null;
        Integer neighborhoodSubset = null;

        for (int i = 0; i < neighbourhood.size(); i++) {
            LocationBean nbLocation = (LocationBean) neighbourhood.get(i);
            if (nbLocation.getLocationName().equals(neighborhood)) {
                citySubset = nbLocation.getLocationID();
            }
        }

        for (int i = 0; i < cities.size(); i++) {
            LocationBean nbLocation = (LocationBean) cities.get(i);
            if (nbLocation.getLocationName().equals(city)) {
                neighborhoodSubset = nbLocation.getLocationID();
            }
        }

        // make the request and return the Document
        Document document = Telelistas.generateDocument(name, (String) statesList.getSelectedItem(), citySubset.toString(), neighborhoodSubset.toString());

        // from the requested Document, verify how many contacts were returned
        Integer total = Telelistas.totalContacts(document);

        // if the total of returned contacts are zero, feedback one messange
        if (total.equals(0)) {
            phonesButton.setEnabled(false);

            contactsListing.setViewportView(new JLabel("no contacts with this name", JLabel.CENTER));
        } // if the total of returned contacts are greater than zero, return them
        else {
            phonesButton.setEnabled(true);

            contactsTable = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 2;
                }
            };
            contactsTable.addColumn("Name");
            contactsTable.addColumn("Address");

            contactsList = Telelistas.telelistas(document);

            for (ContactBean object : contactsList) {
                contactsTable.addRow(new Object[]{
                    object.getName(), object.getAddress().trim()
                });
            }

            table = new JTable();
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setModel(contactsTable);
            table.addMouseListener(new MouseAdapter() {
                // right click
                @Override
                public void mouseReleased(MouseEvent event) {
                    if (table.getSelectedRow() >= 0) {
                        MainWindow.selectContact(event, contactsList.get(table.getSelectedRow()));
                    }
                }
            });
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (table.getColumnCount() >= 3) {
                        try {
                            String digits = table.getValueAt(table.getSelectedRow(), 3).toString();
                            Image img = ImageIO.read(new File(digits));
                            digitsButton.setText("");
                            digitsButton.setIcon(new ImageIcon(img));
                        } catch (IOException ex) {
                            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            });

            contactsListing.setViewportView(table);

        }

        // loading
        progress.setIndeterminate(false);

    }

    public static Integer changeContacts(Boolean iterator) {

        Integer operation = (iterator) ? 1 : -1;

        Integer selected = table.getSelectedRow() + operation;

        if (selected < 0 || selected == table.getRowCount()) {
            selected = table.getSelectedRow();
        }

        table.setRowSelectionInterval(selected, selected);

        return selected;

    }

    public static void selectContact(MouseEvent event, final ContactBean info) {

        JTable source = (JTable) event.getSource();

        JMenuItem itemDetails = new JMenuItem("Details");
        JMenuItem itemBrowser = new JMenuItem("Open on " + getDefaultBrowser());

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
                        // loading
                        progress.setIndeterminate(false);

                        // set and show the contact window information
                        DetailsWindow contact = new DetailsWindow(window);

                        contact.setContactInfo(Telelistas.getContactInfo(info.getLink()), contactsList);

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
                try {
                    Desktop.getDesktop().browse(new URI((String) info.getLink()));
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    public static String getDefaultBrowser() {
        try {
            // Get registry where we find the default browser
            Process process = Runtime.getRuntime().exec("REG QUERY HKEY_CLASSES_ROOT\\http\\shell\\open\\command");
            try (Scanner kb = new Scanner(process.getInputStream())) {
                while (kb.hasNextLine()) {
                    // Get output from the terminal, and replace all '\' with '/' (makes regex a bit more manageable)
                    String registry = (kb.nextLine()).replaceAll("\\\\", "/").trim();

                    // Extract the default browser
                    Matcher matcher = Pattern.compile("/(?=[^/]*$)(.+?)[.]").matcher(registry);
                    if (matcher.find()) {
                        // Scanner is no longer needed if match is found, so close it
                        kb.close();
                        String defaultBrowser = matcher.group(1);

                        // Capitalize first letter and return String
                        defaultBrowser = defaultBrowser.substring(0, 1).toUpperCase() + defaultBrowser.substring(1, defaultBrowser.length());
                        return defaultBrowser;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // Have to return something if everything fails
        return "Browser";
    }

}
