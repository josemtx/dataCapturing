package Mataix.model;

public class Location {
    private String island;
    private final double lat;
    private final double lon;

    public Location(String island, double lat, double lon) {
        this.island = island;
        this.lat = lat;
        this.lon = lon;
    }

    public String getIsland() {
        return island;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}