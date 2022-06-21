import javax.swing.*;
import javax.swing.table.DefaultTableModel;
// import java.awt.*;
// import java.awt.event.*;
import java.util.Vector;


public class InventoryFrame {
    JFrame inventoryFrame = new JFrame("Inventory");
    Vector<String> header = new Vector<>();
    Vector<Vector> values = new Vector<>();

    DefaultTableModel dtm = new DefaultTableModel();
    JTable table = new JTable();
    JPanel tablePanel = new JPanel();

    public InventoryFrame() {    
    
        header.add("ID");
        header.add("Name");
        header.add("Price");
        header.add("Quantity");
        header.add("Status");
        header.add("Action");

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

        dtm.setDataVector(values, header);
        table.setModel(dtm);
        table.setEnabled(false);
        tablePanel.add(new JScrollPane(table));
        
        inventoryFrame.setSize(500, 500);
        inventoryFrame.setLocationRelativeTo(null);

        inventoryFrame.add(tablePanel);
        
        inventoryFrame.setVisible(true);
    }

}
