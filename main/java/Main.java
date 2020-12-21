import exceptionHandling.ExceptionHandling;
import ui.MainMenu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
            MainMenu mainMenu = new MainMenu();
        try {
            mainMenu.mainMenuLoop();
        } catch (Exception e) {
            throw new ExceptionHandling(e);
        }

    }
}
