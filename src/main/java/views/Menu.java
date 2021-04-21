package views;

import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner;
    protected List<String> options;

    public abstract void run();

    public List<String> getOptions() {
        return options;
    }

    public abstract Menu getMenu(int option);

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean checkValidation(String... input) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }
}