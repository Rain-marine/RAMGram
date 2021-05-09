import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import views.MenuManager;

import java.util.Scanner;


public class Main {
    public static Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
       log.info("Application Started");
        MenuManager manager = new MenuManager(new Scanner(System.in));
        manager.run();

    }
}
