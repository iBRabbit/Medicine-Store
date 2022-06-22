import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Classes.Inventory;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class InventoryFrame implements ActionListener{
    Database db = new Database();

    JFrame inventoryFrame = new JFrame("Inventory");

    Vector <Inventory> vInventory = new Vector<>();     
    Vector<String> header = new Vector<>();
    Vector<Vector> values = new Vector<>();
    
    DefaultTableModel dtm = new DefaultTableModel();
    JTable table = new JTable();
    JPanel tablePanel = new JPanel();
    JLabel emptyLabel = new JLabel("");

    JPanel insertPanel = new JPanel(new GridLayout(4,2));
    JLabel productNameLabel = new JLabel("Nama Produk");
    JTextField productNameTextField = new JTextField();
    JLabel productPriceLabel = new JLabel("Harga Product");
    JTextField productPriceTextField = new JTextField();
    JLabel productQuantityLabel = new JLabel("Jumlah Produk");
    JTextField productQuantityTextField = new JTextField();

    JButton submitButton = new JButton("Masukkan Produk ");

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

        insertPanel.add(productNameLabel);
        insertPanel.add(productNameTextField);
        insertPanel.add(productPriceLabel);
        insertPanel.add(productPriceTextField);
        insertPanel.add(productQuantityLabel);
        insertPanel.add(productQuantityTextField);
        insertPanel.add(emptyLabel);
        insertPanel.add(submitButton);

        submitButton.addActionListener(this);
    
        dtm.setDataVector(values, header);
        table.setModel(dtm);
        table.setEnabled(false);
        tablePanel.add(new JScrollPane(table));
        
        inventoryFrame.setSize(500, 600);
        inventoryFrame.setLocationRelativeTo(null);

        inventoryFrame.add(tablePanel, BorderLayout.NORTH);
        inventoryFrame.add(insertPanel, BorderLayout.SOUTH);

        inventoryFrame.setVisible(true);
    }

    public InventoryFrame() {    
        generateLayout();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
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
                            "' " + name + "', " +
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
