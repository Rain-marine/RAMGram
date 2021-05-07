import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import views.MenuManager;

import java.util.Scanner;


public class Main {
    static Logger log = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
       log.info("Application Started");
        MenuManager manager = new MenuManager(new Scanner(System.in));
        manager.run();

    }
}
