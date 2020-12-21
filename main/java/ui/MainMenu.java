package ui;

import domain.Pizza;
import domain.Order;
import persistence.Database;
import persistence.DbMenuCardMapper;
import persistence.DbOrderMapper;

import java.util.List;

public class MainMenu {

    private final String USER = "testdb_user";
    private final String PASSWORD = "1234";
    private final String URL = "jdbc:mysql://localhost:3306/mario?serverTimezone=CET&useSSL=false";

    private Database database = new Database(USER, PASSWORD, URL);
    private DbMenuCardMapper dbMenuCardMapper = new DbMenuCardMapper(database);
    private DbOrderMapper dbOrderMapper = new DbOrderMapper(database);
    private Statistik statistik = new Statistik(database);

    public void mainMenuLoop() {

        boolean running = true;

        while (running) {
            showMenu();
            switch (Input.getInt("Vælg 1-11: ")) {
                case 1:
                    showMenuCard();
                    break;
                case 2:
                    showSinglePizza();
                    break;
                case 3:
                    deletePizza();
                    break;
                case 4:
                    insertPizza();
                    break;
                case 5:
                    updatePizza();
                    break;
                case 6:
                    String statement = "select * from mario.order where remove=0 order by date DESC, pickup_time ASC";
                    System.out.println(dbOrderMapper.getOrdersAsList(statement));
                    break;
                case 7:
                    insertOrder();
                    break;
                case 8:
                    updateOrder();
                    break;
                case 9:
                    deleteOrder();
                    break;
                case 10:
                    System.out.println("Hvilken statistik vil du gerne se? ");
                    System.out.println("1: Omsætning fordelt på pizzaer \n2: Daglig omsætning over periode \n3: Vis handler for given dato ");
                    int option = Input.getInt("");
                    switch (option) {
                        case 1:
                            statistik.pizzaCount();
                            break;
//                        case 2:
////TODO                            Mest solgte pizza. Udtræk fra SQL eller fra metode "statistik.pizzaCount()".
//                            break;
                        case 2:
                            statistik.revenueCountDate();
//                            statistik.pizzaRevenue();
                            break;
                        case 3:
                            statistik.pizzaCountDate();
                            break;
                    }
                    break;
                case 11:
                    setOrderAsDone();
//                    System.out.println("Ønsker du at lukke programmet? ");
//                    System.out.println("Tryk y for at fortsætte eller en vilkårlig tast for at gå tilbage ");
//                    String input = Input.getString("");
//                    if (input.equals("y")){
//                        running=false;
//                    }else {
//                        showMenu();
//                    }
                    break;
                default:
                    System.out.println("Vælg en menu ");
            }
        }
        System.out.println("Tak for denne gang!");
    }

    private void setOrderAsDone() {

        if (dbOrderMapper.setOrderAsDone()) {
            System.out.println("ordre " + dbOrderMapper.getOrderNr() + " er blevet leveret til kunden");

        }

    }

    private void showMenu() {
        System.out.println("**** Marios pizzabar - hovedmenu ******");
        System.out.println("[1] Vis menukort [2] Vis enkelt pizza [3] Fjern pizza [4] Opret ny pizza [5] Opdater pizza [6] Se alle ordre [7] Opret ny ordre [8] Opdater ordre [9] Slet ordre [10] Statistik [11] Afslut program");
    }

