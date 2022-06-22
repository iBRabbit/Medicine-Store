import javax.swing.*;

public class LoginFrame {
    JFrame frame;

    LoginFrame(){
        this.initFrame();
    }
    public void initFrame(){
        this.frame = new JFrame("Login");
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(300, 300);
    }

    public void setVisible(){
        this.frame.setVisible(true);
    }
}
