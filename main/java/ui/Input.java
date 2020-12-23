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

    public static int getIntTime(String question) {
        while (true) {
            try {
        String token = getString(question);
        int pickuptime = 0;
                if (token.contains(":") || token.contains(".") || token.contains(",")) {
                    token = token.replace(".", "");
                    token = token.replace(":", "");
                    token = token.replace(",","");
                }
                pickuptime = Integer.parseInt(token);
                if (pickuptime < 2400) {
                  return pickuptime;
                } else {
                    System.out.println("Fejl i tid - Over 24 timer. Prøv venligst at angive et nyt tidspunkt af formatet '0000' eller '00:00' eller '00.00' ");
                    return getIntTime("Afhentningstidspunkt: ");
                }
            } catch (Exception e) {
                System.out.println("Fejl i tid - Over 24 timer ");
            }
        }
    }
}
