
import views.MenuManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        MenuManager manager = new MenuManager(new Scanner(System.in));
        manager.run();
    }
}
