package Mataix.control;

import Mataix.model.Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteWeatherStore {
    private static final String DATABASE_URL = "jdbc:sqlite:weather_data.db";

    public void saveWeatherData(Weather weatherData) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            createTableIfNotExists(connection, weatherData.getLocations().getName());

            String insertQuery = "INSERT INTO " + weatherData.getLocations().getName() + " (timestamp, temperature, precipitation, humidity, clouds, wind_speed) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setTimestamp(1, weatherData.getTs());
                preparedStatement.setDouble(2, weatherData.getTemp());
                preparedStatement.setDouble(3, weatherData.getPop());
                preparedStatement.setDouble(4, weatherData.getHumidity());
                preparedStatement.setDouble(5, weatherData.getClouds());
                preparedStatement.setDouble(6, weatherData.getWindSpeed());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists(Connection connection, String tableName) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp DATETIME, temperature REAL, precipitation REAL, humidity REAL, clouds REAL, wind_speed REAL)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
            preparedStatement.executeUpdate();
        }
    }
}
