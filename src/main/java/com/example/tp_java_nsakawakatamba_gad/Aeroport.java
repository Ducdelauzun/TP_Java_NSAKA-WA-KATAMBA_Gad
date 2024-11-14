package com.example.tp_java_nsakawakatamba_gad;

public class Aeroport {
    private String IATA;
    private String Name;
    private String country;
    private double latitude;
    private double longitude;

    public Aeroport(String IATA, String name, String country, double latitude, double longitude) {
        this.IATA = IATA;
        this.Name = name;
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

    public double calculDistance(Aeroport a) {
        double deltaTheta = this.latitude - a.getLatitude();
        double deltaPhi = this.longitude - a.getLongitude();
        return Math.pow(deltaTheta, 2) + Math.pow(deltaPhi, 2);
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "IATA='" + IATA + '\'' +
                ", Name='" + Name + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}