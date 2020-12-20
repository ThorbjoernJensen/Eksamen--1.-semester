package domain;

import java.sql.Timestamp;
import java.util.Date;

public class Order {

    private int orderNr;
    private int pizzaId;
    private int amount;
    private int pickuptime;
    private Timestamp ordertime;
    private Date date;
    private String custemorName;
    private String phone;
    private boolean remove;

    public Order(int pizzaId, int amount) {
        this.pizzaId = pizzaId;
        this.amount = amount;
        remove = false;

    }
    public Order(int orderNr, int pizzaId, int amount, int pickuptime, Timestamp ordertime, Date date, String custemorName, String phone) {
        this.orderNr = orderNr;
        this.pizzaId = pizzaId;
        this.amount = amount;
        this.pickuptime = pickuptime;
        this.ordertime = ordertime;
        this.date = date;
        this.custemorName = custemorName;
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public Order(int pizzaId, int amount, int pickuptime, String custemorName, String phone) {
        this.pizzaId = pizzaId;
        this.amount = amount;
        this.pickuptime = pickuptime;
        this.custemorName = custemorName;
        this.phone = phone;
    }

    public Order(int pizzaId, int amount, int pickuptime, Timestamp ordertime, String custemorName, String phone, boolean remove) {
        this.pizzaId = pizzaId;
        this.amount = amount;
        this.pickuptime = pickuptime;
        this.ordertime = ordertime;
        this.custemorName = custemorName;
        this.phone = phone;
        this.remove = remove;
    }

    public Order(int order_nr, int pizza_id, int amount, int pickup_time, Timestamp order_time, String custemor_name, String phone, boolean remove) {
    }

    public int getOrderNr() {
        return orderNr;
    }

    public void setOrderNr(int orderNr) {
        this.orderNr = orderNr;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPickuptime() {
        return pickuptime;
    }

    public void setPickuptime(int pickuptime) {
        this.pickuptime = pickuptime;
    }

    public Timestamp getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Timestamp ordertime) {
        this.ordertime = ordertime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustemorName() {
        return custemorName;
    }

    public void setCustemorName(String custemorName) {
        this.custemorName = custemorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderNr=" + orderNr +
                ", pizzaId=" + pizzaId +
                ", amount=" + amount +
                ", pickuptime=" + pickuptime +
                ", ordertime=" + ordertime +
                ", date=" + date +
                ", custemorName='" + custemorName + '\'' +
                ", phone='" + phone + '\'' +
                '}'+"\n";
    }
}