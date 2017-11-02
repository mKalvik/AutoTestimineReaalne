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

    public ForecastData(JsonReader reader) throws IOException {
        this.reader = reader;
        data = reader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/forecast?q=Tallinn&appid=d60283b7466205ccc628d2a40029306c");
        firstDay = LocalDate.now().toString();
        secondDay = LocalDate.now().plusDays(1).toString();
        thirdDay = LocalDate.now().plusDays(2).toString();
        jsonArray = data.getJSONArray("list");
//        for(int index = 0; index < jsonArray.length(); index++) {
//            datesList.add(jsonArray.getJSONObject(index).getString("dt_text"));
//        }
//
//        for (String date : datesList) {
//            if (date.contains(firstDay)) {
//                firstDayForecast.add(jsonArray.getJSONObject());
//            }
//        }
//        for (int index = 0; index < datesList.size(); index++) {
//            if (datesList.get(index).contains(firstDay)) {
//                firstDayForecast.add(jsonArray.getJSONObject())
//            }
//        }
    }

    public void generateListWithAllDates() {
        for (int index = 0; index < jsonArray.length(); index++) {
            datesList.add(jsonArray.getJSONObject(index).getString("dt_txt"));
        }
    }

    public List<String> getDatesList() {
        return datesList;
    }

    public void generateThreeDayForecast() {
        for (int index = 0; index < datesList.size(); index++) {
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


    public Double getFirstDayMinimumTemp() {
        return getMinTemperature(firstDayForecast);
    }

    public Double getSecondDayMinimumTemp() {
        return getMinTemperature(secondDayForecast);
    }

    public Double getThirdDayMinimumTemp() {
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

    public Double getMaxTemperature(List<JSONObject> jsonObjects) {
        Double maxValue = Double.MIN_VALUE;

        for (JSONObject o : jsonObjects) {
            JSONObject object = (JSONObject) o.get("main");
            Double objectTemporary = Double.valueOf(object.get("temp").toString());
            if (objectTemporary > maxValue) {
                maxValue = objectTemporary;
            }
        }
        return maxValue;
    }
    public Double getMinTemperature(List<JSONObject> jsonObjects) {
        Double maxValue = Double.MAX_VALUE;

        for (JSONObject o : jsonObjects) {
            JSONObject object = (JSONObject) o.get("main");
            Double objectTemp = Double.valueOf(object.get("temp").toString());
            if (objectTemp < maxValue) {
                maxValue = objectTemp;
            }
        }
        return maxValue;
    }


}







