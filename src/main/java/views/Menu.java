package views;

import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner;
    protected List<String> options;

    public void run() {

    }

    public List<String> getOptions() {
        return options;
    }

    public abstract Menu getMenu(int option);

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract boolean checkValidation(String... input);

    @Override
    public String toString() {
        return "";
    }
}