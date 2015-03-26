import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * Created by cris on 25/03/15.
 */
public class FrameClass extends JFrame {
    private Canvas myCanvas;
    private BST albero;
    private int [] chiavi= new int[16];

    public FrameClass(){


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        setPreferredSize(new Dimension(600,600));
        setSize(new Dimension(600, 600));
        myCanvas=new Canvas();
        add(new Controlli(this), BorderLayout.SOUTH);
        add(myCanvas, BorderLayout.CENTER);
        albero = new BST();
        setVisible(true);




    }
    public void delAll(){
        albero = new BST();
        myCanvas.repaint();
        myCanvas.updateUI();
    }
    public void randomTree(int n){
        chiavi = new int[n];
        Random r = new Random();
        for(int i=0; i<n;i++){
            int p= r.nextInt(10)<5 ? -1:1;
            chiavi[i]=p*r.nextInt(100);
        }
        albero = new BST(chiavi);
        myCanvas.repaint();
        myCanvas.updateUI();
    }
    public void addNode(int n){
        albero.insert(n);
        myCanvas.repaint();
        myCanvas.repaint();
    }
    public void delNode(int n){
        albero.delete(n);
        myCanvas.repaint();
        myCanvas.updateUI();
    }
    public void uPdate(){
        myCanvas.updateUI();
    }


    public class Canvas extends JPanel{

        public Canvas(){





        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            AffineTransform at = AffineTransform.getTranslateInstance(getWidth()/2,0);
            g2.setTransform(at);
            albero.printTree(g2);

        }


    }
    public class Controlli extends JPanel{
        JTextArea testo;
        FrameClass up;
        public Controlli(FrameClass p){
            JPanel cnt = new JPanel();
            cnt.setMaximumSize(new Dimension(100,100));
            cnt.setMaximumSize(new Dimension(100,100));
            cnt.setLayout(new BoxLayout(cnt, BoxLayout.X_AXIS));
            up=p;
            testo= new JTextArea("Valore",1,5);
            JButton add = new JButton("Add");
            add.addActionListener(new ascButton());
            JButton newTree = new JButton("New");
            newTree.addActionListener(new ascButton());
            JButton delAll = new JButton("Delete All");
            delAll.addActionListener(new ascButton());
            JButton del = new JButton("Delete");
            del.addActionListener(new ascButton());
            JButton upd = new JButton("Update");
            upd.addActionListener(new ascButton());

            cnt.add(testo);

            cnt.add(add);
            cnt.add(del);
            cnt.add(newTree);
            cnt.add(delAll);
            cnt.add(upd);
            add(cnt,BorderLayout.NORTH);



        }
        public class ascButton implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(actionEvent.getActionCommand()=="Add"){
                    up.addNode(Integer.parseInt(testo.getText()));
                }else if(actionEvent.getActionCommand()=="Delete"){
                    up.delNode(Integer.parseInt(testo.getText()));
                }else if (actionEvent.getActionCommand()=="Delete All"){
                        up.delAll();

                }else if(actionEvent.getActionCommand()=="New"){
                    up.randomTree(Integer.parseInt(testo.getText()));
                }else if(actionEvent.getActionCommand()=="Update"){
                    up.uPdate();
                }
            }
        }
    }

    public static void main(String[] args){
        new FrameClass();
    }
}
