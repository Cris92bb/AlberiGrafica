import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by cris on 25/03/15.
 */
public class Nodo {
    int x =0;
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
        isLeft();
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
    public int altezza(){

        if(padre==null)
            return 0;
        else
            return padre.altezza()+1;
    }
    public void isLeft(){

        if(padre != null && padre.getS()!= this)
            x=padre.x+100/altezza()+40/altezza();
        else if(padre==null){
            x=20;
        }else {
            x=padre.x-100/altezza()-40/altezza();
        }


    }



    public void drawNodo(Graphics2D g){
        g.draw(new Ellipse2D.Double(x,altezza()*40+50,20,20));
        if(padre!= null)
        g.drawLine(padre.x+10,padre.altezza()*40+50,x+10,altezza()*40+50);
        g.drawString("" + key,x+3, altezza()*40+65);
    }


}
