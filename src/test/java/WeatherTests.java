import com.google.gson.Gson;
import main.Voidmain;
import main.json.WeatherAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class WeatherTests {
    private Voidmain data = new Voidmain();


    @Test
    public void mingipaskTest() {
        assert (Double.valueOf(data.getData("Tallinn").getWind().toString()) < 33);
    }


}
