package Mataix.control;

import Mataix.model.Location;
import Mataix.model.Weather;
import Mataix.control.SQLiteWeatherStore;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

public class OpenWeatherMapProvider {
    private static final String apikey = "https://api.openweathermap.org/data/2.5/forecast?lat=27.99549&lon=-15.41765&appid=fe649f1944e892e7eb8d4a735edd3429";
    public static Location teldeLocation = new Location("Telde", 27.99549, -15.41765);

    public static JsonObject convertResponseToJson(String response) {
        // Utiliza la biblioteca Gson para analizar la respuesta JSON y convertirla en un objeto JsonObject
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
        return jsonObject;
    }

    public static void main(String[] args) throws IOException, ParseException {
        String response = new Response().sendGetRequest(27.99549, -15.41765, "fe649f1944e892e7eb8d4a735edd3429");
        JsonObject jsonObject = convertResponseToJson(response);
        JsonArray list = jsonObject.getAsJsonArray("list");
        List<Weather> weatherDataList = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (JsonElement element : list) {
            String dt_txt = element.getAsJsonObject().get("dt_txt").getAsString();

            // Verificar si la hora coincide con las 12:00:00
            if (isTargetHour(dt_txt, "12:00:00")) {
                JsonObject jsonObjectMain = element.getAsJsonObject().getAsJsonObject("main");
                double temperature = jsonObjectMain.get("temp").getAsDouble();
                double probabilityOfPrecipitation = element.getAsJsonObject().get("pop").getAsDouble();
                double humidity = jsonObjectMain.get("humidity").getAsDouble();
                int cloudiness = element.getAsJsonObject().getAsJsonObject("clouds").get("all").getAsInt();
                double windSpeed = element.getAsJsonObject().getAsJsonObject("wind").get("speed").getAsDouble();

                // Supongo que tienes una instancia de Location llamada "teldeLocation" en tu clase
                Location location = teldeLocation;

                // Utilizamos java.sql.Timestamp en lugar de java.security.Timestamp
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                Weather weatherData = new Weather(timestamp, temperature, probabilityOfPrecipitation, humidity, cloudiness, windSpeed, location);
                weatherDataList.add(weatherData);
                // Después de obtener la lista de datos meteorológicos
                SQLiteWeatherStore.insertWeatherData(weatherDataList);
            }
        }

        // Imprimir o trabajar con la lista como sea necesario
        for (Weather weatherData : weatherDataList) {
            System.out.println("Instant: " + weatherData.getTs());
            System.out.println("Temperatura: " + weatherData.getTemp());
            System.out.println("Probabilidad de precipitación: " + weatherData.getPop());
            System.out.println("Humedad: " + weatherData.getHumidity());
            System.out.println("Nubosidad: " + weatherData.getClouds());
            System.out.println("Velocidad del viento: " + weatherData.getWindSpeed());
            System.out.println("Ubicación: " + weatherData.getLocation());
            System.out.println("------");
        }
    }

    private static boolean isTargetHour(String dt_txt, String targetHour) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse(dt_txt);
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hour = hourFormat.format(date);
        return hour.equals(targetHour);
    }
}