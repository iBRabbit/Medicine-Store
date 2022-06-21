import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Data.Accounts;

public class Database {
    Connection connectionID;
    Statement statement;
    ResultSet result;

    public Vector <Accounts> loadAccountsData()  {
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
