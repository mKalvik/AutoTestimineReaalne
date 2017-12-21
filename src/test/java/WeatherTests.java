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
    private JsonReader reader;
    private WeatherData weatherdata;


    @Before
    public void setUp() throws IOException {
        reader = new JsonReader();
        weatherdata = new WeatherData("Tallinn", reader);
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
    public void currentTempNotTooHigh() {
        assert (weatherdata.getCurrentTemp() < 310);
    }

    @Test
    public void currentTempNotTooLow() {
        assert (weatherdata.getCurrentTemp() > 230);
    }

    @Test
    public void cityHasCorrectLon() {
        assert (weatherdata.getCurrentCityLongitude() == 24.75);
    }

    @Test
    public void cityHasCorrectLat() {
        assert (weatherdata.getCurrentCityLatitude() == 59.44);
    }

}

