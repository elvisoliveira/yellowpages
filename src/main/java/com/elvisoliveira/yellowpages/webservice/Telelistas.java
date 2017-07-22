package com.elvisoliveira.yellowpages.webservice;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import com.elvisoliveira.yellowpages.beans.LocationBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Telelistas {

    private static String[] tokens;

    public static Document generateDocument(String initName) throws UnsupportedEncodingException, IOException {

        String name = URLEncoder.encode(initName, "ISO-8859-1");

        String url = String.format("http://www.telelistas.net/templates/resultado_busca.aspx?q=&orgm=0&cod_localidade=31000&atividade=&nome=%s&uf_busca=mg&image.x=38&image.y=3", name);

        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();

        return doc;
    }

    public static Integer totalContacts(Document doc) {

        Elements contacts = doc.select("div#Content_Regs > table");

        return contacts.size();

    }

    public static ArrayList<LocationBean> getCities(String state) {
        ArrayList<LocationBean> cities = new ArrayList<>();
        try {
            cities.add(Telelistas.setLocation(0, "Select the City"));
            Document doc = Jsoup.connect("https://www.telelistas.net/AjaxHandler.ashx")
                    .data("state", state.toLowerCase())
                    .data("style", "busca_interna")
                    .data("selectedCity", "")
                    .data("clientId", "pch_localidade_select")
                    .data("method", "GetSearchCitiesNamed")
                    .userAgent("Mozilla")
                    .post();

            for (Element e : doc.select("option")) {
                if (!e.attr("value").isEmpty()) {
                    cities.add(Telelistas.setLocation(Integer.parseInt(e.attr("value")), e.text()));
                }
            }
        } catch (IOException ex) {
            cities.add(Telelistas.setLocation(0, "Could not retrieve data: " + ex.getMessage()));
        }
        return cities;
    }

    public static LocationBean setLocation(Integer id, String name) {
        LocationBean mockLocation = new LocationBean();
        mockLocation.setLocationID(id);
        mockLocation.setLocationName(name);

        return mockLocation;
    }

    public static Integer getUserID(String url) {
        // get the tokens retuned of string
        tokens = url.split("/");

        for (String string : tokens) {
            // find ont token that has only numbers
            if (string.matches("[-+]?\\d*\\.?\\d+")) {
                // define as contact ID (of telelistas)
                return Integer.parseInt(string);
            }
        }
        return null;
    }

    public static ContactBean getContactInfo(String link) {

        try {

            Document doc = Jsoup.connect(link).userAgent("Mozilla").get();

            String name = doc.select("h1.nome_anun").text();

            Elements phoneDOM = doc.select("div#anunciante div:nth-child(1)");
            String phone = phoneDOM.text().replaceAll("Tel: ", "");

            String filePath = Telelistas.class.getResource(".").getFile() + phone.replaceAll("([^a-zA-Z0-9]|\\s)+", "") + ":" + name.replaceAll("([^a-zA-Z]|\\s)+", "") + ".gif";
            for (Element e : phoneDOM.select("img")) {
                if (e.attr("src").toLowerCase().contains("imgfactory")) {
                    Response resultImageResponse = Jsoup.connect(e.attr("src")).userAgent("Mozilla").ignoreContentType(true).execute();
                    try (FileOutputStream out = new FileOutputStream(new File(filePath))) {
                        out.write(resultImageResponse.bodyAsBytes());
                    }
                }
            }

            phoneDOM.remove();

            String address = doc.select("div#anunciante div:nth-child(1)").text();

            ContactBean contact = new ContactBean();
            contact.setAddress(address);
            contact.setLink(link);
            contact.setName(name);
            contact.setFinal(filePath);
            contact.setTelephone(phone);

            return contact;

        } catch (IOException ex) {
            Logger.getLogger(Telelistas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public static List<ContactBean> telelistas(Document doc) {

        List<ContactBean> contactsList = new ArrayList();

        Elements contacts = doc.select("div#Content_Regs > table:not([bgcolor])");

        for (Element contact : contacts) {
            // get information string
            String contactName = contact.select("td.text_resultado_ib > a").text();
            String contactAddress = contact.select("td.text_endereco_ib").text();
            String contactLink = contact.select("td.text_resultado_ib a").attr("href");

            // 
            ContactBean foreigner = new ContactBean();

            // 
            foreigner.setAddress(contactAddress);
            foreigner.setLink(contactLink);
            foreigner.setName(contactName);

            // set google information
            contactsList.add(foreigner);

        }

        return contactsList;
    }

}
