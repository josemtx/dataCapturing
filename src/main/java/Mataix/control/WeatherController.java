package Mataix.control;

import Mataix.model.Location;
import Mataix.model.Weather;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherController {
    public static void execute() {
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
            CreateDataBase.create(location.getIsland());
        }
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                fetchAndStoreWeatherData(locations);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }, 0, 6, TimeUnit.HOURS);
    }

    private static void fetchAndStoreWeatherData(List<Location> locations) throws IOException, ParseException {
        OpenWeatherMapProvider weatherProvider = new OpenWeatherMapProvider(locations);
        List<Weather> weatherDataList = weatherProvider.fetchWeatherData();
        SQLiteWeatherStore.insertWeatherData(weatherDataList);
    }
}