    private void updatePizza() {
        System.out.println("***** Opdater pizza *******");
        int pizzaNo = Input.getInt("Indtast pizza nummer på den du vil rette: ");
        System.out.println("Indtast ny værdi, hvis den skal rettes - eller blot <retur>: ");
        Pizza pizza = dbMenuCardMapper.getPizzaById(pizzaNo);
        String newPizzaNoInput = Input.getString("Pizzanummer: (" + pizza.getPizzaNo() + "): ");
        if (newPizzaNoInput.length() > 0) {
            pizza.setPizzaNo(Integer.parseInt(newPizzaNoInput));
        }
        String newPizzaNameInput = Input.getString("Pizza navn: (" + pizza.getName() + "): ");
        if (newPizzaNameInput.length() > 0) {
            pizza.setName(newPizzaNameInput);
        }
        String newPizzaIngredientsInput = Input.getString("Pizza ingredienser: (" + pizza.getIngredients() + "): ");
        if (newPizzaIngredientsInput.length() > 0) {
            pizza.setIngredients(newPizzaIngredientsInput);
        }
        String newPizzaPriceInput = Input.getString("Pizza pris: (" + pizza.getPrice() + "): ");
        if (newPizzaPriceInput.length() > 0) {
            pizza.setPrice(Integer.parseInt(newPizzaPriceInput));
        }
        boolean result = dbMenuCardMapper.updatePizza(pizza);
        if (result) {
            System.out.println("Pizzaen med nr = " + pizzaNo + " er nu opdateret");
        } else {
            System.out.println("Vi kunne desværre ikke opdatere den nye pizza.");
        }
    }

    private void insertPizza() {
        System.out.println("**** Opret ny pizza *******");
        int pizzaNo = Input.getInt("Indtast pizza nummer: ");
        String name = Input.getString("Indtast navn på pizza: ");
        String ingredients = Input.getString("Indtast indhold: ");
        int price = Input.getInt("Indtast pris: ");
        Pizza newPizza = new Pizza(pizzaNo, name, ingredients, price);
        Pizza insertedPizza = dbMenuCardMapper.insertPizza(newPizza);
        if (insertedPizza != null) {
            System.out.println("Pizzaen med nr = " + pizzaNo + " er nu tilføjet");
            System.out.println("Pizzaen har fået DB id = " + insertedPizza.getPizzaId());
        } else {
            System.out.println("Vi kunne desværre ikke oprette den nye pizza. PizzaNo findes allerede.");
        }
    }

    private void deletePizza() {
        int pizzaNo = Input.getInt("Indtast nummer på pizza som skal fjernes: ");
        boolean result = dbMenuCardMapper.deletePizza(pizzaNo);
        if (result) {
            System.out.println("Pizzaen med nr = " + pizzaNo + " er nu fjernet");
        } else {
            System.out.println("Pizzaen med nr = " + pizzaNo + " findes ikke, og kan derfor ikke fjernes");
        }
    }

    private void showSinglePizza() {
        int pizzaNo = Input.getInt("Indtast pizzanummer: ");
        Pizza pizza = dbMenuCardMapper.getPizzaById(pizzaNo);
        if (pizza != null) {
            System.out.println("Du har fundet pizza nummer: " + pizzaNo);
            System.out.println(pizza.toString());
        } else {
            System.out.println("Pizza med nr = " + pizzaNo + " findes desværre ikke");
        }
    }

    private void showMenuCard() {
        System.out.println("**** Menukort hos Marios ******");
        List<Pizza> menuCard = dbMenuCardMapper.getAllPizzas();
        for (Pizza pizza : menuCard) {
            System.out.println(pizza.toString());
        }
    }

    private void insertOrder() {
        System.out.println("**** Opret ny ordre *******");
        int pizzaNo = Input.getInt("Indtast pizza nummer: ");
        Pizza chosenPizza = dbMenuCardMapper.getPizzaById(pizzaNo);
        if (chosenPizza == null) {
            System.out.println("Pizza med nr " + pizzaNo + " findes ikke i menuen ");
            return; // TODO Ret så hvis man skriver en pizza der ikke findes skal den fortælle det og efterfølgende give dig lov til at skrive nyt nr.
        } else {
            int amount = Input.getInt("Indtast antal: ");
            int pickup_time = Input.getInt("Hvad tid ønsker du at hente den: ");
            String custemor_name = Input.getString("Hvad er dit navn: ");
            String phone = Input.getString("Skriv telefon nr: ");
            if (chosenPizza != null) {
                Order newOrder = new Order(chosenPizza.getPizzaId(), amount, pickup_time, custemor_name, phone);
                Order insertedOrder = dbOrderMapper.insertOrder(newOrder);
                if (insertedOrder != null) {
                } else {
                    System.out.println("Vi kunne desværre ikke oprette den nye pizza. PizzaNo findes allerede.");
                }
            } else {
                System.out.println("Det lykkedes ikke at finde en pizza med det nummer");
            }
        }
    }

