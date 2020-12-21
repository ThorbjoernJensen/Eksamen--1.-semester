import exceptionHandling.ExceptionHandling;
import ui.MainMenu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            MainMenu mainMenu = new MainMenu();
            mainMenu.mainMenuLoop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
