import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.odf.OpenDocumentParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Assignment extends JFrame implements DocumentListener {

    private static final long serialVersionUID = 1L;
    JFileChooser path;
    JMenuBar toolbar=new JMenuBar();
    JMenu file=new JMenu("File");
    JMenu look =new JMenu("Search");
    JMenu view=new JMenu("View");
    JMenu help =new JMenu("Help");
    JTextArea wordarea=new JTextArea();
    JScrollPane imgScrollPane = new JScrollPane(wordarea);
    String [] str1={"New","Open","Save","Printor","Exit","PDF","odt"};
    String [] str2={"Cut","Copy","Paste","Search","T&D"};
    String [] str3={"About"};
    int flag=0;
    String source="";
    public static void main(String[] args) {
        JFrame Assignmet1=new Assignment();
        Assignmet1.setVisible(true);
    }
    public Assignment(){
        setTitle("Test Editor");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width/2,screenSize.height/2);
        setLocation(screenSize.width/4,screenSize.height/4);
        add(imgScrollPane,BorderLayout.CENTER);
        Component textPane = null;
        JScrollPane scrollPane = new JScrollPane(textPane);
        setJMenuBar(toolbar);
        toolbar.add(file);
        toolbar.add(look);
        toolbar.add(view);
        toolbar.add(help);
        wordarea.getDocument().addDocumentListener(this);
        for(int i=0;i<str1.length;i++){
            JMenuItem item1= new JMenuItem(str1[i]);
            item1.addActionListener(new MyActionListener1());
            file.add(item1);
        }
        for(int i=0;i<str2.length;i++){
            JMenuItem item2= new JMenuItem(str2[i]);
            item2.addActionListener(new MyActionListener1());
            view.add(item2);
        }
        for(int i=0;i<str3.length;i++){
            JMenuItem item3= new JMenuItem(str3[i]);
            item3.addActionListener(new MyActionListener1());
            help.add(item3);
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

    void open(){
        FileDialog  filedialog=new FileDialog(this,"open",FileDialog.LOAD);
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
            } catch (FileNotFoundException x) {
                x.printStackTrace();
            }
            try {
                out.write(wordarea.getText());
            } catch (IOException x) {
                x.printStackTrace();
            }
            flag=0;
            try {
                out.close();
                source=wordarea.getText();
            } catch (IOException x) {
                x.printStackTrace();
            }
        }
    }

    void file(){
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        wordarea.append(df.format(new Date()));
        wordarea.append("\r\n");
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

    void PDF() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        OutputStream outputStream = new FileOutputStream(new File(String.valueOf(path.getSelectedFile().getAbsoluteFile())));
        PdfWriter writer = PdfWriter.getInstance(document,outputStream);
        document.open();
        assert writer != null;
        PdfContentByte canvas = writer.getDirectContent();
        CMYKColor magentaColor = new CMYKColor(1.f, 1.f, 1.f, 1.f);
        canvas.setColorStroke(magentaColor);
        canvas.moveTo(document.left(), document.top() - 4);
        canvas.lineTo(document.right(), document.top() - 4);
        canvas.closePathStroke();
        String content = wordarea.getText();
        String[] StringList = content.split("\n");
        for (String s : StringList) {
            Paragraph paragraphText1 = new Paragraph(s);
            try {
                document.add(paragraphText1);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        document.close();
    }

    void about(){
        JOptionPane.showMessageDialog(null,"The Test Editor is made by JihengXin and YuchenLiu.You can connect us by send email to 156125026@qq.com");
    }


    void dayin(){
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, pras);
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
            PrintService service = ServiceUI.printDialog(null, 200, 200, printService, defaultService, flavor, pras);
            if (service != null) {
                try {
                    DocPrintJob job = service.createPrintJob();
                    FileInputStream fis;
                    PDF();
                    fis = new FileInputStream(path.getSelectedFile().getAbsoluteFile());
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc doc = new SimpleDoc(fis, flavor, das);
                    job.print(doc, pras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    void odt() throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();

        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File(
                "D:\\odt_file.odt"));
        ParseContext pcontext = new ParseContext();

        //Open Document Parser
        OpenDocumentParser openofficeparser = new OpenDocumentParser ();

        openofficeparser.parse(inputstream, handler, metadata,pcontext);
        String out = "Contents of the document:" + handler.toString();
        wordarea.setText(out);
        String[] metadataNames = metadata.names();

        for(String name : metadataNames) {
            System.out.println(name + " :  " + metadata.get(name));
        }
    }

    class MyActionListener1 extends Component implements ActionListener{
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
                if(e.getActionCommand()=="Open"){
                    open();
                }
                if(e.getActionCommand()=="Save"){
                    save();
                }
                if(e.getActionCommand()=="New"){
                    file();
                }
                if(e.getActionCommand()=="Exit"){
                    exit();
                }
                if(e.getActionCommand()=="Printor"){
                    path = new JFileChooser();
                    path.showDialog(this,"sever as a pdf");
//                    File filename = filechoose.getSelectedFile().getAbsoluteFile();
                    dayin();
                }
                if(e.getActionCommand()=="T&D"){
                    time();
                }
                if(e.getActionCommand()=="odt"){
                    try {
                        odt();
                    } catch (IOException | TikaException | SAXException ex) {
                        ex.printStackTrace();
                    }
                }
                if(e.getActionCommand()=="PDF"){
                    try {
                        PDF();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (DocumentException ex) {
                        ex.printStackTrace();
                    }
                }
                if(e.getActionCommand()=="About"){
                    about();
                }
            }

        }

    }
}