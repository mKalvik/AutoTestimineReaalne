package code;


import com.google.gson.Gson;

import org.json.JSONArray;
//import org.json.simple.parser.JSONParser;
//import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
//import org.json.JSONObject;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonReader {

    private String api = "d60283b7466205ccc628d2a40029306c";
//    private JSONArray weatherForecast;

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        JsonReader reader = new JsonReader();
        JSONObject json = reader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=Tallinn&appid=d60283b7466205ccc628d2a40029306c");
//        JSONObject forecast = reader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/forecast?q=Tallinn&appid=d60283b7466205ccc628d2a40029306c");
//        JSONObject s = (JSONObject) forecast.get("list");

//        System.out.println(s.get("main"));
//        System.out.println(forecast.toString());
//        System.out.println(forecast.get("list"));
//        System.out.println(p);

        JSONObject pask = (JSONObject) json.get("main");
        JSONObject sitt = (JSONObject) json.get("wind");

        System.out.println(json.toString());
        System.out.println(json.get("id"));
        System.out.println(json.get("main"));
        System.out.println(pask.get("temp"));
    }



}