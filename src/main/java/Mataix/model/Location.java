package Mataix.model;

public class Location {
    private String isla;
    private final double lat;
    private final double lon;

    public Location(String isla, double lat, double lon) {
        this.isla = isla;
        this.lat = lat;
        this.lon = lon;
    }

    public String getIsla() {
        return isla;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}