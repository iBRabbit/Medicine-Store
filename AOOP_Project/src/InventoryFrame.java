import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.*;
// import java.awt.event.*;
import java.util.Vector;


public class InventoryFrame {
    JFrame inventoryFrame = new JFrame("Inventory");
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

    JButton submitButton = new JButton("Masukkan Produk (Admin)");
    
    public InventoryFrame() {    
    
        header.add("ID");
        header.add("Name");
        header.add("Price");
        header.add("Quantity");
        header.add("Status");
        
        Vector<String> testData1 = new Vector<>();
        testData1.add("001");
        testData1.add("Lalala");
        testData1.add("$15.000");
        testData1.add("15");
        testData1.add("Pending");

        Vector<String> testData2 = new Vector<>();
        testData2.add("002");
        testData2.add("Yeyeye");
        testData2.add("$30.000");
        testData2.add("10");
        testData2.add("Pending");
        values.add(testData1);
        values.add(testData2);

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

}
