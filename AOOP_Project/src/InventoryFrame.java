import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
// import java.awt.event.*;
import java.util.Vector;

import Data.Inventory;

public class InventoryFrame {
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

}
