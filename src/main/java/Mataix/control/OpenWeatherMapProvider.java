package Mataix.control;

import Mataix.model.Location;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class OpenWeatherMapProvider {
    private static final String apikey = "https://api.openweathermap.org/data/2.5/forecast?lat=27.99549&lon=-15.41765&appid=fe649f1944e892e7eb8d4a735edd3429";
    public static Location teldeLocation = new Location("Telde", 27.99549, -15.41765);

    public static JsonObject convertResponseToJson(String response) {
        // Utiliza la biblioteca Gson para analizar la respuesta JSON y convertirla en un objeto JsonObject
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
        return jsonObject;
    }

    public static void main(String[] args) throws IOException {
        String response = new ResponseBuilder().sendGetRequest(27.99549, -15.41765, "fe649f1944e892e7eb8d4a735edd3429");
        JsonObject jsonObject = convertResponseToJson(response);
        System.out.println(jsonObject.get("city"));
    }

}