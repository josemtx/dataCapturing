package Mataix.control;

import Mataix.model.Location;
import Mataix.model.Weather;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class WeatherController {
    public static void main(String[] args) {
        // Lista de ubicaciones para las cuales deseas obtener datos meteorológicos
        List<Location> locations = Arrays.asList(
                new Location("Gran Canaria", 27.99549, -15.41765),
                new Location("Tenerife", 28.463850790803008, -16.25097353346818),
                new Location("Fuerteventura", 28.50047229032077, -16.25097353346818),
                new Location("Lanzarote",28.965080860301025, -13.556148106209083),
                new Location("La Graciosa", 29.23141101200906, -13.503131221117982),
                new Location("El Hierro", 27.809920552606453, -17.91474223115781),
                new Location("La Gomera", 28.094369991798228, -17.109467831251514),
                new Location("La Palma", 28.684160726614596, -17.76582062032028)
        );
        for (Location location : locations) {
            CreateDataBase.create(location.getIsla());
        }

        // Instancia de OpenWeatherMapProvider con la lista de ubicaciones
        OpenWeatherMapProvider weatherProvider = new OpenWeatherMapProvider(locations);

        try {
            // Obtener los datos meteorológicos
            List<Weather> weatherDataList = weatherProvider.fetchWeatherData();

            // Aquí puedes llamar a otra clase o método para insertar los datos en la base de datos
            SQLiteWeatherStore.insertWeatherData(weatherDataList);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }
}
