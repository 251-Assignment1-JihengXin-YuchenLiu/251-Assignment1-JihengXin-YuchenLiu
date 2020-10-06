import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Change extends JDialog{

    private static final long serialVersionUID = 1L;
    JLabel l1=new JLabel("What do you want to search");
    JLabel l2=new JLabel("what do you want to replace");
    JTextField t1=new JTextField(10);
    JTextField t2=new JTextField(10);
    JButton b1=new JButton("replace");
    JButton b2=new JButton("all replace");
    JButton b3=new JButton("quit");
    JTextArea a1=new JTextArea();
    //int m;
    void set(JTextArea n){
        a1=n;
    }
    public Change(){
        setTitle("replace");
        setSize(500,300);
        setLocation(200,300);
        setLayout(new FlowLayout());
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b1);
        add(b2);
        add(b3);
        b1.addActionListener(new MyActionListener3());
        b2.addActionListener(new MyActionListener3());
        b3.addActionListener(new MyActionListener3());
    }
    class MyActionListener3 implements ActionListener{

        public void actionPerformed(ActionEvent e2) {
            int m;
            String source=a1.getText();
            String find=t1.getText();
            String change=t2.getText();
            if(e2.getActionCommand()=="quit"){
                setVisible(false);
                t1.setText("");
                t2.setText("");
            }
            if(e2.getActionCommand()=="replace"){
                m=source.indexOf(find,0);
                String s1=source.substring(0,m);
                String s2=source.substring(m+find.length());
                source=s1+change+s2;
                if(m==-1){
                    JOptionPane.showMessageDialog(null,"Sorry,we can't search that！");
                }else{
                    a1.setText(source);
                }
            }
            if(e2.getActionCommand()=="all replace"){
                m=-change.length();
                while(true){
                    m=source.indexOf(find,m+change.length());
                    if(m==-1)
                        break;
                    else{
                        String s1=source.substring(0,m);
                        String s2=source.substring(m+find.length());
                        source=s1+change+s2;
                    }
                }
                a1.setText(source);
            }

        }
    }

}
