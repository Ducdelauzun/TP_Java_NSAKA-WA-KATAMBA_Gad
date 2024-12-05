package com.example.tp_java_nsakawakatamba_gad;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JsonFlightFillerOracle {
    private ArrayList<Flight> list = new ArrayList<Flight>();

    public JsonFlightFillerOracle(String jsonString, World w) {
        try {
            InputStream is = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            JsonReader rdr = Json.createReader(is);
            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("data");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                try {
                    JsonObject departure = result.getJsonObject("departure");
                    JsonObject arrival = result.getJsonObject("arrival");

                    String airLineCode = departure.getString("iata");
                    String airLineName = departure.getString("airport");

                    String departureTimeString = departure.getString("scheduled");
                    String arrivalTimeString = arrival.getString("scheduled");

                    departureTimeString = departureTimeString.split("\\+")[0];
                    arrivalTimeString = arrivalTimeString.split("\\+")[0];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                    LocalDateTime departureTime = LocalDateTime.parse(departureTimeString, formatter);
                    LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeString, formatter);

                    Flight flight = new Flight(airLineCode, airLineName, arrivalTime, departureTime);
                    list.add(flight);
                    System.out.println(flight);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Flight> getList() {
        return list;
    }

    public static void main (String[] args){
        try {
            World w = new World ("src/main/resources/com/example/tp_java_nsakawakatamba_gad/airport-codes_no_comma.csv");
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/com/example/tp_java_nsakawakatamba_gad/JsonOrly.txt"));
            String test = br.readLine();
            JsonFlightFillerOracle jSonFlightFillerO = new JsonFlightFillerOracle(test,w);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}