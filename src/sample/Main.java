package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Calculator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Calculator.getInstance().setUp();
        Controller controller = new Controller();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        Parent root = loader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Panini Collector");
        primaryStage.setScene(new Scene(root, 1800, 1000));
        primaryStage.show();
        controller = loader.getController();

        controller.setMain(this);

    }
    public static void main(String[] args) {
        launch(args);
    }

}
