package views;

import java.util.Arrays;

public class MessageMenu extends Menu{

    public MessageMenu() {
        options = Arrays.asList("SavedMessage", "PeopleMessage");
    }

    @Override
    public void run() {
        System.out.println("**Message Menu**");

       do{
           System.out.println("Which messages do you wanna see?!");
           for (int i = 0; i < options.size(); i++) {
               System.out.println(i + " : " + options.get(i));
           }
       }while();
    }

    @Override
    public Menu getMenu(int option) {

    }
}
