package com.petadoption;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.petadoption.db.DBConnection;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        Scene scene = new Scene(loader.load(), 900, 600);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        stage.setTitle("Paw & Home Adoption Agency");
        stage.setScene(scene);
        stage.setMinWidth(750);
        stage.setMinHeight(500);
        stage.show();
    }

    @Override
    public void stop() {
        // Close the DB connection cleanly when the window is closed
        try {
            DBConnection.getInstance().close();
        } catch (Exception ignored) {}
    }

    public static void main(String[] args) {
        launch(args);
    }
}
