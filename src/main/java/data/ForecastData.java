package data;


import code.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ForecastData {

    private JsonReader reader;
    private JSONObject data;
    private String firstDay;
    private String secondDay;
    private String thirdDay;
    private List<String> datesList;

    public ForecastData() throws IOException {
        reader = new JsonReader();
        data = reader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/forecast?q=Tallinn&appid=d60283b7466205ccc628d2a40029306c");
        firstDay = LocalDate.now().toString();
        secondDay = LocalDate.now().plusDays(1).toString();
        thirdDay = LocalDate.now().plusDays(2).toString();
        JSONArray jsonArray = data.getJSONArray("list");
        for(int index = 0; index < jsonArray.length(); index++) {
            datesList.add(jsonArray.getJSONObject(index).getString("dt_text"));
        }
    }


    public List<String> threeDaysList () {

    }

}
