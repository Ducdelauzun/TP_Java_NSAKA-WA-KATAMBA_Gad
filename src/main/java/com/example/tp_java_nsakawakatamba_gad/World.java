package com.example.tp_java_nsakawakatamba_gad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class World {
    public ArrayList<Aeroport> list = new ArrayList<>();

    public World (String fileName) {
        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            while (s != null) {
                s = s.replace("\"", "");
                //Enleve les guillemets qui s´eparent les champs de donn´ees GPS.
                String fields[] = s.split(",");
                // Une bonne id´ee : placer un point d'arr^et ici pour du debuggage.
                if (fields[1].equals("large_airport")) {
                    // A continuer
                    String Name = fields[2];
                    String Country = fields[5];
                    String IATA = fields[9];
                    double Latitude = Double.parseDouble(fields[11]);
                    double Longitude = Double.parseDouble(fields[12]);

                    Aeroport aeroport = new Aeroport(IATA, Name, Country, Latitude, Longitude);
                    list.add(aeroport);
                }
                s = buf.readLine();
            }
        } catch (Exception e) {
            System.out.println("Maybe the file isn't there ?");
            System.out.println(list.get(list.size() - 1));
            e.printStackTrace();
        }
    }

    public double distance(double lat1, double long1, double lat2, double long2) {
        double deltaLat = lat2 - lat1;
        double deltaLong = long2 - long1;
        double latAverage = (lat1 + lat2) / 2;
        return deltaLat * deltaLat + (deltaLong * Math.cos(Math.toRadians(latAverage))) * (deltaLong * Math.cos(Math.toRadians(latAverage)));
    }

    public Aeroport findNearest(double latitude, double longitude) {
        Aeroport reference = new Aeroport("", "", "", latitude, longitude);
        Aeroport nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Aeroport aeroport : list) {
            double distance = reference.calculDistance(aeroport);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = aeroport;
            }
        }
        return nearest;
    }


    public Aeroport findByCode(String code) {
        for (Aeroport aeroport : list) {
            if (aeroport.getIATA().equalsIgnoreCase(code)) {
                return aeroport;
            }
        }
        return null;
    }

    public ArrayList<Aeroport> getList() {
        return list;
    }

    public static void main(String[] args){
        World w = new World ("src/main/resources/com/example/tp_java_nsakawakatamba_gad/airport-codes_no_comma.csv");
        System.out.println("Found "+w.getList().size()+" airports.");
        Aeroport paris = w.findNearest(2.316,48.866);
        Aeroport cdg = w.findByCode("CDG");
        double distance = w.distance(2.316,48.866,paris.getLongitude(),paris.getLatitude());
        System.out.println(paris);
        System.out.println(distance);
        double distanceCDG = w.distance(2.316,48.866,cdg.getLongitude(),cdg.getLatitude());
        System.out.println(cdg);
        System.out.println(distanceCDG);
    }
}