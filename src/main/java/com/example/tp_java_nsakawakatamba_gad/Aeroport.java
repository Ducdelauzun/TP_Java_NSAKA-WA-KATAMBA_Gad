package com.example.tp_java_nsakawakatamba_gad;

public class Aeroport {
    private String IATA;
    private String name;
    private String country;
    private double latitude;
    private double longitude;

    public Aeroport(String IATA, String name, String country, double latitude, double longitude) {
        this.IATA = IATA;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getIATA() {
        return IATA;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double calculDistance(Aeroport other) {
        double deltaLat = Math.toRadians(other.latitude - this.latitude);
        double deltaLong = Math.toRadians(other.longitude - this.longitude);
        double avgLat = Math.toRadians((this.latitude + other.latitude) / 2);

        // Calcul de la distance au carré en radians
        return Math.pow(deltaLat, 2) + Math.pow(deltaLong * Math.cos(avgLat), 2);
    }


    @Override
    public String toString() {
        return "Aeroport{" +
                "IATA='" + IATA + '\'' +
                ", Name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}