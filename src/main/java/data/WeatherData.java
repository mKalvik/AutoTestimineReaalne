package data;


import code.JsonReader;
import org.json.JSONObject;

import java.io.IOException;

public class WeatherData {

    private JsonReader reader;
    private JSONObject data;

    public WeatherData() throws IOException {
        reader = new JsonReader();
        data = reader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=Tallinn&appid=d60283b7466205ccc628d2a40029306c");
    }

    public String getCityName() {
        return data.get("name").toString();
    }

    public Double getCurrentTemp() {
        JSONObject mainData = (JSONObject) data.get("main");
        return Double.valueOf(mainData.get("temp").toString());
    }

    public Double getCurrentWindSpeed() {
        JSONObject windData = (JSONObject) data.get("wind");
        return Double.valueOf(windData.get("speed").toString());
    }
}
