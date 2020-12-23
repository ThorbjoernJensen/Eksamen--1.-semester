import exceptionHandling.ExceptionHandling;
import ui.MainMenu;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            MainMenu mainMenu = new MainMenu();
            mainMenu.mainMenuLoop();
        } catch (Exception e) {
            throw new ExceptionHandling(e);
        }

    }
}
