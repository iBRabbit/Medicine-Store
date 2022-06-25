package Classes;

import java.util.*;

public class Inventory {

    private Integer inventoryID;
    private String name;
    private Integer price;
    private Integer quantity;
    private Integer status;

    public Inventory(Integer inventoryID, String name, Integer price, Integer quantity, Integer status) {
        this.inventoryID = inventoryID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }


    public String getStatusName() {
        return (this.status == 0) ? "Not Sale" : "On Sale";
    }

    public Integer getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(Integer inventoryID) {
        this.inventoryID = inventoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    

}
