import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Classes.Accounts;
import Classes.Inventory;

public class Database {
    Connection connectionID;
    Statement statement;
    ResultSet result;

    public void query(String query) {
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInventoryQuantity(Integer qty, Integer id) {
        try {
            result = statement.executeQuery("UPDATE inventory SET quantity = "+qty+" WHERE inventoryID = "+id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector <Inventory> getOnSaleData() {
        Vector <Inventory> vInventory = new Vector <Inventory>();
        Integer dataSize = 0;
        try {
            result = statement.executeQuery("SELECT * FROM inventory WHERE status = 1");
            while(result.next()) {
                Inventory inv = new Inventory(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4), result.getInt(5));
                vInventory.add(inv);
                dataSize++;
            }

            System.out.println("Successfully loaded " + dataSize + " on sale data.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vInventory;
    }

    public String getProductNameByID(Integer ID) {
        String name="";
        try {
            result = statement.executeQuery("SELECT name FROM inventory WHERE inventoryID = "+ID);
            result.next();
            name = result.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public Vector <Inventory> getInventoryData() {
        Vector <Inventory> vInventory = new Vector <Inventory>();
        Integer dataSize = 0;
        try {
            result = statement.executeQuery("SELECT * FROM inventory");
            while(result.next()) {
                Inventory inv = new Inventory(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4), result.getInt(5));
                vInventory.add(inv);
                dataSize++;
            }

            System.out.println("Successfully loaded " + dataSize + " inventory data.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vInventory;
    }

    public Vector <Accounts> getAccountsData()  {
        Vector<Accounts> users = new Vector<Accounts>();

        try {
            result = statement.executeQuery("SELECT * FROM accounts");
            while(result.next()) {
                Accounts acc = new Accounts(result.getInt(1), result.getString(2), result.getString(3));
                users.add(acc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public Accounts login(String username, String password){
        try {
            result = statement.executeQuery(
                    "SELECT * FROM accounts where "
                            .concat("username=\"")
                            .concat(username)
                            .concat("\"")
                            .concat(" and ")
                            .concat("password=\"")
                            .concat(password)
                            .concat("\";")
            );
            if(result.next() == false) {
                return null;
            }else {
                Accounts acc = new Accounts(result.getInt(1), result.getString(2), result.getString(3));
                return acc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void createConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connectionID = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoopproject", "root", "");
            statement = connectionID.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
