import Data.Accounts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    JTextField  textField1, textField2;
    Database db;

    Accounts accounts;

    LoginFrame(Database db){
        createLayout();
        initFrame();
        accounts = null;
        this.db = db;
    }
    void createLayout(){
        userLabel = new JLabel();
        userLabel.setText("Username");

        textField1 = new JTextField(15);

        passLabel = new JLabel();
        passLabel.setText("Password");

        textField2 = new JPasswordField(15);    //set length for the password

        b1 = new JButton("SUBMIT"); //set label to button

        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);

        add(newPanel, BorderLayout.CENTER);

        b1.addActionListener(this);
        setTitle("LOGIN FORM");
    }
    public void initFrame(){
        setTitle("Login");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        accounts = this.db.login(userValue, passValue);
        if (accounts != null) {
            JOptionPane.showMessageDialog(null, "Login Success, welcome ".concat(accounts.getUsername()));
        }
        else{
            JOptionPane.showMessageDialog(null, "Please enter valid username and password");
        }
    }
}
