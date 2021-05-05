import views.MenuManager;
import java.util.Scanner;
import java.util.logging.Logger;


public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(String.valueOf(Main.class));
        logger.info("This is my first log4j's statement");
        MenuManager manager = new MenuManager(new Scanner(System.in));
        manager.run();

    }
}
