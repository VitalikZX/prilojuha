package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*****************************************************************
 * При работе с библиотекой jssc помимо включения исходного кода *
 * нужно в src создать директорию lib и положить туда jssc.jar   *
 *****************************************************************/

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Приложуха");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.getScene().getStylesheets().add("css/JMetroDarkTheme.css");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}