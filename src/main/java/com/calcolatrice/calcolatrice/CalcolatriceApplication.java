package com.calcolatrice.calcolatrice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CalcolatriceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalcolatriceApplication.class.getResource("calcolatriceView.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("calcolatriceStyle.css")).toExternalForm());
        newStage.setScene(scene);

        newStage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}
