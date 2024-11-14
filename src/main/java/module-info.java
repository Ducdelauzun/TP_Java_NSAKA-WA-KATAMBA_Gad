module com.example.tp_java_nsakawakatamba_gad {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tp_java_nsakawakatamba_gad to javafx.fxml;
    exports com.example.tp_java_nsakawakatamba_gad;
}