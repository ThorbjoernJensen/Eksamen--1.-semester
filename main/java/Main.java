import exceptionHandling.ExceptionHandling;
import ui.MainMenu;

public class Main {

    public static void main(String[] args) throws ExceptionHandling {
        try {
            MainMenu mainMenu = new MainMenu();
            mainMenu.mainMenuLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
