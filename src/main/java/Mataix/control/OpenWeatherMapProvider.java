package Mataix.control;

import Mataix.model.Location;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenWeatherMapProvider {
    private static final String apikey = "https://api.openweathermap.org/data/2.5/weather?lat=#&lon=#&appid=fe649f1944e892e7eb8d4a735edd3429";
    public static Location teldeLocation = new Location("Telde", 27.99549, -15.41765);

    public static JsonObject convertResponseToJson(String response) throws InstantiationException, IllegalAccessException {
        // Utiliza la biblioteca Gson para analizar la respuesta JSON y convertirla en un objeto JsonObject
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();

        return jsonObject;
    }
    @Test
    public void testConvertResponseToJson() throws InstantiationException, IllegalAccessException {
        String response = "{\"key1\": \"value1\", \"key2\": 42}";
        JsonObject jsonObject = OpenWeatherMapProvider.convertResponseToJson(response);

        // Verifica que el objeto JSON se haya creado correctamente
        assertEquals("value1", jsonObject.get("key1").getAsString());
        assertEquals(42, jsonObject.get("key2").getAsInt());
    }

}