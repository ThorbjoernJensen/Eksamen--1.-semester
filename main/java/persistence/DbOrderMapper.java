package persistence;

import domain.Order;
import exceptionHandling.ExceptionHandling;
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

    public List<Order> getOrdersAsList(String sqlargument) throws Exception {
        List<Order> orderList = new ArrayList<>();
        String sql = sqlargument;
//        String sql = "drop table order";

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
                    orderList.add(new Order(order_nr, pizza_id, amount, pickup_time, order_time, date, custemor_name, phone));
                }
            } catch (Exception e) {
                throw new ExceptionHandling(e);
            }
        } catch (Exception e) {
            throw new ExceptionHandling(e);
        }
        return orderList;
    }

    public Order insertOrder(Order order) throws Exception {
        boolean result = false;
        int newId = 0;
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
            } catch (SQLException e) {
                throw new ExceptionHandling(e);
            }
        } catch (SQLException e) {
            throw new ExceptionHandling(e);
        }
        return order;
    }

    public boolean updateOrder(Order order) throws Exception {
        boolean result = false;
        String sql = "update mario.order set pizza_id = ?, amount = ?, pickup_time = ?, custemor_name = ?, phone = ? where order_nr = ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order.getPizzaId());
                ps.setInt(2, order.getAmount());
                ps.setInt(3, order.getPickuptime());
                ps.setString(4, order.getCustemorName());
                ps.setString(5, order.getPhone());
                ps.setInt(6, order.getOrderNr());
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

    public Order getOrderById(int order_nr) throws Exception {
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
                    order = new Order(orderNr, pizza_id, amount, pickup_time, order_time, date, custemor_name, phone);
                }
            } catch (Exception e) {
                throw new ExceptionHandling(e);
            }
        } catch (Exception e) {
          throw new ExceptionHandling(e);
        }
        return order;
    }

    public boolean deleteOrder(int orderNo) throws Exception {
        boolean result = false;
        String sql = "delete from mario.order where order_nr= ?";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderNo);
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

    public boolean setOrderAsDone() throws Exception {
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
            } catch (Exception e) {
                throw new ExceptionHandling(e);
            }
        } catch (Exception e) {
            throw new ExceptionHandling(e);
        }

        return remove;
    }

    public int færdigeOrdre() throws Exception {
        List<Order> færdigeordre = getOrdersAsList("select * from mario.order where remove = 'false' order by date ASC, pickup_time ASC");
        int orderNo = færdigeordre.get(0).getOrderNr();
        return orderNo;

    }
}

