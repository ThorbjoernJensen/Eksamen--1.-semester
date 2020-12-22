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
    private String stringPickUpTime;

    public String stringPickUpTime(){
        String token = Integer.toString(getPickuptime());
        token = new StringBuilder(token).insert(token.length()-2, ":").toString();
     return token;
    }

    public void setStringPickUpTime(String stringPickUpTime) {
        this.stringPickUpTime = stringPickUpTime;
    }

    public String getStringPickUpTime() {
        return stringPickUpTime();
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