package ui;

import domain.Order;
import domain.Pizza;
import persistence.Database;
import persistence.DbMenuCardMapper;
import persistence.DbOrderMapper;

import java.util.Date;
import java.util.List;


public class Statistik {

    private DbMenuCardMapper dbMenuCardMapper;
    private DbOrderMapper dbOrderMapper;

    private List<Pizza> pizzaScores;


    public Statistik(Database database) {
        this.dbOrderMapper = new DbOrderMapper(database);
        this.dbMenuCardMapper = new DbMenuCardMapper(database);

    }


    public void showOrdersByTime() {
//        en liste der viser get all orders sorteret


    }

    public void pizzaCount() {
        List<Pizza> allPizzas = dbMenuCardMapper.getAllPizzas();
        int pizzaCount = 0;
        int pizzaRevenue = 0;
        Date orderdate;
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

        }
int totalRevenue= pizzaRevenue + pizzaRevenue;
        System.out.println(totalRevenue);


//        for (Pizza pizza : allPizzas
//        ) {
//            for (Order order : allOrders
//            ) {
//                if (pizza.getPizzaId() == order.getPizzaId()) {
////                    når der er dette sammentræf skal den tælle amount op for den pågældende pizza.
////                    den sum der opstår ved gennemløb af ordre skal gemmes sammen med pizza id. så vi ender med en kobling af pizza-id og en sum.
//
//
////                    order.getAmount();

////                    den skal gemme mængde sammen med pizzaid - det kunne man måske godt. lave en arrayliste arrangeret efter pizza-id.
////                    men så skal det være med kun pizza og mængde. og det har vi ikke en klasse der hedder. (måske med en constructor).
////                    nej for mængde er ikke en attribut ved pizza.
////                    så skulle det være udfra ordre-klassen.


//                    public static void averagePizzaPrice(ArrayList<Pizza> pizzaListe) {
//                        int tmp = 0;
//                        for (int i = 0; i < pizzaListe.size(); i++) {
//                            tmp = Integer.parseInt(pizzaListe.get(i).getPrice());
//                            tmp = tmp + tmp * i;
//                        }
//                        int divideBy = pizzaListe.size();
//                        int finalPrice = (tmp - divideBy) / divideBy;
//                        System.out.println(finalPrice);
    }

//            }
//
//                    }

//    Map<Order, Integer> optælling = new HashMap<>();
//
//        for (Order order : dbOrderMapper) {
//
//        if (optælling.containsKey(s)) {
//            Integer i = optælling.get(s);
//            optælling.put(s, i+ 1);
//        }
//        else {
//            optælling.put(s,1);
//        }
//    }
//
//        System.out.println(optælling);


//                    }
//                            }


    }
