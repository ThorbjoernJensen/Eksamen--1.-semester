import persistence.Database;
import persistence.DbMenuCardMapper;
import persistence.DbOrderMapper;
import ui.MainMenu;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenuLoop();

    }
}