    private void updateOrder() {
        System.out.println("***** Opdater order *******");
        int orderNo = Input.getInt("Indtast order nummer på den du vil rette: ");
        Order order = dbOrderMapper.getOrderById(orderNo);
        System.out.println("Hvad ønsker du at rette? " + "\n" + "1: Alt: " + "2: Pizza nr: " + "3: Pizza mængde: " + "4: Leverings tid: " + "5: Kundens navn: " + "6: Tlf nr: ");
        int option = Input.getInt("");
        switch (option) {
            case 1:
                int newPizzaInput = Input.getInt("Pizza nr: (" + order.getPizzaId() + "): ");
                if (newPizzaInput > 0) {
                    order.setPizzaId(newPizzaInput);
                }
                int newPizzaAmountInput = Input.getInt("Pizza mængde: (" + order.getAmount() + "): ");
                if (newPizzaAmountInput > 0) {
                    order.setAmount(newPizzaAmountInput);
                }
                int newPickupTimeInput = Input.getInt("Leverings tid: (" + order.getPickuptime() + "): ");
                if (newPickupTimeInput > 0) {
                    order.setPickuptime(newPickupTimeInput);
                }

                String newNameInput = Input.getString("Kundens navn: (" + order.getCustemorName() + "): ");
                if (newNameInput.length() > 0) {
                    order.setCustemorName(newNameInput);
                }

                String newPhoneInput = Input.getString("Tlf nr: (" + order.getPhone() + "): ");
                if (newPhoneInput.length() > 0) {
                    order.setPhone(newPhoneInput);
                }
                updateSqlOrder(orderNo, order);
                break;
            case 2:
                int newPizzaInputOption2 = Input.getInt("Pizza nr: (" + order.getPizzaId() + "): ");
                if (newPizzaInputOption2 > 0) {
                    order.setPizzaId(newPizzaInputOption2);
                }
                updateSqlOrder(orderNo, order);
                break;

            case 3:
                int newPizzaAmountInputOption3 = Input.getInt("Pizza mængde: (" + order.getAmount() + "): ");
                if (newPizzaAmountInputOption3 > 0) {
                    order.setAmount(newPizzaAmountInputOption3);
                }
                updateSqlOrder(orderNo, order);
                break;
            case 4:
                int newPickupTimeInput4 = Input.getInt("Leverings tid: (" + order.getPickuptime() + "): ");
                if (newPickupTimeInput4 > 0) {
                    order.setPickuptime(newPickupTimeInput4);
                }
                updateSqlOrder(orderNo, order);
                break;
            case 5:
                String newNameInput5 = Input.getString("Kundens navn: (" + order.getCustemorName() + "): ");
                if (newNameInput5.length() > 0) {
                    order.setCustemorName(newNameInput5);
                }
                updateSqlOrder(orderNo, order);
                break;
            case 6:
                String newPhoneInput6 = Input.getString("Tlf nr: (" + order.getPhone() + "): ");
                if (newPhoneInput6.length() > 0) {
                    order.setPhone(newPhoneInput6);
                }
                updateSqlOrder(orderNo, order);
                break;
        }

    }

    private void updateSqlOrder(int orderNo, Order order) {
        boolean result = dbOrderMapper.updateOrder(order);
        if (result) {
            System.out.println("Ordren med nr = " + orderNo + " er nu opdateret");
        } else {
            System.out.println("Vi kunne desværre ikke opdatere den nye ordre.");
        }
    }

    private void deleteOrder() {
        int orderNo = Input.getInt("Indtast nummer på den ordre som skal fjernes: ");
        boolean result = dbOrderMapper.deleteOrder(orderNo);
        if (result) {
            System.out.println("Ordren med nr = " + orderNo + " er nu fjernet");
        } else {
            System.out.println("Ordren med nr = " + orderNo + " findes ikke, og kan derfor ikke fjernes");
        }
    }

}