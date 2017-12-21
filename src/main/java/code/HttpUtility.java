package code;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtility {
    public int getForecastConnectionResponseCode(String cityName) throws IOException {
        String requestUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=d60283b7466205ccc628d2a40029306c";
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection.getResponseCode();
    }

    public int getWeatherConnectionResponseCode(String cityName) throws IOException {
        String requestUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=d60283b7466205ccc628d2a40029306c";
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection.getResponseCode();
    }
}
