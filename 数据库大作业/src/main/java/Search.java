import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search extends JDialog {

    private static final long serialVersionUID = 1L;
    JLabel l1=new JLabel("search information");
    JTextField t1=new JTextField(10);
    JButton b1=new JButton("search next");
    JButton b2=new JButton("quit");
    JTextArea a1=new JTextArea();
    public Search(){
        setTitle("search");
        setSize(300,200);
        setLayout(new FlowLayout());
        add(l1);
        add(t1);
        add(b1);
        add(b2);
        b2.addActionListener(new MyActionListener2());
    }

    void set(JTextArea n){
       a1 = n;
    }

    class MyActionListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e1) {
            setVisible(false);
        }

    }
}