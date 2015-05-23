import java.awt.*;

public class BST {
    private BST Tree;
    private Nodo root;
    public static int nodi=0;


    public BST(int[] A) {for (int i = 0; i < A.length; i++) insert(A[i]);}
    public BST(int key) {root = new Nodo(key);}
    public BST() {}

    public Nodo root() {return root;}
    public void setRoot(Nodo root) {this.root = root;}

    private Nodo search_(Nodo x, int key) {return (x == null || x.getKey() == key) ? x : (key < x.getKey() ? search_(x.getS(), key) : search_(x.getD(), key));}
    public Nodo search(Nodo x) {return search_(root, x.getKey());}
    public Nodo search(int key) {return search_(root, key);}

    private Nodo max_(Nodo x) {return x == null || x.getD() == null ? x : max_(x.getD());}
    public Nodo max() {return max_(root);}

    private Nodo min_(Nodo x) {return x == null || x.getS() == null ? x : min_(x.getS());}
    public Nodo min() {return min_(root);}
    public static double getAltezza(){
        return Math.log10(nodi+1)/Math.log10(2)-1;
    }
    private Nodo find(int key, String id) {
        Nodo x = search(key);

        if (id.equals("next") ? x.getD() != null : x.getS() != null) {
            return id.equals("next") ? min_(x.getD()) : max_(x.getS());

        } else {
            Nodo y = x.getPadre();

            while (y != null && (id.equals("next") ? x != y.getS() : x != y.getD())) {
                x = y; x.setPadre(y);

            }

            return y;

        }
    }

    public Nodo next(Nodo x) {return find(x.getKey(), "next");}
    public Nodo previous(Nodo x) {return find(x.getKey(), "previous");}
    public Nodo next(int key) {return find(key, "next");}
    public Nodo previous(int key) {return find(key, "previous");}

    public void insert(Nodo x) {
        nodi++;
        Nodo y = null, z = root;
        boolean STOP = false;

        while (z != null && STOP == false) {
            if (z.getKey() == x.getKey()) {
                System.out.println("~Chiave giˆ inserita: " + x.getKey() + "\n");
                STOP = true;

            }

            y = z;
            z = x.getKey() < y.getKey() ? y.getS() : y.getD();

        }

        if (STOP == false) {

            if (y == null) {
                root = x;
                root.x=35;
                root.y=10;

            } else {
                x.setPadre(y);

                if (x.getKey() < y.getKey()) {
                    y.setS(x);

                } else {
                    y.setD(x);

                }
            }
        }
    }

    public void insert(int key) {insert(new Nodo(key));}

    private int children(Nodo x) {return (x.getS() == null ? 0 : 1) + (x.getD() == null ? 0 : 1);}

    private void setSon(Nodo x, Nodo y) {
        if (y != null) {
            y.setPadre(x);

            if (x != null) {

                if (y.getKey() < x.getKey()) {
                    x.setS(y);

                } else {
                    x.setD(y);

                }
            }
        }
    }

    public void delete(Nodo x) {
        if (x != null) {

            if (children(x) == 0) {

                if (x != root) {
                    if (x.getPadre().getS() == x) x.getPadre().setS(null);
                    else if (x.getPadre().getD() == x) x.getPadre().setD(null);

                } else root = null;

            } else if (children(x) == 1) {

                if (x != root) setSon(x.getPadre(), (x.getS() == null ? x.getD() : x.getS()));

                else {
                    root = x.getS() == null ? x.getD() : x.getS();
                    root.setPadre(null);

                }

            } else {
                int k = next(x.getKey()).getKey();
                delete(next(x.getKey()));
                x.setKey(k);

            }

        } else System.out.println("Impossibile eliminare null");
    }

    public void delete(int key) {
        nodi--;
        delete(search(key));}

    public void printNodo(Nodo x, Graphics2D g) {
        if (x != null) {
            x.drawNodo(g);
            System.out.println("~ key: " + x.getKey());

            if (x.getPadre() != null) System.out.println("  - parent: " + x.getPadre().getKey());
            if (x.getPadre() == null) {System.out.println("  - parent: null");}

            if (x.getS() != null) {System.out.println("  - left: " + x.getS().getKey()); x.drawNodo(g);}
            if (x.getS() == null) System.out.println("  - left: null");

            if (x.getD() != null) {System.out.println("  - right: " + x.getD().getKey()); x.drawNodo(g);}
            if (x.getD() == null)System.out.println("  - right: null");

            if (x.getS() == null && x.getD() == null) System.out.println(x.getPadre() == null ? "  _ROOT_&_LEAVE_\n" : "  _LEAVE_\n");
            else if (x.getPadre() == null) System.out.println("  _ROOT_\n");
            else System.out.println("  _Nodo_\n");

        } else System.out.println("null");

    }

    private void print_(Nodo x,Graphics2D g) {
        printNodo(x, g);
        if (x != null && x.getS() != null) print_(x.getS(),g);
        if (x != null && x.getD() != null) print_(x.getD(),g);

    }

    public void printTree(Graphics2D g) {print_(root,g);}

}