package Mataix.control;

import Mataix.model.Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLiteWeatherStore {
    private static final String url = "jdbc:sqlite:src/main/resources/database.db";

    public static void insertOrUpdateWeatherData(List<Weather> weatherDataList) {
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                System.out.println("Successful connection");

                for (Weather weatherData : weatherDataList) {
                    if (recordExists(connection, weatherData)) {
                        updateWeatherData(connection, weatherData);
                    } else {
                        insertWeatherData(connection, weatherData);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean recordExists(Connection connection, Weather weatherData) throws SQLException {
        String locationName = weatherData.getLocation().getIsland().replace(" ", "");
        String query = String.format("SELECT COUNT(*) FROM %sWeather WHERE timestamp = ?", locationName);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, weatherData.getTs().toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private static void updateWeatherData(Connection connection, Weather weatherData) throws SQLException {
        String locationName = weatherData.getLocation().getIsland().replace(" ", "");
        String updateQuery = String.format(
                "UPDATE %sWeather SET " +
                        "temperature = ?, " +
                        "pop = ?, " +
                        "humidity = ?, " +
                        "clouds = ?, " +
                        "windSpeed = ? " +
                        "WHERE timestamp = ?", locationName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setDouble(1, weatherData.getTemp());
            preparedStatement.setDouble(2, weatherData.getPop());
            preparedStatement.setDouble(3, weatherData.getHumidity());
            preparedStatement.setDouble(4, weatherData.getClouds());
            preparedStatement.setDouble(5, weatherData.getWindSpeed());
            preparedStatement.setString(6, weatherData.getTs().toString());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No existing record found to update for " + locationName + " at " + weatherData.getTs().toString());
            } else {
                System.out.println("Record updated successfully for " + locationName + " at " + weatherData.getTs().toString());
            }
        }
    }

    private static void insertWeatherData(Connection connection, Weather weatherData) throws SQLException {
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
            System.out.println("Data inserted successfully in " + locationName);
        }
    }
}
