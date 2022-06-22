import javax.swing.*;

public class LoginFrame extends JFrame {

    LoginFrame(){
        this.initFrame();
    }
    public void initFrame(){
        setTitle("Login");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
    }

    public void setVisible(){
        setVisible(true);
    }
}
