package com.elvisoliveira.yellowpages;

import com.elvisoliveira.yellowpages.ui.mainWindow;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.JScrollPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class yellowpages {

    private static JScrollPane conatctsListing;
    private static JList listing;

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {

        String name = URLEncoder.encode("Ann", "ISO-8859-1");

        String url = String.format("http://www.telelistas.net/templates/resultado_busca.aspx?q=&orgm=0&cod_localidade=31000&atividade=&nome=%s&uf_busca=mg&image.x=38&image.y=3", name);

        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();

        if (doc.select("p.tit_erro").hasText()) {
            System.out.println("Não há resultados para este nome.");
        } else {

            List<contactbean> contactsList = new ArrayList();

            Elements contacts = doc.select("div#Content_Regs > table");

            for (Element contact : contacts) {

                String contactName = contact.select("td.text_resultado_ib > a").text();
                String contactAddress = contact.select("td.text_endereco_ib").text();
                String contactLink = contact.select("td.text_resultado_ib a").attr("href");

                contactbean foreigner = new contactbean();

                foreigner.setAddress(contactAddress);
                foreigner.setLink(contactLink);
                foreigner.setName(contactName);

                contactsList.add(foreigner);

            }

            mainWindow.setContacts(contactsList);

        }
    }

}
