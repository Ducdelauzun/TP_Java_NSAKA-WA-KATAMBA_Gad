module com.example.tp_java_nsakawakatamba_gad {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.glassfish.java.json;
    requires java.net.http;


    opens com.example.tp_java_nsakawakatamba_gad to javafx.fxml;
    exports com.example.tp_java_nsakawakatamba_gad;
}