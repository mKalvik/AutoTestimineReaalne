import code.JsonReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.WeatherData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class WeatherTests {
    private JsonReader data;
    private WeatherData weatherdata;


    @Before
    public void setUp() throws IOException {
        data = new JsonReader();
        weatherdata = new WeatherData("Tallinn");
    }

    @Test
    public void currentWindSpeedTest() {
        assert (weatherdata.getCurrentWindSpeed() < 33);
    }

    @Test
    public void weatherUsesTallinnTest() {
        assert (weatherdata.getCityName().equals("Tallinn"));
    }

    @Test
    public void currentTemperatureTest() {
//        assert (Double.valueOf(weatherdata.get("temp")) < 310);
        assert (weatherdata.getCurrentTemp() < 310);
    }

}

