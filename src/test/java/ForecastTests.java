import code.HttpUtility;
import code.JsonReader;
import data.ForecastData;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ForecastTests {

    private ForecastData forecastData;
    private JsonReader reader;

    @Before
    public void setUp() throws IOException {
        reader = new JsonReader();
        forecastData = new ForecastData(reader, "Tallinn");
        forecastData.generateThreeDayForecast();
    }

    @Test
    public void checkConnectionCode() throws IOException {
        HttpUtility utility = new HttpUtility();
        assert (utility.getForecastConnectionResponseCode("Tallinn") == 200);
    }

    @Test
    public void checkJsonNotNull() throws IOException {
        String url = "http://api.openweathermap.org/data/2.5/forecast?q=Tallinn&appid=d60283b7466205ccc628d2a40029306c";
        assertNotNull(reader.readJsonFromUrl(url));
    }

    @Test
    public void allThreeDaysMinimumTempNotTooLow() throws IOException {
        assert (forecastData.getFirstDayMinimumTemp() > 243.00);
        assert (forecastData.getSecondDayMinimumTemp() > 243.00);
        assert (forecastData.getThirdDayMinimumTemp() > 243.00);
    }

    @Test
    public void allThreeDaysMaximumTempNotTooHigh() {
        assert (forecastData.getFirstDayMaximumTemp() < 313.00);
        assert (forecastData.getSecondDayMaximumTemp() < 313.00);
        assert (forecastData.getThirdDayMaximumTemp() < 313.00);
    }

    @Test
    public void minTempLessThanMaxTemp() {
        assert (forecastData.getFirstDayMinimumTemp() < forecastData.getFirstDayMaximumTemp());
        assert (forecastData.getSecondDayMinimumTemp() < forecastData.getSecondDayMaximumTemp());
        assert (forecastData.getThirdDayMinimumTemp() < forecastData.getThirdDayMaximumTemp());
    }


}
