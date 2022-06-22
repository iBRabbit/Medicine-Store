import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener{

    public static Boolean isLogged = false;
    public static Integer accountID = -1;

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
    JMenu menuAdmin;
    JMenu menuHelp = new JMenu("Help");
    JMenuItem loginItem;
    JMenuItem aboutItem = new JMenuItem("About");

    LoginFrame loginFrame;

    public static Boolean getIsLogged() {
        return isLogged;
    }
    public static void setIsLogged(Boolean isLogged) {
        MainFrame.isLogged = isLogged;
    }
    public static Integer getAccountID() {
        return accountID;
    }
    public static void setAccountID(Integer accountID) {
        MainFrame.accountID = accountID;
    }

    void setMenuAdmin(Database db){
        this.menuAdmin =  new JMenu("Admin");
        this.loginItem = new JMenuItem("Login");
        this.loginItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame = new LoginFrame(db);
                
                if(isLogged) {
                    JOptionPane.showMessageDialog(null, "You are already logged in.");
                    return;
                }

                loginFrame.setVisible(true);
            }
        });

    }
    public MainFrame() {

        Database db = new Database();
        db.createConnection();

        setTitle("AOOP Project");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);

        setMenuAdmin(db);
        setJMenuBar(menuBar);

        menuAdmin.add(loginItem);
        menuHelp.add(aboutItem);

        menuBar.add(menuAdmin);
        menuBar.add(menuHelp);

        containerContent.add(inventoryMenuBtn);
        containerContent.add(onSellMenuBtn);
        containerContent.add(ordersMenuBtn);
        containerLayout.add(emptyLabel);
        containerLayout.add(emptyLabel);
        containerLayout.add(containerContent);

        inventoryMenuBtn.addActionListener(this);

        footer.add(footerLabel);

        add(containerLayout, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inventoryMenuBtn) {
            if(!isLogged) {
                JOptionPane.showMessageDialog(null, "You haven't login as an admin yet.");
                return;
            }
            
            new InventoryFrame();
        }
            
    }
}
