import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Vector;

import Classes.Orders;

public class OrdersFrame {

    Database db = new Database();

    JFrame ordersFrame = new JFrame("Orders");

    Vector <Orders> vOrders = new Vector<>();
    Vector <String> header = new Vector<>();
    Vector <Vector <String>> values = new Vector<>();

    DefaultTableModel dtm = new DefaultTableModel();
    JTable table = new JTable();
    JPanel tablePanel = new JPanel();
    JLabel emptyLabel1 = new JLabel("");
    JLabel emptyLabel2 = new JLabel("");

    public void loadOrdersData() {
        header.add("ID");
        header.add("Ordered by");
        header.add("Quantity");
        header.add("Address");
        header.add("Total Price");

        for(Orders i : vOrders) {
            Vector <String> temp = new Vector<>();
            temp.add(i.getOrderID().toString());
            temp.add(i.getOrderedBy());
            temp.add(i.getQuantity().toString());
            temp.add(i.getAddress().toString());
            temp.add(i.getPrice().toString());

            values.add(temp);
        }

    }

    void generateLayout() {
        db.createConnection();
        vOrders = db.getOrdersData();
        loadOrdersData();
    
        dtm.setDataVector(values, header);
        table.setModel(dtm);
        table.setEnabled(false);
        tablePanel.add(new JScrollPane(table));
        

        ordersFrame.setSize(500, 650);
        ordersFrame.setLocationRelativeTo(null);

        ordersFrame.add(tablePanel, BorderLayout.NORTH);

        ordersFrame.setVisible(true);
    }

    public OrdersFrame () {
        generateLayout();
    }
}
