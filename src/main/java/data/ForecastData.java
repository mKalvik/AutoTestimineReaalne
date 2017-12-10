package data;


import code.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ForecastData {

    private JsonReader reader;
    private JSONObject data;
    private String firstDay;
    private String secondDay;
    private String thirdDay;
    private List<String> datesList = new ArrayList<String>();
    private List<JSONObject> firstDayForecast = new ArrayList<JSONObject>();
    private List<JSONObject> secondDayForecast = new ArrayList<JSONObject>();
    private List<JSONObject> thirdDayForecast = new ArrayList<JSONObject>();
    private JSONArray jsonArray;
    private String cityName;

    public ForecastData(JsonReader reader, String cityName) throws IOException {
        this.reader = reader;
        this.cityName = cityName;
        data = reader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&appid=d60283b7466205ccc628d2a40029306c");
        firstDay = LocalDate.now().toString();
        secondDay = LocalDate.now().plusDays(1).toString();
        thirdDay = LocalDate.now().plusDays(2).toString();
        jsonArray = data.getJSONArray("list");
    }

    private void generateListWithAllDates() {
        for (int index = 0; index < jsonArray.length(); index++) {
            datesList.add(jsonArray.getJSONObject(index).getString("dt_txt"));
        }
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<String> getDatesList() {
        return datesList;
    }

    public String getCityName() {
        return cityName;
    }

    public void generateThreeDayForecast() {
        generateListWithAllDates();
        for (int index = 0; index < datesList.size(); index++) {
            JSONObject k = jsonArray.getJSONObject((index));
            String s = datesList.get(index);
            if (datesList.get(index).contains(firstDay)) {
                firstDayForecast.add(jsonArray.getJSONObject(index));
            }
            if (datesList.get(index).contains(secondDay)) {
                secondDayForecast.add(jsonArray.getJSONObject(index));
            }
            if (datesList.get(index).contains(thirdDay)) {
                thirdDayForecast.add(jsonArray.getJSONObject(index));
            }
        }
    }

    public List<JSONObject> getFirstDayForecast() {
        return firstDayForecast;
    }
    public List<JSONObject> getSecondDayForecast() {
        return secondDayForecast;
    }
    public List<JSONObject> getThirdDayForecast() {
        return thirdDayForecast;
    }


    public double getFirstDayMinimumTemp() {
        return getMinTemperature(firstDayForecast);
    }
    public double getSecondDayMinimumTemp() {
        return getMinTemperature(secondDayForecast);
    }
    public double getThirdDayMinimumTemp() {
        return getMinTemperature(thirdDayForecast);
    }
    public double getFirstDayMaximumTemp() {
        return getMaxTemperature(firstDayForecast);
    }
    public double getSecondDayMaximumTemp() {
        return getMaxTemperature(secondDayForecast);
    }
    public double getThirdDayMaximumTemp() {
        return getMaxTemperature(thirdDayForecast);
    }

    public double getMaxTemperature(List<JSONObject> jsonObjects) {
        double maxValue = -1000.00;

        for (JSONObject o : jsonObjects) {
            double objectTemporary = o.getJSONObject("main").getDouble("temp");
            if (objectTemporary > maxValue) {
                maxValue = objectTemporary;
            }
        }
        return maxValue;
    }
    public double getMinTemperature(List<JSONObject> jsonObjects) {
        double maxValue = 1000.00;

        for (JSONObject o : jsonObjects) {
            double objectTemp = o.getJSONObject("main").getDouble("temp");
            if (objectTemp < maxValue) {
                maxValue = objectTemp;
            }
        }
        return maxValue;
    }


    public String stringToWriteToFile() {
        return String.format(getCityName()+" Kogu esimese päeva minTemp: %.2f, maxTemp: %.2f. Kogu teise päeva minTemp: %.2f, maxTemp: %.2f. Kogu kolmanda päeva minTemp: %.2f, maxTemp: %.2f",
                getFirstDayMinimumTemp(), getFirstDayMaximumTemp(), getSecondDayMinimumTemp(), getSecondDayMaximumTemp(), getThirdDayMinimumTemp(), getThirdDayMaximumTemp());
    }


}







