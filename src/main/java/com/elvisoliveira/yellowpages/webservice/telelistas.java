package com.elvisoliveira.yellowpages.webservice;

import com.elvisoliveira.yellowpages.beans.contactbean;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class telelistas {
//
    public static List<contactbean> telelistas(String initName) throws UnsupportedEncodingException, IOException {

        String name = URLEncoder.encode(initName, "ISO-8859-1");

        String url = String.format("http://www.telelistas.net/templates/resultado_busca.aspx?q=&orgm=0&cod_localidade=31000&atividade=&nome=%s&uf_busca=mg&image.x=38&image.y=3", name);

        List<contactbean> contactsList = new ArrayList();

        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();

        if (doc.select("p.tit_erro").hasText()) {
            System.out.println("Não há resultados para este nome.");
        } else {

            Elements contacts = doc.select("div#Content_Regs > table[bgcolor=#f0f0f0]");

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

        }

        return contactsList;
    }

}
