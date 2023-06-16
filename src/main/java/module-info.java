module com.example.kolos2k22zapierdolesie {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kolos2k22cwicz to javafx.fxml;
    exports com.example.kolos2k22cwicz;
}