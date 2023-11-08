package Mataix.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResponseBuilder {
    public String sendGetRequest(double latitude, double longitude, String apikey) throws IOException {
        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast";
        String queryString = "&lat=" + latitude + "&lon=" + longitude + "&appid=" + apikey;

        URL url = new URL(apiUrl + "?" + queryString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            connection.disconnect();

            return response.toString();
        } else {
            throw new IOException("HTTP GET request failed with response code: " + responseCode);
        }
    }
}
