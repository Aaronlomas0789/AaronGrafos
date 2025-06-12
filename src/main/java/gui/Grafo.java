
package gui;

import java.util.ArrayList;

public class Grafo {
    private ArrayList<Vertice> vertices;
    private int[][] matrizAdyacencia;

    public Grafo() {
        vertices = new ArrayList<>();
        matrizAdyacencia = new int[100][100]; // máximo 100 vértices
    }

    public void agregarVertice(String nombre, int x, int y) {
        vertices.add(new Vertice(nombre, x, y));
    }

    public void conectar(String nombre1, String nombre2) {
        int i = buscarIndice(nombre1);
        int j = buscarIndice(nombre2);
        if (i != -1 && j != -1) {
            matrizAdyacencia[i][j] = 1;
            matrizAdyacencia[j][i] = 1;
        }
    }

    public int buscarIndice(String nombre) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }
}
