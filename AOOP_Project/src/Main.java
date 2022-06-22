import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import Data.Accounts;

public class Main implements ActionListener{


    JFrame mainFrame = new JFrame("AOOP Project");
    
    JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    JButton loginBtn = new JButton("Admin Login");

    JPanel containerLayout = new JPanel(new GridLayout(3,2));
    JLabel emptyLabel = new JLabel("");
    JPanel containerContent = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
    JButton inventoryMenuBtn = new JButton("Inventory");
    JButton onSellMenuBtn = new JButton("On Sell");
    JButton ordersMenuBtn = new JButton("Orders");

    JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel footerLabel = new JLabel("Created by : Group 4");

    JMenuBar menuBar = new JMenuBar();
    JMenu menuAdmin = new JMenu("Admin");
    JMenu menuHelp = new JMenu("Help");
    JMenuItem loginItem = new JMenuItem("Login");
    JMenuItem aboutItem = new JMenuItem("About");

    public Main() {

        Database db = new Database();
        db.createConnection();    

        Vector<Accounts> accounts = new Vector<>();
        accounts = db.loadAccountsData();

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 300);

        mainFrame.setJMenuBar(menuBar);
        menuAdmin.add(loginItem);
        menuHelp.add(aboutItem);

        menuBar.add(menuAdmin);
        menuBar.add(menuHelp);

        // header.add(loginBtn);
        
        containerContent.add(inventoryMenuBtn);
        containerContent.add(onSellMenuBtn);
        containerContent.add(ordersMenuBtn);
        containerLayout.add(emptyLabel);
        containerLayout.add(emptyLabel);
        containerLayout.add(containerContent);

        inventoryMenuBtn.addActionListener(this);

        footer.add(footerLabel);

        // mainFrame.add(header, BorderLayout.NORTH);
        mainFrame.add(containerLayout, BorderLayout.CENTER);
        mainFrame.add(footer, BorderLayout.SOUTH);
        mainFrame.setVisible(true);

    }

    public static void main(String[] args) throws Exception {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inventoryMenuBtn) 
            new InventoryFrame();

    }
}
