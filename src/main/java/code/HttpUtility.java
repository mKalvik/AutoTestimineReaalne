package code;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtility {
    public int getConnectionResponseCode(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection.getResponseCode();
    }
}
