module com.example.dndrandomstats {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dndrandomstats to javafx.fxml;
    exports com.example.dndrandomstats;
}