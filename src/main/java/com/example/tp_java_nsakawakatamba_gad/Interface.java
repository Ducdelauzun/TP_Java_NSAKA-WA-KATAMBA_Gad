package com.example.tp_java_nsakawakatamba_gad;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Interface extends Application {

    private double previousY;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello world");

        Earth earth = new Earth();

        Group root = new Group();
        root.getChildren().add(earth);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);

        Scene theScene = new Scene(root, 600, 400, true);
        theScene.setCamera(camera);

        theScene.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                // Clic gauche
                if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    System.out.println("Clicked on : (" + event.getSceneX() + ", " + event.getSceneY() + ")");
                }

                if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                    double deltaY = event.getSceneY() - previousY;
                    previousY = event.getSceneY();

                    Translate translate = new Translate(0, 0, deltaY * 0.5);
                    camera.getTransforms().add(translate);
                }
            }

            if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                // Clic droit
                PickResult pickResult = event.getPickResult();

                if (pickResult.getIntersectedNode() != null) {
                    double x = pickResult.getIntersectedTexCoord().getX();
                    double y = pickResult.getIntersectedTexCoord().getY();

                    double latitude = 180 * (0.5 - y);
                    double longitude = 360 * (x - 0.5);

                    System.out.println("Latitude: " + latitude + ", Longitude: " + longitude);

                    World world = new World("src/main/resources/com/example/tp_java_nsakawakatamba_gad/airport-codes_no_comma.csv");
                    Aeroport nearestAirport = world.findNearest(latitude, longitude);

                    if (nearestAirport != null) {
                        System.out.println("Aéroport le plus proche: " + nearestAirport);
                        earth.displayRedSphere(nearestAirport);
                    } else {
                        System.out.println("Aucun aéroport trouvé à proximité.");
                    }
                }
            }
        });

        primaryStage.setScene(theScene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
