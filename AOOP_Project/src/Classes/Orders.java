package Classes;

public class Orders {
    private Integer orderID;
    private String orderedBy;
    private String address;
    private Integer quantity;
    private Integer price;

    public Orders(Integer orderID, String orderedBy, String address, Integer quantity, Integer price) {
        this.orderID = orderID;
        this.orderedBy = orderedBy;
        this.address = address;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
