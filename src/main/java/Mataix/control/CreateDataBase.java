package Mataix.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDataBase {
    public static void create(String locationName) {
        final String url = "jdbc:sqlite:src/main/resources/database.db";

        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
                Statement statement = connection.createStatement();
                String tableName = locationName.replace(" ", "");
                String createTableQuery = String.format(
                        "CREATE TABLE IF NOT EXISTS %sWeather (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "timestamp DATETIME," +
                                "temperature REAL," +
                                "pop REAL," +
                                "humidity REAL," +
                                "clouds REAL," +
                                "windSpeed REAL" +
                                ")", tableName);

                statement.executeUpdate(createTableQuery);

                System.out.println("Tabla creada exitosamente para " + locationName);
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla para " + locationName);
            e.printStackTrace();
        }
    }
}
