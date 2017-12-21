package code;


import com.google.gson.Gson;

import data.ForecastData;
import data.WeatherData;
import org.json.JSONArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.JsonParser;
import txtFile.Controller;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JsonReader {

    private String url;

    private String api = "d60283b7466205ccc628d2a40029306c";
//    private JSONArray weatherForecast;

    public void setUrl(String url) {
        this.url = url;
    }

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
        WeatherData weatherData = new WeatherData("Tallinn", reader);
        ForecastData forecastData = new ForecastData(reader, "Tallinn");
        Controller controller = new Controller();
        InfoController infoController = new InfoController(reader, weatherData, forecastData, controller);
        infoController.whatToWrite();

    }


}