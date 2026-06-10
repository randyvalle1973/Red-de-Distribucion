package Grafo;

import arbol.*;

import java.util.ArrayList;
import java.util.List;

public class GrafoAlmacenes {
    private int[][] matriz;
    private String[] almacenes;
    private int cantidadVertices;
    private int maxVertices;

    public GrafoAlmacenes(int maxVertices) {
        this.maxVertices = maxVertices;
        matriz = new int[maxVertices][maxVertices];
        almacenes = new String[maxVertices];
        cantidadVertices = 0;
    }
    //Metodo para comprobar si el grafo esta vacio
    public boolean grafovacio(){
        return cantidadVertices == 0;
    }
    //Metodo para agregar nodos al grafo, en este caso almacenes
    public boolean agregarAlmacen(String nombre) {
        if (obtenerIndice(nombre) != -1){
            return false;
        }
        if (cantidadVertices<maxVertices){
            almacenes[cantidadVertices] = nombre;
            cantidadVertices++;
            return true;
    }
        return false;
    }
    //Metodo auxiliar para obtener el inidice del nodo
    public int obtenerIndice(String nombre) {
        for(int i = 0; i < cantidadVertices; i++) {
            if(almacenes[i].equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }
    //Metodo para agregar las rutas de entrega
    public void agregarRuta(String origen, String destino, int peso) {
        int i = obtenerIndice(origen);
        int j = obtenerIndice(destino);

        if(i != -1 && j != -1) {
            matriz[i][j] = peso;
            matriz[j][i] = peso;
        }
    }
    //Metodo para obtener el menor indice
    private int VerticeMinimo(int[] distancias, boolean[] visitados) {
        int minimo = Integer.MAX_VALUE;
        int indiceMinimo = -1;

        for(int i = 0; i < cantidadVertices; i++) {
            if(!visitados[i] && distancias[i] < minimo) {
                minimo = distancias[i];
                indiceMinimo = i;
            }
        }
        return indiceMinimo;
    }
    //Metodo dijkstra para obtener el camino minimo hacia cada nodo
    public List<resultado> dijkstra(String origen) {
        int origenI = obtenerIndice(origen);
        if(origenI == -1) {
            return new ArrayList<>();
        }
        int[] distancias = new int[cantidadVertices];
        boolean[] visitados = new boolean[cantidadVertices];
        int[] padres = new int[cantidadVertices];

        for(int i = 0; i < cantidadVertices; i++) {
            distancias[i] = Integer.MAX_VALUE;
            visitados[i] = false;
            padres[i] = -1;
        }
        distancias[origenI] = 0;

        for(int i = 0; i < cantidadVertices - 1; i++) {
            int u = VerticeMinimo(distancias, visitados);
            if(u == -1) {
                break;
            }
            visitados[u] = true;
            for(int v = 0; v < cantidadVertices; v++) {
                if(
                        !visitados[v]
                                && matriz[u][v] != 0
                                && distancias[u] != Integer.MAX_VALUE
                                && distancias[u] + matriz[u][v] < distancias[v]
                ) {
                    distancias[v] =
                            distancias[u] + matriz[u][v];

                    padres[v] = u;
                }
            }
        }
        List<resultado> resultados = new ArrayList<>();
        for (int i = 0; i < cantidadVertices; i++) {
            if (distancias[i] != Integer.MAX_VALUE) {
                String ruta = construircamino(padres, i).trim();
                resultados.add(
                        new resultado(
                                almacenes[i],
                                distancias[i],
                                ruta
                        )
                );
            }
        }
        return resultados;
    }
    //Metodo para mostrar el camino minimo hallado
    private String construircamino(int[] padre, int actual) {
        if (actual == -1) return "";
        String ruta = construircamino(padre, padre[actual]);
        if (ruta.isEmpty()){
            return almacenes[actual];
        }
        return ruta + " -> " + almacenes[actual];
    }
    //Metodo prim para hallar el Arbol de expansion minima y rellenar el arbol general
    public ArbolRuta prim() {
        int[] padres = new int[cantidadVertices];
        int[] menor = new int[cantidadVertices];
        boolean[] incluidos = new boolean[cantidadVertices];

        for (int i = 0; i < cantidadVertices; i++) {
            menor[i] = Integer.MAX_VALUE;
            incluidos[i] = false;
            padres[i] = -1;
        }
        menor[0] = 0;
        for (int i = 0; i < cantidadVertices - 1; i++) {
            int u = MinimoPrim(menor, incluidos);
            if(u == -1){
                break;
            }
            incluidos[u] = true;
            for (int v = 0; v < cantidadVertices; v++) {
                if (matriz[u][v] != 0 && !incluidos[v] && matriz[u][v] < menor[v]) {
                    menor[v] = matriz[u][v];
                    padres[v] = u;
                }
            }
        }
        NodoArbol[] nodos = new NodoArbol[cantidadVertices];
        for (int i = 0; i < cantidadVertices; i++) {
            nodos[i] = new NodoArbol(almacenes[i]);
        }
        for (int i = 1; i < cantidadVertices; i++) {
            int padre = padres[i];
            if (padre != -1) {
                nodos[padre].getHijos().add(nodos[i]);
            }
        }

        ArbolRuta arbol = new ArbolRuta(almacenes[0]);
        arbol.setRaizDirecta(nodos[0]);
        return arbol;
    }
    //Metodo auxiliar para encontrar el minimo en prim
    private int MinimoPrim(int[] menor, boolean[] incluidos) {
        int minimo = Integer.MAX_VALUE;
        int indiceMinimo = -1;

        for(int i = 0; i < cantidadVertices; i++) {
            if(!incluidos[i] && menor[i] < minimo) {
                minimo = menor[i];
                indiceMinimo = i;
            }
        }
        return indiceMinimo;
    }

}
