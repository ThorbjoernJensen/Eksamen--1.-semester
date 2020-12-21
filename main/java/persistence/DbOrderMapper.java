package persistence;


import domain.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbOrderMapper {

    private Database database;

    private int orderNr = 0;

    public int getOrderNr() {
        return orderNr;
    }

    public void setOrderNr(int orderNr) {
        this.orderNr = orderNr;
    }

    public DbOrderMapper(Database database) {
        this.database = database;
    }

    public DbOrderMapper() {

    }

    public List<Order> getOrdersAsList(String sqlargument) {
        List<Order> orderList = new ArrayList<>();

        //        String sql = "select * from mario.order order by date DESC, pickup_time ASC";
        String sql = sqlargument;
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int order_nr = rs.getInt("order_nr");
                    int pizza_id = rs.getInt("pizza_id");
                    int amount = rs.getInt("amount");
                    int pickup_time = rs.getInt("pickup_time");
                    Timestamp order_time = rs.getTimestamp("order_time");
                    Date date = rs.getDate("date");
                    String custemor_name = rs.getString("custemor_name");
                    String phone = rs.getString("phone");
//                    int price = rs.getInt("price");
                    orderList.add(new Order(order_nr, pizza_id, amount, pickup_time, order_time, date, custemor_name, phone));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orderList;
    }

    public Order insertOrder(Order order) {
        boolean result = false;
        int newId = 0;

//
        String sql = "INSERT INTO mario.order ( pizza_id, amount, pickup_time, order_time, date, custemor_name, phone, remove) values (?, ?, ?,NOW(), CURDATE(),?,?,?)";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order.getPizzaId());
                ps.setInt(2, order.getAmount());
                ps.setInt(3, order.getPickuptime());
                ps.setString(4, order.getCustemorName());
                ps.setString(5, order.getPhone());
                ps.setBoolean(6, order.isRemove());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }

                ResultSet idResultset = ps.getGeneratedKeys();
                if (idResultset.next()) {
                    newId = idResultset.getInt(1);
                    order.setOrderNr(newId);
                } else {
                    order = null;
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            // TODO: Make own throwable exception and let it bubble upwards
            throwables.printStackTrace();
        }
        return order;
    }
//    public Order (int pizzaNo) {
//        Pizza pizza = null;
//        String sql = "select * from pizza where pizza_no = ?";
//        try (Connection connection = database.connect()) {
//            try (PreparedStatement ps = connection.prepareStatement(sql)) {
//                ps.setInt(1, pizzaNo);
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//                    int pizza_id = rs.getInt("pizza_id");
//                    int pizza_no = rs.getInt("pizza_no");
//                    String name = rs.getString("name");
//                    String ingredients = rs.getString("ingredients");
//                    int price = rs.getInt("price");
//                    pizza = new Pizza(pizza_id, pizza_no, name, ingredients, price);
//                }
//            } catch (SQLException throwables) {
//                // TODO: Make own throwable exception and let it bubble upwards
//                throwables.printStackTrace();
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return pizza;
//    }
//
//    public boolean deletePizza(int pizzaNo) {
//        boolean result = false;
//        String sql = "delete from pizza where pizza_no = ?";
//        try (Connection connection = database.connect()) {
//            try (PreparedStatement ps = connection.prepareStatement(sql)) {
//                ps.setInt(1, pizzaNo);
//                int rowsAffected = ps.executeUpdate();
//                if (rowsAffected == 1) {
//                    result = true;
//                }
//            } catch (SQLException throwables) {
//                // TODO: Make own throwable exception and let it bubble upwards
//                throwables.printStackTrace();
//            }
//        } catch (SQLException throwables) {
//            // TODO: Make own throwable exception and let it bubble upwards
//            throwables.printStackTrace();
//        }
//        return result;
//    }
//
//    public Pizza insertPizza(Pizza pizza) {
//        boolean result = false;
//        int newId = 0;
//        String sql = "insert into pizza (pizza_no, name, ingredients, price) values (?,?,?,?)";
//        try (Connection connection = database.connect()) {
//            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//                ps.setInt(1, pizza.getPizzaNo());
//                ps.setString(2, pizza.getName());
//                ps.setString(3, pizza.getIngredients());
//                ps.setInt(4, pizza.getPrice());
//                int rowsAffected = ps.executeUpdate();
//                if (rowsAffected == 1) {
//                    result = true;
//                }
//                ResultSet idResultset = ps.getGeneratedKeys();
//                if (idResultset.next()) {
//                    newId = idResultset.getInt(1);
//                    pizza.setPizzaId(newId);
//                } else {
//                    pizza = null;
//                }
//            } catch (SQLException throwables) {
//                // TODO: Make own throwable exception and let it bubble upwards
//                throwables.printStackTrace();
//            }
//        } catch (SQLException throwables) {
//            // TODO: Make own throwable exception and let it bubble upwards
//            throwables.printStackTrace();
//        }
//        return pizza;
//    }
//

    public boolean updateOrder(Order order) {
        boolean result = false;
        String sql = "update mario.order set order_nr = ?, pizza_id = ?, amount = ?, pickup_time = ?, custemor_name = ?, phone = ? where order_nr = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order.getOrderNr());
                ps.setInt(2, order.getPizzaId());
                ps.setInt(3, order.getAmount());
                ps.setInt(4, order.getPickuptime());
                ps.setString(5, order.getCustemorName());
                ps.setString(6, order.getPhone());
                ps.setInt(7, order.getOrderNr());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            // TODO: Make own throwable exception and let it bubble upwards
            throwables.printStackTrace();
        }
        return result;
    }

    public Order getOrderById(int order_nr) {
        Order order = null;
        String sql = "select * from mario.order where order_nr = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order_nr);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int orderNr = rs.getInt("order_nr");
                    int pizza_id = rs.getInt("pizza_id");
                    int amount = rs.getInt("amount");
                    int pickup_time = rs.getInt("pickup_time");
                    Timestamp order_time = rs.getTimestamp("order_time");
                    Date date = rs.getDate("date");
                    String custemor_name = rs.getString("custemor_name");
                    String phone = rs.getString("phone");
                    boolean remove = rs.getBoolean("remove");
                    order = new Order(orderNr, pizza_id, amount, pickup_time, order_time, date, custemor_name, phone);
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return order;
    }

    public boolean deleteOrder(int orderNo) {
        boolean result = false;
        String sql = "delete from mario.order where order_nr= ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderNo);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            // TODO: Make own throwable exception and let it bubble upwards
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean setOrderAsDone() {
        boolean remove = false;
        færdigeOrdre();


        String sql = "update mario.order SET remove = true where order_nr=?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                setOrderNr(færdigeOrdre());
                ps.setInt(1, færdigeOrdre());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    remove = true;

                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            // TODO: Make own throwable exception and let it bubble upwards
            throwables.printStackTrace();
        }

        return remove;
    }

    public int færdigeOrdre() {

        List<Order> færdigeordre = getOrdersAsList("select * from mario.order where remove = 'false' order by date ASC, pickup_time ASC");
        int orderNo = færdigeordre.get(0).getOrderNr();

        return orderNo;

    }
}

