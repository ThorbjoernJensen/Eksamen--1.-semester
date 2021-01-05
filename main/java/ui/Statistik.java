package ui;

import domain.Order;
import domain.Pizza;
import persistence.Database;
import persistence.DbMenuCardMapper;
import persistence.DbOrderMapper;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Statistik {

    private DbMenuCardMapper dbMenuCardMapper;
    private DbOrderMapper dbOrderMapper;

    private List<Pizza> pizzaScores;
    private List<String> dates;


    public Statistik(Database database) {
        this.dbOrderMapper = new DbOrderMapper(database);
        this.dbMenuCardMapper = new DbMenuCardMapper(database);

    }

    public void pizzaCount() throws Exception {
        List<Pizza> allPizzas = dbMenuCardMapper.getAllPizzas();
        int pizzaCount = 0;
        int pizzaRevenue = 0;
        int totalRevenue = 0;
        int maxRevenue = pizzaRevenue;
        int token = 0;
        int token2 = 0;
        int i=0;
        System.out.println("salgs-statistik for pizzaer: ");

//        for (int i = 1; i < allPizzas.size()+1; i++) {
        for (Pizza pizza : allPizzas) {
            i=pizza.getPizzaId();

            pizzaCount = 0;
//            Pizza pizza = dbMenuCardMapper.getPizzaById(i);
            String statement = "select * from mario.order where pizza_id = " + i;
            List<Order> selectedOrders = dbOrderMapper.getOrdersAsList(statement);
            for (Order o : selectedOrders) {
                pizzaCount = pizzaCount + o.getAmount();
            }
            pizzaRevenue = pizzaCount * pizza.getPrice();
            System.out.println("pizza nr. " + pizza.getPizzaNo() + ", " + pizza.getName() + ": " + pizzaCount + " stk.");
            System.out.println("samlet omsætning: " + pizzaRevenue + " kr.\n");
            totalRevenue = totalRevenue + pizzaRevenue;
            if (pizzaRevenue >= maxRevenue) {
                if (pizzaRevenue == maxRevenue) {
                    token2 = i;
                }
                if (pizzaRevenue > maxRevenue) {
                    maxRevenue = pizzaRevenue;
                    token = i;
                    token2 = 0;
                }
            }
        }
        if (totalRevenue != 0) {
            if (token2 == 0) {
                System.out.println("Total omsætning: " + totalRevenue + "kr.");
                System.out.println("pizza med størst omsætning er pizza nr. " + dbMenuCardMapper.getPizzaById(token).getPizzaNo() +
                        " " + dbMenuCardMapper.getPizzaById(token).getName() + ", der har indtjent " + maxRevenue + " kr.\n");
            }
            if (token2 != 0) {
                System.out.println("Der er 2 pizzaer med samme omsætning: " + "pizza nr. " + dbMenuCardMapper.getPizzaById(token).getPizzaNo() +
                        " og pizza nr. " + dbMenuCardMapper.getPizzaById(token2).getPizzaNo() + " har begge solgt for " + maxRevenue + " kr.\n");
            }
        } else {
            System.out.println("Der har ikke været nogen omsætning.");
        }
    }

    public void pizzaCountDate() throws Exception {
        List<Pizza> allPizzas = dbMenuCardMapper.getAllPizzas();
        int pizzaCount = 0;
        int pizzaRevenue = 0;
        String statementPrint = "select * from mario.order date where date IS NOT NULL ";
        List<Order> notNullOrders = dbOrderMapper.getOrdersAsList(statementPrint);
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < notNullOrders.size(); i++) {
            if (dates.contains(String.valueOf(notNullOrders.get(i).getDate()))) {
            } else {
                dates.add(String.valueOf(notNullOrders.get(i).getDate()));
            }
        }
        System.out.println(dates.toString());
        String orderdate = Input.getString("Dato? ");
        int totalRevenue = 0;
        for (int i = 1; i < allPizzas.size(); i++) {
            String statement2 = "select * from mario.order where date = '" + orderdate + "'" + " AND pizza_id = " + i;
            List<Order> selectedOrders2 = dbOrderMapper.getOrdersAsList(statement2);
            Pizza pizza = dbMenuCardMapper.getPizzaById(i);
            for (Order o : selectedOrders2) {
                pizzaCount = o.getAmount();
                pizzaRevenue = pizzaCount * pizza.getPrice();
                System.out.println("Antal solgte pizzaer for nr. " + pizza.getPizzaNo() + " , " + pizza.getName() + " er " + pizzaCount + " stk.");
                System.out.println("Samlet omsætning for denne pizza er " + pizzaRevenue + " kr.\n");
                totalRevenue = totalRevenue + pizzaRevenue;
            }
        }
        System.out.println("Total omsætning for den: " + orderdate + " er: " + totalRevenue + "kr." + "\n");
    }

    public void revenueCountDate() throws Exception {
        int totalRevenue = 0;
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        LocalDate nowDate = ts.toLocalDateTime().toLocalDate();
        int n = 0;
        boolean running = true;
        while (running) {
            LocalDate nDaysAgo = nowDate.minus(n, ChronoUnit.DAYS);
            String statement = "select * from mario.order where date = '" + nDaysAgo + "'";
            List<Order> selectedOrders = dbOrderMapper.getOrdersAsList(statement);
            int dayRevenue = 0;
            for (Order o : selectedOrders) {
                dayRevenue = dayRevenue + o.getAmount() * dbMenuCardMapper.getPizzaById(o.getPizzaId()).getPrice();
            }
            System.out.println("omsætning den " + nDaysAgo + " er " + dayRevenue + " kr.");
            totalRevenue = totalRevenue + dayRevenue;
            n++;
            if (n > 30) {
                running = false;
            }
        }
        System.out.println("\n Total omsætning: " + totalRevenue + "kr.");
    }
}