package Mataix.model;

import java.sql.Timestamp;

public class Weather {
    private Timestamp instant;
    private double temp;
    private double pop;
    private double humidity;
    private double clouds;
    private double windSpeed;
    private Location Locations;

    public Weather(Timestamp instant, double temp, double pop, double humidity, double clouds, double windSpeed, Location locations) {
        this.instant = instant;
        this.temp = temp;
        this.pop = pop;
        this.humidity = humidity;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        Locations = locations;
    }

    public Timestamp getTs() {
        return instant;
    }

    public double getTemp() {
        return temp;
    }

    public double getPop() {
        return pop;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getClouds() {
        return clouds;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public Location getLocations() {
        return Locations;
    }
}
