package com.elvisoliveira.yellowpages;

import com.elvisoliveira.yellowpages.beans.geocode.Geocode;
import com.elvisoliveira.yellowpages.ui.MainWindow;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class YellowPages {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, Exception {

        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            switch (info.getName()) {
                case "Windows":
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                case "GTK+":
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
            }
        }

        // testing the geocode API
        // geocode geocode = new geocode();
        // geocode.geocode("1600 Amphitheatre Parkway, Mountain View, CA");
        // App startup
        MainWindow.setContacts("");
        
        // String json = "{\n"
        //         + "   \"results\" : [\n"
        //         + "      {\n"
        //         + "         \"address_components\" : [\n"
        //         + "            {\n"
        //         + "               \"long_name\" : \"1600\",\n"
        //         + "               \"short_name\" : \"1600\",\n"
        //         + "               \"types\" : [ \"street_number\" ]\n"
        //         + "            },\n"
        //         + "            {\n"
        //         + "               \"long_name\" : \"Amphitheatre Parkway\",\n"
        //         + "               \"short_name\" : \"Amphitheatre Pkwy\",\n"
        //         + "               \"types\" : [ \"route\" ]\n"
        //         + "            },\n"
        //         + "            {\n"
        //         + "               \"long_name\" : \"Mountain View\",\n"
        //         + "               \"short_name\" : \"Mountain View\",\n"
        //         + "               \"types\" : [ \"locality\", \"political\" ]\n"
        //         + "            },\n"
        //         + "            {\n"
        //         + "               \"long_name\" : \"Santa Clara County\",\n"
        //         + "               \"short_name\" : \"Santa Clara County\",\n"
        //         + "               \"types\" : [ \"administrative_area_level_2\", \"political\" ]\n"
        //         + "            },\n"
        //         + "            {\n"
        //         + "               \"long_name\" : \"California\",\n"
        //         + "               \"short_name\" : \"CA\",\n"
        //         + "               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n"
        //         + "            },\n"
        //         + "            {\n"
        //         + "               \"long_name\" : \"United States\",\n"
        //         + "               \"short_name\" : \"US\",\n"
        //         + "               \"types\" : [ \"country\", \"political\" ]\n"
        //         + "            },\n"
        //         + "            {\n"
        //         + "               \"long_name\" : \"94043\",\n"
        //         + "               \"short_name\" : \"94043\",\n"
        //         + "               \"types\" : [ \"postal_code\" ]\n"
        //         + "            }\n"
        //         + "         ],\n"
        //         + "         \"formatted_address\" : \"1600 Amphitheatre Parkway, Mountain View, CA 94043, USA\",\n"
        //         + "         \"geometry\" : {\n"
        //         + "            \"location\" : {\n"
        //         + "               \"lat\" : 37.4219998,\n"
        //         + "               \"lng\" : -122.0839596\n"
        //         + "            },\n"
        //         + "            \"location_type\" : \"ROOFTOP\",\n"
        //         + "            \"viewport\" : {\n"
        //         + "               \"northeast\" : {\n"
        //         + "                  \"lat\" : 37.4233487802915,\n"
        //         + "                  \"lng\" : -122.0826106197085\n"
        //         + "               },\n"
        //         + "               \"southwest\" : {\n"
        //         + "                  \"lat\" : 37.4206508197085,\n"
        //         + "                  \"lng\" : -122.0853085802915\n"
        //         + "               }\n"
        //         + "            }\n"
        //         + "         },\n"
        //         + "         \"types\" : [ \"street_address\" ]\n"
        //         + "      }\n"
        //         + "   ],\n"
        //         + "   \"status\" : \"OK\"\n"
        //         + "}";

        // JsonParser parser = new JsonParser();
        // JsonElement addressComponents = parser.parse(json).getAsJsonObject().get("results").getAsJsonArray();

        // Geocode geo;
        // geo = new Geocode();
        // geo.setStatus(jsonO.get("status").getAsString());
        // geo.setFormatedAddress(jsonO.get("formatted_address").getAsString());

        // System.out.println(jsonO.get("status").getAsString());

    }

}
