package Mataix.control;

public class Main {
    public static void main(String[] args) {
        // Llamada directa al WeatherController si el controlador se diseñó para ser estático
        WeatherController.main(args);

        // O si prefieres instanciar el WeatherController
        // WeatherController weatherController = new WeatherController();
        // weatherController.run(); // Suponiendo que tengas un método run en WeatherController
    }
}
