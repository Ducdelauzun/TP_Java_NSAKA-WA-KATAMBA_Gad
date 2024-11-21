package com.example.tp_java_nsakawakatamba_gad;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Earth extends Group {
    private Rotate ry;
    private Sphere sph;
    private ArrayList<Sphere> yellowSphere;

    public Earth() {
        sph = new Sphere(300);

        Image earthTexture = new Image(getClass().getResourceAsStream("earth_lights_4800.png"));

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(earthTexture);
        sph.setMaterial(material);

        ry = new Rotate(0, Rotate.Y_AXIS);
        this.getTransforms().add(ry);

        this.getChildren().add(sph);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                double angle = (time % 15000000000L) / 15000000000.0 * 360.0;
                ry.setAngle(angle);
            }
        };
        animationTimer.start();
    }

    public Sphere createSphere(Aeroport a, Color color) {
        double R = 300; // Rayon de la Terre
        double latitude = Math.toRadians(a.getLatitude()); // Latitude en radians
        double longitude = Math.toRadians(a.getLongitude()); // Longitude en radians

        // Calcul des coordonnées (X, Y, Z) pour l'aéroport
        double X = R * Math.cos(latitude) * Math.sin(longitude);
        double Y = -R * Math.sin(latitude); // Latitude inversée
        double Z = -R * Math.cos(latitude) * Math.cos(longitude);

        // Création de la sphère
        Sphere airportSphere = new Sphere(2); // Rayon de la sphère = 2
        airportSphere.setMaterial(new PhongMaterial(color)); // Application de la couleur

        // Positionner la sphère à un emplacement fixe (en dehors de la rotation de la Terre)
        airportSphere.setTranslateX(X);
        airportSphere.setTranslateY(Y);
        airportSphere.setTranslateZ(Z);

        return airportSphere;
    }

    public void displayRedSphere(Aeroport a) {
        Sphere redSphere = createSphere(a, Color.RED);
        this.getChildren().add(redSphere);
    }

}
