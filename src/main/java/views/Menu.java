package views;

import views.profiles.FollowingProfile;

import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected static Scanner scanner = new Scanner(System.in);
    protected List<String> options;

    public abstract void run();

    public List<String> getOptions() {
        return options;
    }

    public abstract Menu getMenu(int option);

    public boolean checkValidation(String... input) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }
}