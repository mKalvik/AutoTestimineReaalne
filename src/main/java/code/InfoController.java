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


    public void whatToWrite() throws IOException {

        for (String cityName : controller.getCityNamesList()) {
            String stringToPrint = "";
            forecastData = new ForecastData(reader, cityName);
            weatherData = new WeatherData(cityName);
            forecastData.generateThreeDayForecast();
            stringToPrint += weatherData.writeToFileForWeather() + " " + forecastData.stringToWriteToFile();
            controller.printToOutputFile(stringToPrint, cityName + ".txt");
            System.out.println("peaks kirjutatud olema");
        }
    }
}
