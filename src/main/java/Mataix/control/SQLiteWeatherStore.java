package Mataix.control;

import Mataix.model.Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLiteWeatherStore {
    private static final String url = "jdbc:sqlite:C:\\Users\\josem\\IdeaProjects\\dataCapturing\\src\\main\\resources\\database.db";

    public static void insertWeatherData(List<Weather> weatherDataList) {
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                System.out.println("Conexión exitosa");

                for (Weather weatherData : weatherDataList) {
                    insertWeatherData(connection, weatherData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertWeatherData(Connection connection, Weather weatherData) {
        String insertDataQuery = "INSERT INTO TeldeWeather (timestamp, temperature, pop, humidity, clouds, windSpeed) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery)) {
            preparedStatement.setTimestamp(1, weatherData.getTs());
            preparedStatement.setDouble(2, weatherData.getTemp());
            preparedStatement.setDouble(3, weatherData.getPop());
            preparedStatement.setDouble(4, weatherData.getHumidity());
            preparedStatement.setInt(5, (int) weatherData.getClouds());
            preparedStatement.setDouble(6, weatherData.getWindSpeed());

            preparedStatement.executeUpdate();
            System.out.println("Datos insertados exitosamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
