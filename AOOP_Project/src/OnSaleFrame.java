import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Classes.Inventory;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class OnSaleFrame implements ActionListener{
    Database db = new Database();

    JFrame onSaleFrame = new JFrame("On Sale");

    Vector <Inventory> vOnSale = new Vector<>();     
    Vector <Inventory> vInventory = new Vector<>();
    Vector <String> header = new Vector<>();
    Vector <Vector <String>> values = new Vector<>();

    DefaultTableModel dtm = new DefaultTableModel();
    JTable table = new JTable();
    JPanel tablePanel = new JPanel();
    JLabel emptyLabel1 = new JLabel("");
    JLabel emptyLabel2 = new JLabel("");

    JPanel adminPanel = new JPanel(new GridLayout(1,3));

    JPanel insertPanel = new JPanel(new GridLayout(5,2));
    JLabel usernameLabel = new JLabel("Nama Pembeli : ");
    JTextField usernameTextField = new JTextField();
    JLabel productIDLabel = new JLabel("ID Barang yang mau dibeli ");
    JTextField productIDTextField = new JTextField();
    JLabel userAddressLabel = new JLabel("Alamat");
    JTextField userAddressTextField = new JTextField();
    JLabel userQuantityLabel = new JLabel("Quantity");
    JTextField userQuantityTextField = new JTextField();
    JButton buyButton = new JButton("Buy");

    public void loadOnSaleData() {
        header.add("ID");
        header.add("Name");
        header.add("Price");
        header.add("Quantity");
        header.add("Status");

        for(Inventory i : vOnSale) {
            Vector <String> temp = new Vector<>();
            temp.add(i.getInventoryID().toString());
            temp.add(i.getName());
            temp.add(i.getPrice().toString());
            temp.add(i.getQuantity().toString());
            temp.add(i.getStatusName());

            values.add(temp);
        }

    }

    void generateLayout() {
        db.createConnection();
        vOnSale = db.getOnSaleData();
        vInventory = db.getInventoryData();
        loadOnSaleData();
    
        dtm.setDataVector(values, header);
        table.setModel(dtm);
        table.setEnabled(false);
        tablePanel.add(new JScrollPane(table));
        
        insertPanel.add(usernameLabel);
        insertPanel.add(usernameTextField);
        insertPanel.add(productIDLabel);
        insertPanel.add(productIDTextField);
        insertPanel.add(userAddressLabel);
        insertPanel.add(userAddressTextField);
        insertPanel.add(userQuantityLabel);
        insertPanel.add(userQuantityTextField);
        insertPanel.add(buyButton);

        adminPanel.add(insertPanel);

        buyButton.addActionListener(this);

        onSaleFrame.setSize(500, 650);
        onSaleFrame.setLocationRelativeTo(null);

        onSaleFrame.add(tablePanel, BorderLayout.NORTH);
        onSaleFrame.add(adminPanel, BorderLayout.SOUTH);

        onSaleFrame.setVisible(true);
    }

    public OnSaleFrame() {
        generateLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buyButton) {
            String username, address;
            Integer quantity, inventoryID;

            try {
                username = usernameTextField.getText();
                address = userAddressTextField.getText();
                quantity = Integer.parseInt(userQuantityTextField.getText());
                inventoryID = Integer.parseInt(productIDTextField.getText());
            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "All text must not empty. Qty and id must be number");
                return;
            }

            if(!db.isDataExistsOnSale(inventoryID)) {
                JOptionPane.showMessageDialog(null, "Data you entered is not exists.");
                return;
            }

            if(quantity <= 0) {
                JOptionPane.showMessageDialog(null, "Your quantity must be greater than 0.");
                return;
            }

            if(vInventory.get(db.getInventoryVectorIndexByID(inventoryID)).getQuantity() - quantity < 0) {
                JOptionPane.showMessageDialog(null, "A quantity must not greater than our stocks.");
                return;
            }

            String query = "INSERT INTO `orders` (`orderID`, `inventoryID`, `name`, `orderedBy`, `address`, `quantity`) VALUES (NULL, " + 
            "'" + inventoryID + "', " +
            "'" + db.getProductNameByID(inventoryID) + "', " +
            "'" + username + "', " +
            "'" + address + "'," + 
            "'" + quantity + "');";

            db.setInventoryQuantity(vInventory.get(db.getInventoryVectorIndexByID(inventoryID)).getQuantity() - quantity, inventoryID);

            if(vInventory.get(db.getInventoryVectorIndexByID(inventoryID)).getQuantity() - quantity <= 0) 
                db.query("UPDATE inventory SET status = 0 WHERE inventoryID = " + inventoryID);
                
            System.out.println("Query Success : " + query);
            db.query(query);

            onSaleFrame.dispose();
            new OnSaleFrame();
        }
        
    }
}
