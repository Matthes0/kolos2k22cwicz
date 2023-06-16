package com.example.kolos2k22zapierdolesie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        WordBag wordBag = new WordBag();
        wordBag.populate();
        Server server = new Server(5000, wordBag);
        server.start();
        server.startSending();
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        ServerThread serverThread = new ServerThread("localhost",5000, fxmlLoader.getController());
        serverThread.start();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}