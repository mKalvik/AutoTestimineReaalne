package main;


import com.google.gson.Gson;
import main.json.WeatherAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;


public class Voidmain {

    private static OkHttpClient client = new OkHttpClient();

    public String getJSON(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public WeatherAPI getData(String cityName) {
        String json = null;
        try {
            json = getJSON("http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid=d60283b7466205ccc628d2a40029306c");
        }catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson= new Gson();

//        WeatherAPI weatherAPI = gson.fromJson(json, WeatherAPI.class);
//mddsa
        return gson.fromJson(json, WeatherAPI.class);
//        return weatherAPI.getWind().toString();
    }

}
