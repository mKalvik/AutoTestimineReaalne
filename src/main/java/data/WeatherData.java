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
        return data.getString("name");
    }

    public Double getCurrentTemp() {
        return data.getJSONObject("main").getDouble("temp");
    }

    public Double getCurrentWindSpeed() {
        return data.getJSONObject("main").getDouble("speed");
    }

    public String writeToFileForWeather() {
        return String.format("Current weather temp: %.2f", getCurrentTemp());
    }
}
