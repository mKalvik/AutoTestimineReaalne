package data;


import code.JsonReader;
import org.json.JSONObject;

import java.io.IOException;

public class WeatherData {

    private JsonReader reader;
    private JSONObject data;

    public WeatherData(String cityName, JsonReader reader) throws IOException {
        this.reader = reader;
        data = reader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=d60283b7466205ccc628d2a40029306c");
    }

    public String getCityName() {
        return data.getString("name");
    }

    public void setUrlToReader(String url) {
        reader.setUrl(url);
    }

    public Double getCurrentTemp() {
        return data.getJSONObject("main").getDouble("temp");
    }

    public Double getCurrentWindSpeed() {
        return data.getJSONObject("wind").getDouble("speed");
    }

    public double getCurrentCityLatitude() {
        return data.getJSONObject("coord").getDouble("lat");
    }

    public double getCurrentCityLongitude() {
        return data.getJSONObject("coord").getDouble("lon");
    }

    public JSONObject getJson() {
        return data;
    }

    public String writeToFileForWeather() {
        return String.format("Current city: %s\nCity lon/lat: %.2f/%.2f.\nCurrent temp: %.2f", getCityName(), getCurrentCityLongitude(), getCurrentCityLatitude(), getCurrentTemp());
    }
}
