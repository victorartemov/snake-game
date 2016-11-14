package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Виктор on 23.10.2016.
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        //специфическая хренотень из javafx
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../screen-layout.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        //add stylesheet file
        scene.getStylesheets().add("custom-style.css");

        primaryStage.resizableProperty().set(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
    }
}
