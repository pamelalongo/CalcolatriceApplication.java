module com.calcolatrice.calcolatrice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;
    requires javaluator;


    opens com.calcolatrice.calcolatrice to javafx.fxml;
    exports com.calcolatrice.calcolatrice;
}