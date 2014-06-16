package com.elvisoliveira.yellowpages.webservice;

import com.elvisoliveira.yellowpages.beans.GeocodeBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GeoCode {

    private static ArrayList<Map> address_components;
    private static ArrayList<ArrayList> address;
    private static GeocodeBean addressinfo;

    public static GeocodeBean geocode(String location) throws UnsupportedEncodingException, Exception {
        // sanitize the location params
        String geocodeUrl = URLEncoder.encode(location, "ISO-8859-1");
        // set the google geocode API request URL
        String unencodedUrl = "http://maps.googleapis.com/maps/api/geocode/json?address=" + geocodeUrl + "&sensor=true";
        // 

        String results = getJson(unencodedUrl);

        GeocodeBean geocode = parseJson(results);

        return geocode;

    }

    private static GeocodeBean parseJson(String json) throws ParseException {
        
        System.out.println(json);
        
        return null;
    }

    private static String getJson(String urlParam) throws Exception {

        URL url;
        BufferedReader reader = null;
        StringBuilder stringBuilder;
        String line;

        try {
            // create the HttpURLConnection
            url = new URL(urlParam);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");

            // give it 15 seconds to respond
            connection.setReadTimeout(15 * 1000);

            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

        }
        finally {
            // if the reader is set, the connection was made
            if (reader != null) {
                // close the connection
                reader.close();
            }
        }

        return stringBuilder.toString();

    }

}
