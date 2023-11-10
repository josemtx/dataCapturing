package Mataix.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDataBase {
    public static void main(String[] args) {
        // Establecer la URL de la base de datos
        final String url = "jdbc:sqlite:C:\\Users\\josem\\IdeaProjects\\dataCapturing\\src\\main\\resources\\database.db";

        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                System.out.println("Conexión exitosa");

                // Crear una declaración
                Statement statement = connection.createStatement();

                // Crear una tabla para cada isla (ejemplo con isla "Telde")
                String createTableQuery = "CREATE TABLE IF NOT EXISTS TeldeWeather (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "timestamp DATETIME," +
                        "temperature REAL," +
                        "pop REAL," +
                        "humidity REAL," +
                        "clouds INT," +
                        "windSpeed REAL" +
                        ")";

                statement.executeUpdate(createTableQuery);

                System.out.println("Tabla creada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}