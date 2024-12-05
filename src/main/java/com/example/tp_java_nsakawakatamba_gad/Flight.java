package com.example.tp_java_nsakawakatamba_gad;

import java.time.LocalDateTime;

public class Flight {
    private String airLineCode;
    private String airLineName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private int number;

    public Flight(String airLineCode, String airLineName, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        this.airLineCode = airLineCode;
        this.airLineName = airLineName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public String getAirLineCode() {
        return airLineCode;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "airLineCode='" + airLineCode + '\'' +
                ", airLineName='" + airLineName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
