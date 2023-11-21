package Mataix.control;

import Mataix.model.Location;
import Mataix.model.Weather;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.time.Instant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpenWeatherMapProvider {

    private final List<Location> listLocation;

    public OpenWeatherMapProvider(List<Location> locations) {
        this.listLocation = locations;
    }

    public List<Weather> fetchWeatherData() throws IOException, ParseException {
        List<Weather> weatherDataList = new ArrayList<>();
        for (Location location : listLocation) {
            String response = new Response().sendGetRequest(location.getLat(), location.getLon(), "fe649f1944e892e7eb8d4a735edd3429");
            JsonObject jsonObject = convertResponseToJson(response);
            processWeatherData(jsonObject, location, weatherDataList);
        }
        return weatherDataList;
    }

    private JsonObject convertResponseToJson(String response) {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(response).getAsJsonObject();
    }

    private void processWeatherData(JsonObject jsonObject, Location location, List<Weather> weatherDataList) throws ParseException {
        JsonArray list = jsonObject.getAsJsonArray("list");
        for (JsonElement element : list) {
            String dt_txt = element.getAsJsonObject().get("dt_txt").getAsString();
            if (isTargetHour(dt_txt, "12:00:00")) {
                JsonObject jsonObjectMain = element.getAsJsonObject().get("main").getAsJsonObject();
                double temperature = jsonObjectMain.get("temp").getAsDouble();
                double pop = element.getAsJsonObject().get("pop").getAsDouble();
                double humidity = jsonObjectMain.get("humidity").getAsDouble();
                double clouds = element.getAsJsonObject().getAsJsonObject("clouds").get("all").getAsDouble();
                double windSpeed = element.getAsJsonObject().getAsJsonObject("wind").get("speed").getAsDouble();
                Instant instant = Instant.ofEpochSecond(element.getAsJsonObject().get("dt").getAsLong());
                Weather weatherData = new Weather(instant, temperature, pop, humidity, clouds, windSpeed, location);
                weatherDataList.add(weatherData);
            }
        }
    }

    private boolean isTargetHour(String dt_txt, String targetHour) throws ParseException {
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt_txt);
        return hourFormat.format(date).equals(targetHour);
    }
}
