import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class Assignment1 extends JFrame implements DocumentListener {

    private static final long serialVersionUID = 1L;
    JMenuBar menubar=new JMenuBar();
    JMenu file=new JMenu("File");
    JMenu look =new JMenu("Search");
    JMenu view=new JMenu("View");
    JMenu help =new JMenu("Help");
    JTextArea wordarea=new JTextArea();
    JScrollPane imgScrollPane = new JScrollPane(wordarea);
    String [] str1={"New","Open","Save","Print","Exit"};
    String [] str2={"Cut","Copy","Paste","Search","Cut(SCPC)","T&D"};
    String [] str3={"About"};
    Search d1=new Search();
    Change c1=new Change();
    int flag=0;
    String source="";
    public static void main(String[] args) {
        JFrame Assignmet1=new Assignment1();
        Assignmet1.setVisible(true);
    }
    public Assignment1(){
        c1.set(wordarea);
        setTitle("Test Editor");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width/2,screenSize.height/2);
        setLocation(screenSize.width/4,screenSize.height/4);
        add(imgScrollPane,BorderLayout.CENTER);
        setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(look);
        menubar.add(view);
        menubar.add(help);
        wordarea.getDocument().addDocumentListener(this);
        for(int i=0;i<str1.length;i++){
            JMenuItem item1= new JMenuItem(str1[i]);
            item1.addActionListener(new MyActionListener1());
            file.add(item1);
        }
        for(int i=0;i<str2.length;i++){
            JMenuItem item2= new JMenuItem(str2[i]);
            item2.addActionListener(new MyActionListener1());
        }
        for(int i=0;i<str3.length;i++){
            JMenuItem item3= new JMenuItem(str3[i]);
            item3.addActionListener(new MyActionListener1());
            file.add(item3);
        }
    }
    public void changedUpdate(DocumentEvent e) {
        flag=1;
    }

    public void insertUpdate(DocumentEvent e) {
        flag=1;
    }
    public void removeUpdate(DocumentEvent e) {
        flag=1;
    }
    public void display(DocumentEvent e) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    void open(){
        FileDialog  filedialog=new FileDialog(this,"打开",FileDialog.LOAD);
        filedialog.setVisible(true);
        String path=filedialog.getDirectory();
        String name=filedialog.getFile();
        if(path!=null && name!=null){
            FileInputStream file = null;
            try {
                file = new FileInputStream(path+name);
            } catch (FileNotFoundException e) {

            }
            InputStreamReader put =new InputStreamReader(file);
            BufferedReader in=new BufferedReader(put);
            String temp="";
            String now = null;
            try {
                now = (String)in.readLine();
            } catch (IOException e) {

                e.printStackTrace();
            }
            while(now!=null){
                temp+=now+"\r\n";
                try {
                    now=(String)in.readLine();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }

            wordarea.setText(temp);
        }

    }

    void save(){
        FileDialog  filedialog=new FileDialog(this,"Save",FileDialog.SAVE);
        filedialog.setVisible(true);
        if(filedialog.getDirectory()!=null && filedialog.getFile()!=null){
            OutputStreamWriter out = null;
            try {
                out = new OutputStreamWriter(new FileOutputStream(filedialog.getDirectory()+filedialog.getFile()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                out.write(wordarea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag=0;
            try {
                out.close();
                source=wordarea.getText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    void newfile(){
        if(flag==0){
            wordarea.setText("");
        }
        if(flag==1){
            int m=  JOptionPane.showConfirmDialog(this,"Should you want to save the file");
            if(m==0){
                save();
                wordarea.setText("");
            }

            if(m==1){
                wordarea.setText("");
                flag=0;
            }
        }
    }
    void time(){

    }
    void exit(){
        if(flag==0){
            System.exit(0);
        }
        if(flag==1){
            int m=  JOptionPane.showConfirmDialog(this,"Should you want to save the file");
            if(m==0){
                save();
            }
            if(m==1){
                System.exit(0);
            }
        }
    }

    void about(){
        JOptionPane.showMessageDialog(null,"The Test Editor is made by JihengXin and YuchenLiu");
    }
    class MyActionListener1 implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()instanceof JMenuItem){
                if(e.getActionCommand()=="Cut"){
                    wordarea.cut();
                }
                if(e.getActionCommand()=="Copy"){
                    wordarea.copy();
                }
                if(e.getActionCommand()=="Paste"){
                    wordarea.paste();
                }
                if(e.getActionCommand()=="Search"){
                    d1.setVisible(true);
                }
                if(e.getActionCommand()=="Open"){
                    open();
                }
                if(e.getActionCommand()=="Save"){
                    save();
                }
                if(e.getActionCommand()=="New"){
                    newfile();
                }
                if(e.getActionCommand()=="Exit"){
                    exit();
                }
                if(e.getActionCommand()=="T&D"){
                    time();
                }
                if(e.getActionCommand()=="About"){
                    about();
                }
            }

        }

    }
}