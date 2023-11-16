package Mataix.control;

import Mataix.model.Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLiteWeatherStore {
    private static final String url = "jdbc:sqlite:src/main/resources/database.db";

    public static void insertWeatherData(List<Weather> weatherDataList) {
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                System.out.println("Successful connection");

                for (Weather weatherData : weatherDataList) {
                    insertWeatherData(connection, weatherData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertWeatherData(Connection connection, Weather weatherData) {
        String locationName = weatherData.getLocation().getIsland().replace(" ", "");
        String insertDataQuery = String.format(
                "INSERT INTO %sWeather (timestamp, temperature, pop, humidity, clouds, windSpeed) " +
                        "VALUES (?, ?, ?, ?, ?, ?)", locationName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
            preparedStatement.setString(1, weatherData.getTs().toString());
            preparedStatement.setDouble(2, weatherData.getTemp());
            preparedStatement.setDouble(3, weatherData.getPop());
            preparedStatement.setDouble(4, weatherData.getHumidity());
            preparedStatement.setDouble(5, weatherData.getClouds());
            preparedStatement.setDouble(6, weatherData.getWindSpeed());

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully on " + locationName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
