import code.InfoController;
import code.JsonReader;
import data.WeatherData;
import org.junit.Before;
import org.junit.Test;
import txtFile.Controller;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MockTests {

    private JsonReader reader;
    private JsonReader mockedReader;
    private Controller controller;
    private Controller mockedController;
    private WeatherData weatherData;
    private WeatherData mockedWeatherWithReader;
    private String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=Tallinn&appid=d60283b7466205ccc628d2a40029306c";
    private String forecastUrl = "http://api.openweathermap.org/data/2.5/forecast?q=Tallinn&appid=d60283b7466205ccc628d2a40029306c";

    @Before
    public void setUp() throws IOException {
        controller = new Controller();
        reader = new JsonReader();
        mockedReader = mock(JsonReader.class);
        mockedController = mock(Controller.class);
        mockedWeatherWithReader = new WeatherData("Tallinn", mockedReader);


    }

    @Test
    public void apiRequestMockTest() throws IOException {
        mockedWeatherWithReader.setUrlToReader(weatherUrl);
        verify(mockedReader).readJsonFromUrl(weatherUrl);
    }

    @Test
    public void test() throws IOException {
        mockedReader.readJsonFromUrl(forecastUrl);
        verify(mockedReader).readJsonFromUrl(forecastUrl);
    }

    @Test
    public void test2() {

    }
}
