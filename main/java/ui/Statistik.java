package ui;

import domain.Order;
import domain.Pizza;
import persistence.Database;
import persistence.DbMenuCardMapper;
import persistence.DbOrderMapper;

import java.util.ArrayList;
import java.util.Date;
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


    public void showOrdersByTime() {

    }

    public void pizzaCount() {
        List<Pizza> allPizzas = dbMenuCardMapper.getAllPizzas();
        int pizzaCount = 0;
        int pizzaRevenue = 0;
        Date orderdate;
        int totalRevenue = 0;
        for (int i = 1; i < allPizzas.size(); i++) {
            Pizza pizza = dbMenuCardMapper.getPizzaById(i);
            String statement = "select * from mario.order where pizza_id = " + i;
            List<Order> selectedOrders = dbOrderMapper.getOrdersAsList(statement);
            for (Order o : selectedOrders) {
                pizzaCount = pizzaCount + o.getAmount();
            }
            pizzaRevenue = pizzaCount * pizza.getPrice();
            System.out.println("antal solgte pizzaer for nr. " + pizza.getPizzaNo() + " , " + pizza.getName() + " er " + pizzaCount + " stk.");
            System.out.println("samlet omsætning for denne pizza er " + pizzaRevenue + " kr.\n");

            totalRevenue = totalRevenue + pizzaRevenue;
        }
        System.out.println("Total omsætning: " + totalRevenue + "kr.");


    }

    public void pizzaRevenue() {
        List<Pizza> allPizzas = dbMenuCardMapper.getAllPizzas();
        int pizzaCount = 0;
        int pizzaRevenue = 0;
        Date orderdate;
        int totalRevenue = 0;
        for (int i = 1; i < allPizzas.size(); i++) {
            Pizza pizza = dbMenuCardMapper.getPizzaById(i);
            String statement = "select * from mario.order where pizza_id = " + i;
            List<Order> selectedOrders = dbOrderMapper.getOrdersAsList(statement);
            for (Order o : selectedOrders) {
                pizzaCount = pizzaCount + o.getAmount();
            }
            pizzaRevenue = pizzaCount * pizza.getPrice();
            totalRevenue = totalRevenue + pizzaRevenue;
        }
        System.out.println("Total omsætning: " + totalRevenue + "kr.");


    }

    public void pizzaCountDate() {
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
        for (
                int i = 1; i < allPizzas.size(); i++) {
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

//    public void pizzaPopular(){
//
//
//
//    }


}