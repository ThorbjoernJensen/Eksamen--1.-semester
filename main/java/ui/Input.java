package ui;

import java.util.Scanner;

public class Input {

    public static String getString(String question) {
        System.out.print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int getInt(String question) {
        while (true) {
            try {
                return Integer.parseInt(getString(question));
            } catch (Exception e) {
                System.out.println("Det må ikke være et tal ord");
            }
        }
    }

    public static String getMinutsToTimeFormat(int minuts){

        String hoursText = String.valueOf(minuts / 60 );
        String minutsText = String.valueOf(minuts % 60);
        return hoursText + "." +minutsText;

    }

    public static int getTimeInMinutes(String question) {
        while (true) {
            System.out.print(question);
            Scanner scanner = new Scanner(System.in);
            String timeAsString = scanner.nextLine();
            String[] strings = timeAsString.split("\\.");
            try {
                int hourInMinutes = Integer.parseInt(strings[0]) * 60;
                int minutes = Integer.parseInt(strings[1]);
                return hourInMinutes + minutes;
            } catch (NumberFormatException e) {
                System.out.println("fejl i tidsformat. Det skal være formen hh.mm");
            }
        }

    }
}
