package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
    private int x = 500, y = 500;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loade = new FXMLLoader(getClass().getResource("../sample/sample.fxml"));
        Parent root = null;
        loade.setController(new Controller(x, y, primaryStage));
        root = loade.load();
        primaryStage.setScene(new Scene(root, x, y));
        primaryStage.setTitle("Space Invaders");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
