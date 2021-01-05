package persistence;

import domain.Pizza;
import exceptionHandling.ExceptionHandling;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbMenuCardMapper {

    private Database database;

    public DbMenuCardMapper(Database database) {
        this.database = database;
    }

    public List<Pizza> getAllPizzas() throws Exception {

        List<Pizza> pizzaList = new ArrayList<>();

        String sql = "select * from pizza";

        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pizza_id = rs.getInt("pizza_i");
                int pizza_no = rs.getInt("pizza_no");
                String name = rs.getString("name");
                String ingredients = rs.getString("ingredients");
                int price = rs.getInt("price");
                pizzaList.add(new Pizza(pizza_id, pizza_no, name, ingredients, price));
            }

        } catch (Exception e) {
            throw new ExceptionHandling(e);
        }
        return pizzaList;
    }

    public Pizza getPizzaByNo(int pizzaNo) throws Exception {
        Pizza pizza = null;
        String sql = "select * from pizza where pizza_no = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, pizzaNo);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int pizza_id = rs.getInt("pizza_id");
                    int pizza_no = rs.getInt("pizza_no");
                    String name = rs.getString("name");
                    String ingredients = rs.getString("ingredients");
                    int price = rs.getInt("price");
                    pizza = new Pizza(pizza_id, pizza_no, name, ingredients, price);
                }
            } catch (Exception e) {
                throw new ExceptionHandling(e);
            }
        } catch (Exception e) {
            throw new ExceptionHandling(e);
        }
        return pizza;
    }


    public Pizza getPizzaById(int pizzaNo) throws Exception {
        Pizza pizza = null;
        String sql = "select * from pizza where pizza_id = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, pizzaNo);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int pizza_id = rs.getInt("pizza_id");
                    int pizza_no = rs.getInt("pizza_no");
                    String name = rs.getString("name");
                    String ingredients = rs.getString("ingredients");
                    int price = rs.getInt("price");
                    pizza = new Pizza(pizza_id, pizza_no, name, ingredients, price);
                }
            } catch (Exception e) {
                throw new ExceptionHandling(e);
            }
        } catch (Exception e) {
            throw new ExceptionHandling(e);
        }
        return pizza;
    }

    public boolean deletePizza(int pizzaNo) throws Exception {
        boolean result = false;
        String sql = "delete from pizza where pizza_no = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, pizzaNo);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
            } catch (Exception e) {
                throw new ExceptionHandling(e);
            }
        } catch (Exception e) {
            throw new ExceptionHandling(e);
        }
        return result;
    }

    public Pizza insertPizza(Pizza pizza) throws Exception {
        boolean result = false;
        int newId = 0;
        String sql = "insert into pizza (pizza_no, name, ingredients, price) values (?,?,?,?)";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, pizza.getPizzaNo());
                ps.setString(2, pizza.getName());
                ps.setString(3, pizza.getIngredients());
                ps.setInt(4, pizza.getPrice());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
                ResultSet idResultset = ps.getGeneratedKeys();
                if (idResultset.next()) {
                    newId = idResultset.getInt(1);
                    pizza.setPizzaId(newId);
                } else {
                    pizza = null;
                }
            } catch (Exception e) {
                throw new ExceptionHandling(e);
            }
        } catch (Exception e) {
            throw new ExceptionHandling(e);
        }
        return pizza;
    }

    public boolean updatePizza(Pizza pizza) throws Exception {
        boolean result = false;
        String sql = "update mario.pizza set pizza_no = ?, name = ?, ingredients = ?, price = ? where pizza_id = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, pizza.getPizzaNo());
                ps.setString(2, pizza.getName());
                ps.setString(3, pizza.getIngredients());
                ps.setInt(4, pizza.getPrice());
                ps.setInt(5, pizza.getPizzaId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
            } catch (Exception e) {
                throw new ExceptionHandling(e);
            }
        } catch (Exception e) {
            throw new ExceptionHandling(e);
        }
        return result;
    }
}
