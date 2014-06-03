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

    ArrayList<Map> address_components;
    ArrayList<ArrayList> address;
    GeocodeBean addressinfo;

    public void geocode(String location) throws UnsupportedEncodingException, Exception {
        // sanitize the location params
        String geocodeUrl = URLEncoder.encode(location, "ISO-8859-1");
        // set the google geocode API request URL
        String unencodedUrl = "http://maps.googleapis.com/maps/api/geocode/json?address=" + geocodeUrl + "&sensor=true";
        // 
        String results = getJson(unencodedUrl);

        parseJson(results);
    }

    private void parseJson(String json) throws ParseException {
        // load the javaBean
        addressinfo = new GeocodeBean();
        // ready the parser object
        JSONParser parser = new JSONParser();
        // supply the json to be parsed
        JSONObject jsonObject = (JSONObject) parser.parse(json);
        // status
        addressinfo.setStatus((String) jsonObject.get("status"));

        // results: address_components
        address_components = new ArrayList<>();

        for (Object results : (JSONArray) jsonObject.get("results")) {

            JSONObject address_components_iterator = (JSONObject) results;

            address = new ArrayList<>();

            for (Object address_components_index : (JSONArray) address_components_iterator.get("address_components")) {

                JSONObject address_components_object = (JSONObject) address_components_index;

                JSONArray typesArray = (JSONArray) address_components_object.get("types");

                Map<String, String> map = new HashMap<>();

                map.put("long_name", (String) address_components_object.get("long_name"));
                map.put("short_name", (String) address_components_object.get("short_name"));
                map.put("types", (String) typesArray.get(0));

                address_components.add(map);
            }

            address.add(address_components);
        }
        addressinfo.setResults_address_components(address);

        // address_componentss
        // long_name
        // short_name
        // types
        // get the first item
        // formatted_address
        // geometry
        // location
        // lat
        // lng
        // location_type
        // viewport
        // northeast
        // lat
        // lng
        // southwest
        // lat
        // lng
        // types
    }

    private String getJson(String urlParam) throws Exception {

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

        } finally {
            // if the reader is set, the connection was made
            if (reader != null) {
                // close the connection
                reader.close();
            }
        }

        return stringBuilder.toString();

    }

}
