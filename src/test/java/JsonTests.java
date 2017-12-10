import code.HttpUtility;
import code.JsonReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class JsonTests {

    private JsonReader reader;
    private String url;


    @Before
    public void setUp() {
        reader = new JsonReader();
        url = "http://api.openweathermap.org/data/2.5/weather?q=Tallinn&appid=d60283b7466205ccc628d2a40029306c";

    }

    @Test
    public void checkConnectionCode() throws IOException {
        HttpUtility utility = new HttpUtility();
        assert (utility.getWeatherConnectionResponseCode("Tallinn") == 200);
    }

    @Test
    public void checkIfGotJson() throws IOException {
        assertNotNull(reader.readJsonFromUrl(url));
    }
}
