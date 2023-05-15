package gaborets.lab3_fx.taskone;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SubFXMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gaborets/lab3_fx/MetroForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 700, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Subway station");
            primaryStage.show();
        } catch (Exception e) {//IllegalArgumentException
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

