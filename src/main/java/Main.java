import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import views.MenuManager;

import java.util.Scanner;


public class Main extends Application {
    static Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
       log.info("Application Started");
//        MenuManager manager = new MenuManager(new Scanner(System.in));
//        manager.run();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("RAMGram");
        primaryStage.getIcons().add(new Image("file:resources/Images/icon.jpg"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
