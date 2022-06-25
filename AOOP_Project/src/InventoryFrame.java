import javax.swing.*;
// import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import Classes.Inventory;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

public class InventoryFrame implements ActionListener{
    Database db = new Database();

    JFrame inventoryFrame = new JFrame("Inventory");

    Vector <Inventory> vInventory = new Vector<>();     
    Vector<String> header = new Vector<>();
    Vector<Vector <String>> values = new Vector<>();
    
    DefaultTableModel dtm = new DefaultTableModel();
    JTable table = new JTable();
    JPanel tablePanel = new JPanel();
    JLabel emptyLabel1 = new JLabel("");
    JLabel emptyLabel2 = new JLabel("");

    JPanel adminPanel = new JPanel(new GridLayout(1,3));
    
    JPanel insertPanel = new JPanel(new GridLayout(4,2));
    JLabel productNameLabel = new JLabel("Nama Produk ");
    JTextField productNameTextField = new JTextField();
    JLabel productPriceLabel = new JLabel("Harga Product ");
    JTextField productPriceTextField = new JTextField();
    JLabel productQuantityLabel = new JLabel("Jumlah Produk ");
    JTextField productQuantityTextField = new JTextField();

    JPanel removePanel = new JPanel(new GridLayout(2,2));
    JLabel removeLabel = new JLabel("Remove ID ");
    JTextField removeTextField = new JTextField();

    JButton submitButton = new JButton("Masukkan Produk");
    JButton removeButton = new JButton("Hapus Produk");

    JPanel updatePanel = new JPanel(new GridLayout(2,2));
    JLabel changeStatusLabel = new JLabel("Change status to for ID :");
    JTextField changeStatusTextField = new JTextField();
    JLabel emptyLabel3 = new JLabel("");
    JButton updateButton = new JButton("Update");

    public void loadInventoryData() {
        header.add("ID");
        header.add("Name");
        header.add("Price");
        header.add("Quantity");
        header.add("Status");

        for(Inventory i : vInventory) {
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
        vInventory = db.getInventoryData();
        loadInventoryData();

        productNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        productPriceLabel.setHorizontalAlignment(JLabel.RIGHT);
        productQuantityLabel.setHorizontalAlignment(JLabel.RIGHT);

        insertPanel.add(productNameLabel);
        insertPanel.add(productNameTextField);
        insertPanel.add(productPriceLabel);
        insertPanel.add(productPriceTextField);
        insertPanel.add(productQuantityLabel);
        insertPanel.add(productQuantityTextField);
        insertPanel.add(emptyLabel1);
        insertPanel.add(submitButton);

        submitButton.addActionListener(this);
    
        dtm.setDataVector(values, header);
        table.setModel(dtm);
        table.setEnabled(false);
        tablePanel.add(new JScrollPane(table));
        
        removeLabel.setHorizontalAlignment(JLabel.RIGHT);
        removePanel.add(removeLabel);
        removePanel.add(removeTextField);
        removePanel.add(emptyLabel2);
        removePanel.add(removeButton);

        removeButton.addActionListener(this);

        updatePanel.add(changeStatusLabel);
        updatePanel.add(changeStatusTextField);
        updatePanel.add(emptyLabel3);
        updatePanel.add(updateButton);

        updateButton.addActionListener(this);

        adminPanel.add(insertPanel);
        adminPanel.add(removePanel);
        adminPanel.add(updatePanel);
        

        inventoryFrame.setSize(500, 650);
        inventoryFrame.setLocationRelativeTo(null);

        inventoryFrame.add(tablePanel, BorderLayout.NORTH);
        inventoryFrame.add(adminPanel, BorderLayout.SOUTH);

        inventoryFrame.setVisible(true);
    }

    public InventoryFrame() {    
        generateLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == updateButton) {
            Integer id;

            try {
                id = Integer.parseInt(changeStatusTextField.getText());
            } catch (NumberFormatException nfe) {
                changeStatusTextField.setText("");
                JOptionPane.showMessageDialog(null, "Please enter a valid ID");
                return;
            }

            String query;
            query = "SELECT * FROM inventory WHERE inventoryID = " + id;

            try {
                db.result = db.statement.executeQuery(query);
                db.result.next();
                if(db.result.getInt(5) == 0) 
                    db.query("UPDATE inventory SET status = 1 WHERE inventoryID = " + id);
                else 
                    db.query("UPDATE inventory SET status = 0 WHERE inventoryID = " + id);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            inventoryFrame.dispose();
            new InventoryFrame();
        }

        if(e.getSource() == removeButton) {
            Integer id;

            try {
                id = Integer.parseInt(removeTextField.getText());
            } catch (NumberFormatException nfe) {
                removeTextField.setText("");
                JOptionPane.showMessageDialog(null, "Please enter a valid ID");
                return;
            }

            if(!db.isDataExistsOnInventory(id)) {
                JOptionPane.showMessageDialog(null, "ID you entered does not exist");
                return;
            }

            String query = "DELETE FROM inventory WHERE inventoryID = " + id;
            db.query(query);

            inventoryFrame.dispose();
            new InventoryFrame();
        }

        if(e.getSource() == submitButton) {
            String name;
            Integer price, quantity;

            try {
                name = productNameTextField.getText();
                price = Integer.parseInt(productPriceTextField.getText());
                quantity = Integer.parseInt(productQuantityTextField.getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter valid price or quantity");
                productPriceTextField.setText("");
                productQuantityTextField.setText("");
                return;
            }

            String query = "INSERT INTO `inventory` (`inventoryID`, `name`, `price`, `quantity`, `status`) VALUES (NULL, " + 
                            "'" + name + "', " +
                            "'" + price + "', " +
                            "'" + quantity + "', " +
                            "'0');";

            System.out.println("Query Success : " + query);
            db.query(query);

            inventoryFrame.dispose();
            new InventoryFrame();

        }

    }

}
