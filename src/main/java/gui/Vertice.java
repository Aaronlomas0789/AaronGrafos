
package gui;

public class Vertice {
    private String nombre;
    private int x;
    private int y;

    public Vertice(String nombre, int x, int y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return nombre;
    }
}
