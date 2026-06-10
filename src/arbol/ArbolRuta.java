package arbol;

import java.util.ArrayList;
import java.util.List;

public class ArbolRuta {
    private NodoArbol raiz;

    public ArbolRuta(String raiz) {
        this.raiz = null;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }
    public void setRaizDirecta(NodoArbol nuevaRaiz) {
        this.raiz = nuevaRaiz;
    }
    //Metodo recursivo para encontrar un nodo especifico
    private NodoArbol buscarnodo(NodoArbol actual, String nombre){
        if (actual == null){
            return null;
        }
        if (actual.getNombre().equalsIgnoreCase(nombre)){
            return actual;
        }
        for ( NodoArbol hijo: actual.getHijos()){
            NodoArbol encontrado = buscarnodo(hijo, nombre);
            if (encontrado != null){
                return encontrado;
            }
        }
        return null;
    }
    //Metodo para insertar en el arbol
    public void insertar(String padre, String hijo) {
        NodoArbol nodoPadre = buscarnodo(raiz, padre);
        if (nodoPadre == null) return;

        nodoPadre.getHijos().add(new NodoArbol(hijo));

        System.out.println("Insertando: " + padre + " -> " + hijo);

    }
    //Metodo publico para poder utilizar el metodo buscar
    public NodoArbol buscar(String nombre){
        return buscarnodo(raiz, nombre);
    }
    //Metodo publico para rellenar una lista con el resultado del metodo recursivo
    public List<String> preorden(NodoArbol nodo) {
        List<String> pre = new ArrayList<>();
        preordenrec(raiz, pre);
        return pre;
        }
    //Metodo recursivo que devuelve el recorrido en preorden del arbol
    private void preordenrec(NodoArbol nodo, List<String> pre){
        if (nodo == null) return;

        pre.add(nodo.getNombre());

        for (NodoArbol hijo : nodo.getHijos()){
            preordenrec(hijo, pre);
        }
    }
    //Metodo para comprobar si el arbol esta vacio
    public boolean estaVacio(){
        return raiz == null;
    }


}
