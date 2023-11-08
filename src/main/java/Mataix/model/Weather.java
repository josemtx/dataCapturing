package Mataix.model;

import java.sql.Timestamp;
import java.time.Instant;

public class Weather {
    private Instant instant;
    private double temp;
    private double pop;
    private double humidity;
    private double clouds;
    private double windSpeed;
    private Location Locations;

    public Weather(Timestamp ts, double temp, double pop, double humidity, double clouds, double windSpeed, Location locations) {
        this.instant = Instant.now();
        this.temp = temp;
        this.pop = pop;
        this.humidity = humidity;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        Locations = locations;
    }

    public Instant getTs() {
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
