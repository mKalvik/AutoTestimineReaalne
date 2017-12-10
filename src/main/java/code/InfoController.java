package code;


import data.ForecastData;
import data.WeatherData;
import txtFile.Controller;

import java.io.IOException;

public class InfoController {

    private JsonReader reader;
    private WeatherData weatherData;
    private ForecastData forecastData;
    private Controller controller;

    public InfoController(JsonReader reader, WeatherData weatherData, ForecastData forecastData, Controller controller) {
        this.reader = reader;
        this.weatherData = weatherData;
        this.forecastData = forecastData;
        this.controller = controller;
    }


    public String whatToWrite() throws IOException {
        String stringToPrint = "";
        for (String cityName : controller.getCityNamesList()) {
            forecastData = new ForecastData(reader, cityName);
            weatherData = new WeatherData(cityName);
            forecastData.generateThreeDayForecast();
            stringToPrint += forecastData.stringToWriteToFile() + " " + weatherData.writeToFileForWeather() + "\n";
        }
        return stringToPrint;
    }
}
