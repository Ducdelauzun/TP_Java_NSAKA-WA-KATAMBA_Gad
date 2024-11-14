package com.example.tp_java_nsakawakatamba_gad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class World {
    public ArrayList<Aeroport> list;

    public World(String fileName) {
        list = new ArrayList<>();
        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine(); // Lire l'en-tête
            s = buf.readLine(); // Lire la première ligne de données
            while (s != null) {
                s = s.replaceAll("\"", "");
                String[] fields = s.split(",");
                if (fields.length > 10 && fields[1].equals("large_airport")) {
                    try {
                        String iataCode = fields[9];
                        String name = fields[2];
                        String country = fields[5];
                        String[] coordinates = fields[11].split(" ");
                        double longitude = Double.parseDouble(coordinates[0]);
                        double latitude = Double.parseDouble(coordinates[1]);
                        Aeroport aeroport = new Aeroport(iataCode, name, country, latitude, longitude);
                        list.add(aeroport);
                    } catch (Exception e) {
                        System.out.println("Erreur de parsing de l'aéroport: " + s);
                    }
                }
                s = buf.readLine();
            }
        } catch (Exception e) {
            System.out.println("Maybe the file isn't there ?");
            e.printStackTrace();
        }
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
}
