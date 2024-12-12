package com.example.tp_java_nsakawakatamba_gad;

import javafx.application.Platform;

public class ApiRequestTask implements Runnable {

    private final Earth earth;
    private final World world;
    private final Aeroport nearestAirport;

    public ApiRequestTask(Earth earth, World world, Aeroport nearestAirport) {
        this.earth = earth;
        this.world = world;
        this.nearestAirport = nearestAirport;
    }

    @Override
    public void run() {
        ApiRequester apiRequester = new ApiRequester();
        String flightData = apiRequester.getFlightData();

        if (flightData != null) {
            JsonFlightFillerOracle flightFiller = new JsonFlightFillerOracle(flightData, world);

            // Mise à jour de l'interface utilisateur sur le thread principal
            Platform.runLater(() -> {
                for (Flight flight : flightFiller.getList()) {
                    Aeroport departureAirport = world.findByCode(flight.getAirLineCode());
                    if (departureAirport != null) {
                        Interface.displayYellowBall(earth, departureAirport);
                    }
                }
            });
        } else {
            System.out.println("Aucune donnée récupérée de l'API.");
        }
    }
}
