
package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class GrafoUI extends JFrame {
    private Grafo grafo = new Grafo();

    private JTextField nombreField = new JTextField(10);
    private JTextField xField = new JTextField(5);
    private JTextField yField = new JTextField(5);
    private JComboBox<String> vertice1Box = new JComboBox<>();
    private JComboBox<String> vertice2Box = new JComboBox<>();
    private DefaultTableModel matrizModel = new DefaultTableModel();
    private JTable matrizTable = new JTable(matrizModel);

    private JPanel drawPanel = new JPanel() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Vertice v : grafo.getVertices()) {
                g.fillOval(v.getX(), v.getY(), 10, 10);
                g.drawString(v.getNombre(), v.getX() + 10, v.getY());
            }

            int[][] matriz = grafo.getMatrizAdyacencia();
            var vertices = grafo.getVertices();
            for (int i = 0; i < vertices.size(); i++) {
                for (int j = i + 1; j < vertices.size(); j++) {
                    if (matriz[i][j] == 1) {
                        Vertice vi = vertices.get(i);
                        Vertice vj = vertices.get(j);
                        g.drawLine(vi.getX() + 5, vi.getY() + 5, vj.getX() + 5, vj.getY() + 5);
                    }
                }
            }
        }
    };

    public GrafoUI() {
        setTitle("Grafo con Coordenadas y Matriz");
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Nombre:"));
        controlPanel.add(nombreField);
        controlPanel.add(new JLabel("X:"));
        controlPanel.add(xField);
        controlPanel.add(new JLabel("Y:"));
        controlPanel.add(yField);
        JButton agregarButton = new JButton("Agregar Vértice");
        controlPanel.add(agregarButton);

        controlPanel.add(new JLabel("V1:"));
        controlPanel.add(vertice1Box);
        controlPanel.add(new JLabel("V2:"));
        controlPanel.add(vertice2Box);
        JButton conectarButton = new JButton("Conectar");
        controlPanel.add(conectarButton);

        add(controlPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        add(new JScrollPane(matrizTable), BorderLayout.SOUTH);

        agregarButton.addActionListener(e -> {
            String nombre = nombreField.getText();
            int x = Integer.parseInt(xField.getText());
            int y = Integer.parseInt(yField.getText());
            grafo.agregarVertice(nombre, x, y);
            vertice1Box.addItem(nombre);
            vertice2Box.addItem(nombre);
            actualizarMatriz();
            drawPanel.repaint();
        });

        conectarButton.addActionListener(e -> {
            String v1 = (String) vertice1Box.getSelectedItem();
            String v2 = (String) vertice2Box.getSelectedItem();
            grafo.conectar(v1, v2);
            actualizarMatriz();
            drawPanel.repaint();
        });
    }

    private void actualizarMatriz() {
        ArrayList<Vertice> vertices = grafo.getVertices();
        int size = vertices.size();
        matrizModel.setRowCount(0);
        matrizModel.setColumnCount(0);

        for (Vertice v : vertices) {
            matrizModel.addColumn(v.getNombre());
        }

        int[][] matriz = grafo.getMatrizAdyacencia();
        for (int i = 0; i < size; i++) {
            Object[] row = new Object[size];
            for (int j = 0; j < size; j++) {
                row[j] = matriz[i][j];
            }
            matrizModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GrafoUI().setVisible(true));
    }
}
