package com.elvisoliveira.yellowpages.webservice;

import com.elvisoliveira.yellowpages.beans.ContactBean;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Telelistas
{

    private static String[] tokens;

    public static Document generateDocument(String initName) throws UnsupportedEncodingException, IOException
    {

        String name = URLEncoder.encode(initName, "ISO-8859-1");

        String url = String.format("http://www.telelistas.net/templates/resultado_busca.aspx?q=&orgm=0&cod_localidade=31000&atividade=&nome=%s&uf_busca=mg&image.x=38&image.y=3", name);

        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();

        return doc;
    }

    public static Integer totalContacts(Document doc)
    {

        Elements contacts = doc.select("div#Content_Regs > table");

        return contacts.size();

    }

    public static Integer getUserID(String url)
    {
        // get the tokens retuned of string
        tokens = url.split("/");

        for (String string : tokens)
        {
            // find ont token that has only numbers
            if (string.matches("[-+]?\\d*\\.?\\d+"))
            {
                // define as contact ID (of telelistas)
                return Integer.parseInt(string);
            }
        }
        return null;
    }

    public static ContactBean getContactInfo(Integer id)
    {

        String url = "http://www.telelistas.net/templates/v_impressao_vcard.aspx?id=" + id.toString();

        try
        {
            Document doc = Jsoup.connect(url).userAgent("Mozilla").get();

            String address = doc.select("p.infoplus_text1").text();
            String telephone = doc.select("p.infoplus_text2").text();

            doc.select("td.nome_anun span").empty();

            String name = doc.select("td.nome_anun").text();

            ContactBean contact = new ContactBean();
            contact.setAddress(address);
            contact.setLink(url);
            contact.setName(name);
            contact.setTelephone(telephone);
            contact.setId(id.toString());

            return contact;

        }
        catch (IOException ex)
        {
            Logger.getLogger(Telelistas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public static List<ContactBean> telelistas(Document doc)
    {

        List<ContactBean> contactsList = new ArrayList();

        Elements contacts = doc.select("div#Content_Regs > table");

        for (Element contact : contacts)
        {
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
