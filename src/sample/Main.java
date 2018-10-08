package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sample.WordTranslate.DBHandler;
import sample.WordTranslate.HintManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 960, 640));
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
