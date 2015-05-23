import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by cris on 25/03/15.
 */
public class Nodo {
    public int x=0;
    public int y=0;
    private int key;
    private Nodo padre=null;
    private Nodo d=null;
    private Nodo s=null;

    public Nodo(int key){
        this.key=key;

    }
    public int getKey(){
        return key;
    }
    public void setKey(int n){
        key=n;
    }

    public Nodo getPadre(){
        return padre;

    }

    public Nodo getD(){
        return d;
    }
    public Nodo getS(){
        return s;
    }
    public void setS(Nodo s){
        this.s = s;
    }
    public void setD(Nodo d){
        this.d= d;
    }
    public void setPadre(Nodo p){
        this.padre = p;
    }

    public boolean isRoot(){
        return padre==null ? true: false;
    }

    public boolean isLeaf(){
        return d==null && s == null ? true:false;
    }

    public int segno(){
        if(padre!=null && (padre.getS()==null || padre.getD()==null)){
            return 0;
        }else if(padre != null && padre.getS()==this){
             return -1;
        }else{
            return 1;
        }

    }
    public int getAltezza(){
        if(padre==null){
            return 1;
        }else{
            return 1+padre.getAltezza();
        }
    }



    public void drawNodo(Graphics2D g){
        int tempx=0, tempy=0;
        if(padre!=null){
            tempx=padre.x;
            tempy=padre.y;
            int SHIFTX=(int)((BST.getAltezza()*20/getAltezza()+5)/getAltezza()*BST.getAltezza());
            x=padre.x+SHIFTX*segno();
            y=padre.y+60;
        }
        g.draw(new Ellipse2D.Double(x,y,20,20));
        if(padre!= null)
        g.drawLine(tempx+10,tempy+20,x+10,y);
        g.drawString("" + key,x+6,y+13);
    }


}
